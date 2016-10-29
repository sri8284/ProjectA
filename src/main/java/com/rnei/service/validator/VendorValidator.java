package com.rnei.service.validator;

import static com.rnei.service.constants.RENIServiceConstant.VND_ADDRESS_VALIDATE;
import static com.rnei.service.constants.RENIServiceConstant.VND_BASIC_VALIDATE;
import static com.rnei.service.constants.RENIServiceConstant.VND_MANDTORY_VALIDATE;
import static com.rnei.service.constants.RENIServiceConstant.VENDOR_ID_MANDATORY;
import static com.rnei.service.constants.RENIServiceConstant.VND_ITEM_RATE_START_END_DATE_VALIDATE;
import static com.rnei.service.constants.RENIServiceConstant.VND_ITEM_RATE_VALIDATE;
import static com.rnei.service.constants.RENIServiceConstant.NO;
import static com.rnei.service.constants.RENIServiceConstant.EXISTING_VND_ITEM_RATE_DATES_VALIDATE;
import static com.rnei.service.constants.RENIServiceConstant.PICKUP_SCHEDULE_ID_MANDATORY_ERROR;
import static com.rnei.service.constants.RENIServiceConstant.PICKUP_SCHEDULE_ITEMS_MANDATORY_ERROR;
import static com.rnei.service.constants.RENIServiceConstant.PICKUP_SCHEDULE_MANDATORY_ERROR;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.rnei.common.util.CommonUtil;
import com.rnei.model.Operation;
import com.rnei.model.Vendor;
import com.rnei.model.VendorItemRate;
import com.rnei.model.VendorSchedule;
import com.rnei.service.exception.RENIServiceException;
import com.rnei.service.exception.RENIValidationException;

public class VendorValidator {

  public void validateVendorMandatoryFields(final Vendor vendor, final Operation operation) throws RENIServiceException {
    if (vendor == null) {
      throw new RENIValidationException(VND_MANDTORY_VALIDATE);
    }
    if (Strings.isNullOrEmpty(vendor.getVndfirstName()) || Strings.isNullOrEmpty(vendor.getVndlastName()) || Strings.isNullOrEmpty(vendor.getVndContactNo()) || Strings.isNullOrEmpty(vendor.getAreaCode()) || Strings.isNullOrEmpty(vendor.getVendorType()) || vendor.getVndAddress() == null) {
      throw new RENIValidationException(VND_BASIC_VALIDATE);
    }

    if (vendor.getVndAddress() != null && Strings.isNullOrEmpty(vendor.getVndAddress().getCity()) && Strings.isNullOrEmpty(vendor.getVndAddress().getState())) {
      throw new RENIValidationException(VND_ADDRESS_VALIDATE);
    }
    if (Operation.UPDATE.equals(operation)) {
      if (Strings.isNullOrEmpty(vendor.getVendorId())) {
        throw new RENIValidationException(VENDOR_ID_MANDATORY);
      }
    }
  }

  public void validateVendorItemRateDates(VendorItemRate vendorItemRate, List<VendorItemRate> existngVendorItemRates) throws RENIValidationException {
    if(existngVendorItemRates!=null ){
      for(VendorItemRate existingVendorItemRate : existngVendorItemRates){
        if(existingVendorItemRate.getStatus().equals(NO) && existingVendorItemRate.getItemCode().equals(vendorItemRate.getItemCode()) && vendorItemRate.getDiscountStartDate().compareTo(existingVendorItemRate.getDiscountStartDate())>=0 &&vendorItemRate.getDiscountEndDate().compareTo(existingVendorItemRate.getDiscountEndDate())<=0){
          throw new RENIValidationException(EXISTING_VND_ITEM_RATE_DATES_VALIDATE);
        }
      };
    }
  }

  public void validateVendorItemRateMandatoryFields(VendorItemRate vendorItemRate) throws RENIValidationException {
    if(CommonUtil.isNullOrEmpty(vendorItemRate.getItemCode(), vendorItemRate.getDiscount(), vendorItemRate.getDiscountStartDate(), vendorItemRate.getDiscountEndDate())){
     throw new RENIValidationException(VND_ITEM_RATE_VALIDATE);
    }
    
    if((vendorItemRate.getDiscountStartDate().compareTo(vendorItemRate.getDiscountEndDate()))>=0 ){
      throw new RENIValidationException(VND_ITEM_RATE_START_END_DATE_VALIDATE);
    }
  }

  public void validateManadatoryPicupScheduleFields(VendorSchedule vendorSchedule, final Operation operation) throws RENIValidationException {
    if(CommonUtil.isNullOrEmpty(vendorSchedule.getAreaCode(), vendorSchedule.getVendorId(), vendorSchedule.getScheduleDate(), vendorSchedule.getScheduleTime())){
      throw new RENIValidationException(PICKUP_SCHEDULE_MANDATORY_ERROR);
    }
    if(CollectionUtils.isEmpty(vendorSchedule.getItems())){
      throw new RENIValidationException(PICKUP_SCHEDULE_ITEMS_MANDATORY_ERROR);
    }
    if(Operation.UPDATE.equals(operation) && vendorSchedule.getPickupScheduleId()==null){
      throw new RENIValidationException(PICKUP_SCHEDULE_ID_MANDATORY_ERROR);
    }
  }
}
