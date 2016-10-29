package com.rnei.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.rnei.model.Expense;
import com.rnei.model.Vendor;
import com.rnei.model.VendorItemRate;
import com.rnei.model.VendorSchedule;
import com.rnei.service.exception.RENIDataServiceException;
import com.rnei.service.exception.RENIServiceException;

public interface VendorDataService {

  String createVendorDetails(Vendor vendor) throws RENIServiceException;

  String createVendorId(String vendorType) throws RENIServiceException;

  boolean isVendorExists(String vendorId) throws RENIServiceException;

  void updateVendorDetails(Vendor vendor) throws RENIServiceException;

  void deleteVendor(String vendorId) throws RENIServiceException;

  List<Vendor> fetchVendorDetails() throws RENIServiceException;

  Vendor fetchVendor(String vendorId) throws RENIServiceException;

  List<VendorItemRate> getVendorItemRateDetails(String vendorId);

  void createVendorItemRates(VendorItemRate vendorItemRate);

  VendorItemRate getVendorItemRateByCode(String vendorId, String itemCode);

  void updateVendorItemRates(VendorItemRate vendorItemRate);

  void deleteVendorItemRate(String userId, String vendorId, String itemCode);

  List<VendorItemRate> getVendorItemRates(String vendorId, String itemCode);

  List<VendorSchedule> getVendorPickupSchedule(String vendorId, Date scheduleDate, String areaCode);

  String createVendorPickupSchedule(VendorSchedule vendorSchedule);

  void updateVendorPickupSchedule(VendorSchedule vendorSchedule);

  List<VendorSchedule> getVendorPickupSchedules(String vendorId);

  VendorSchedule getVendorPickupSchedule(String vendorId);


}
