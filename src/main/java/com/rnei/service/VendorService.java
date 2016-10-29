package com.rnei.service;

import java.util.List;

import com.rnei.model.Expense;
import com.rnei.model.Vendor;
import com.rnei.model.VendorItemRate;
import com.rnei.model.VendorSchedule;
import com.rnei.service.exception.RENIServiceException;
import com.rnei.service.exception.RENIValidationException;

public interface VendorService {

  String createVendorDetails(Vendor vendor) throws RENIServiceException;

  void updateVendorDetails(Vendor vendor) throws RENIServiceException;

  void deleteVendor(String vendorId) throws RENIServiceException;

  List<Vendor> fetchVendorDetails() throws RENIServiceException;

  Vendor fetchVendor(String vendorId) throws RENIServiceException;

  void createVendorItemRates(VendorItemRate vendorItemRate) throws RENIServiceException;

  void updateVendorItemRates(VendorItemRate vendorItemRate) throws RENIServiceException;

  void deleteVendorItemRate(String userId, String vendorId, String itemCode) throws RENIValidationException;

  List<VendorItemRate> getVendorItemRates(String vendorId, String itemCode);

  String createVendorPickupSchedule(VendorSchedule vendorSchedule) throws RENIServiceException;

  void updateVendorPickupSchedule(VendorSchedule vendorSchedule) throws RENIServiceException;

  List<VendorSchedule> getVendorPickupSchedules(String vendorId) throws RENIServiceException;

  VendorSchedule getVendorPickupSchedule(String pickupScheduleId, String vendorId) throws RENIServiceException;
}
