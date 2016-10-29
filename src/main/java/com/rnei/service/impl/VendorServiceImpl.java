package com.rnei.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rnei.dao.VendorDataService;
import com.rnei.model.Item;
import com.rnei.model.Operation;
import com.rnei.model.Vendor;
import com.rnei.model.VendorItemRate;
import com.rnei.model.VendorSchedule;
import com.rnei.model.VendorScheduleItemRate;
import com.rnei.service.MasterDataSerice;
import com.rnei.service.VendorService;
import com.rnei.service.exception.RENIServiceException;
import com.rnei.service.exception.RENIValidationException;
import com.rnei.service.validator.VendorValidator;
import static com.rnei.service.constants.RENIServiceConstant.VENDOR_NOT_EXISTS;
import static com.rnei.service.constants.RENIServiceConstant.VND_ITEM_RATE_NOT_FOUND;
import static com.rnei.service.constants.RENIServiceConstant.VENDOR_PICKUP_SCHEDULE_COUNT;
import static com.rnei.service.constants.RENIServiceConstant.ITEM_MIN_VOLUMN;
import static com.rnei.service.constants.RENIServiceConstant.AREA_NOT_EXISTS;
import static com.rnei.service.constants.RENIServiceConstant.SCHEDULE_PAST_DATE_VALIDATE;
import static com.rnei.service.constants.RENIServiceConstant.INVALID_ITEM_CODES;
import static com.rnei.service.constants.RENIServiceConstant.SCHEDULE_ITEM_EXPECTED_ERROR;
import static com.rnei.service.constants.RENIServiceConstant.SCHEDULE_MAX_COUNT_ERROR;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class VendorServiceImpl implements VendorService {

  VendorDataService vendorDataService;

  VendorValidator vendorValidator = new VendorValidator();
  MasterDataSerice masterDataService;

  @Override
  public String createVendorDetails(final Vendor vendor) throws RENIServiceException {
    vendorValidator.validateVendorMandatoryFields(vendor, Operation.CREATE);
    vendor.setVendorId(vendorDataService.createVendorId(vendor.getVendorType()));

    return vendorDataService.createVendorDetails(vendor);
  }

  @Override
  public void updateVendorDetails(final Vendor vendor) throws RENIServiceException {
    vendorValidator.validateVendorMandatoryFields(vendor, Operation.UPDATE);
    validateVendorAvailable(vendor.getVendorId());
    // TODO - When we can change the vendor inactive to active - Do we have
    // another process to make him active again ?
    vendorDataService.updateVendorDetails(vendor);
  }

  @Override
  public void deleteVendor(final String vendorId) throws RENIServiceException {
    validateVendorAvailable(vendorId);

    vendorDataService.deleteVendor(vendorId);
  }

  @Override
  public List<Vendor> fetchVendorDetails() throws RENIServiceException {
    return vendorDataService.fetchVendorDetails();
  }

  @Override
  public Vendor fetchVendor(final String vendorId) throws RENIServiceException {
    return vendorDataService.fetchVendor(vendorId);
  }

  @Override
  public void createVendorItemRates(final VendorItemRate vendorItemRate) throws RENIServiceException {
    vendorValidator.validateVendorItemRateMandatoryFields(vendorItemRate);

    final List<VendorItemRate> existngVendorItemRates = vendorDataService.getVendorItemRateDetails(vendorItemRate.getVendorId());
    vendorValidator.validateVendorItemRateDates(vendorItemRate, existngVendorItemRates);
    final Item item = masterDataService.fetchItem(vendorItemRate.getItemCode());
    // TODO - itemcode status to check and throw the exception if not active. -
    // think on common code for all modules.
    vendorItemRate.setItemActualRate(item.getCurrentRate());

    vendorDataService.createVendorItemRates(vendorItemRate);
  }

  @Override
  public void updateVendorItemRates(final VendorItemRate vendorItemRate) throws RENIServiceException {
    vendorValidator.validateVendorItemRateMandatoryFields(vendorItemRate);
    validateVendorItemAvilable(vendorItemRate.getVendorId(), vendorItemRate.getItemCode());
    final Item item = masterDataService.fetchItem(vendorItemRate.getItemCode());
    // TODO - itemcode status to check and throw the exception if not active. -
    // think on common code for all modules.
    vendorItemRate.setItemActualRate(item.getCurrentRate());
    vendorDataService.updateVendorItemRates(vendorItemRate);
  }

  @Override
  public void deleteVendorItemRate(final String userId, final String vendorId, final String itemCode) throws RENIValidationException {
    validateVendorItemAvilable(vendorId, itemCode);
    vendorDataService.deleteVendorItemRate(userId, vendorId, itemCode);
  }

  @Override
  public List<VendorItemRate> getVendorItemRates(final String vendorId, final String itemCode) {

    return vendorDataService.getVendorItemRates(vendorId, itemCode);
  }

  @Override
  public String createVendorPickupSchedule(final VendorSchedule vendorSchedule) throws RENIServiceException {
    /*
     * 11. TODO schedule shouldn't be duplicate. date, itemcode, area code,
     * vendorId. 12. TODO schedule should be 2hr different for the same areaCode
     * or should be - should give
     */
    vendorValidator.validateManadatoryPicupScheduleFields(vendorSchedule, Operation.CREATE);
    pickupScheduleValidation(vendorSchedule);

    return vendorDataService.createVendorPickupSchedule(vendorSchedule);
  }

  @Override
  public void updateVendorPickupSchedule(final VendorSchedule vendorSchedule) throws RENIServiceException {
    vendorValidator.validateManadatoryPicupScheduleFields(vendorSchedule, Operation.UPDATE);
    pickupScheduleValidation(vendorSchedule);

    vendorDataService.updateVendorPickupSchedule(vendorSchedule);

  }

  @Override
  public List<VendorSchedule> getVendorPickupSchedules(final String vendorId) throws RENIServiceException {
    validateVendorAvailable(vendorId);
    return vendorDataService.getVendorPickupSchedules(vendorId);
  }

  @Override
  public VendorSchedule getVendorPickupSchedule(final String pickupScheduleId, final String vendorId) throws RENIServiceException {
    validateVendorAvailable(vendorId);
    return vendorDataService.getVendorPickupSchedule(vendorId);
  }

  private void validateVendorAvailable(final String vendorId) throws RENIServiceException {
    if (vendorId != null) {
      final boolean isVendorAvailable = vendorDataService.isVendorExists(vendorId);
      if (!isVendorAvailable) {
        throw new RENIValidationException(VENDOR_NOT_EXISTS);
      }
    } else {
      throw new RENIValidationException(VENDOR_NOT_EXISTS);
    }
  }

  private void pickupScheduleValidation(final VendorSchedule vendorSchedule) throws RENIServiceException {
    validateVendorAvailable(vendorSchedule.getVendorId());
    validateAreaCodeAvailable(vendorSchedule.getAreaCode());
    final LocalDate currenDate = LocalDate.now();
    final LocalDate scheduleDate = LocalDateTime.ofInstant(vendorSchedule.getScheduleDate().toInstant(), ZoneId.systemDefault()).toLocalDate();
    if (scheduleDate.isBefore(currenDate)) {
      throw new RENIValidationException(SCHEDULE_PAST_DATE_VALIDATE);
    }

    final Set<String> scheduleItemCodes = new HashSet<>();
    final Set<String> items = new HashSet<>();

    for (final VendorScheduleItemRate vendorScheduleItemRate : vendorSchedule.getItems()) {
      if (vendorScheduleItemRate.getItemExpectedVol() >= ITEM_MIN_VOLUMN) {
        throw new RENIValidationException(SCHEDULE_ITEM_EXPECTED_ERROR + ITEM_MIN_VOLUMN);
      }

      scheduleItemCodes.add(vendorScheduleItemRate.getItemCode());
    }
    masterDataService.fetchItemDetails().stream().forEach(item -> items.add(item.getItemCode()));
    final Collection<String> invalidItemCodes = CollectionUtils.subtract(scheduleItemCodes, items);
    if (invalidItemCodes != null) {
      throw new RENIValidationException(INVALID_ITEM_CODES + invalidItemCodes);
    }

    final List<VendorSchedule> vendorSchedules = vendorDataService.getVendorPickupSchedule(vendorSchedule.getVendorId(), vendorSchedule.getScheduleDate(), vendorSchedule.getAreaCode());
    if (vendorSchedules != null && vendorSchedules.size() == VENDOR_PICKUP_SCHEDULE_COUNT) {
      throw new RENIValidationException(SCHEDULE_MAX_COUNT_ERROR);
    }
  }
  
  private void validateAreaCodeAvailable(final String vendorId) throws RENIServiceException {
    if (vendorId != null) {
      final boolean isVendorAvailable = vendorDataService.isVendorExists(vendorId);
      if (!isVendorAvailable) {
        throw new RENIValidationException(AREA_NOT_EXISTS);
      }
    } else {
      throw new RENIValidationException(AREA_NOT_EXISTS);
    }
  }

  private void validateVendorItemAvilable(final String vendorId, final String itemCode) throws RENIValidationException {
    final VendorItemRate existngVendorItemRate = vendorDataService.getVendorItemRateByCode(vendorId, itemCode);
    if (existngVendorItemRate != null) {
      throw new RENIValidationException(VND_ITEM_RATE_NOT_FOUND);
    }
  }
}
