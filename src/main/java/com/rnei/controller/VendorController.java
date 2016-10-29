package com.rnei.controller;

import static com.rnei.service.constants.RENIServiceConstant.VENDOR_PATH;

import java.util.List;

import static com.rnei.service.constants.RENIServiceConstant.ALL_PATH;
import static com.rnei.service.constants.RENIServiceConstant.ITEM_RATES_PATH;
import static com.rnei.service.constants.RENIServiceConstant.UPLOAD_DOCUMENTS_PATH;
import static com.rnei.service.constants.RENIServiceConstant.PICKUP_SCHEDULE_PATH;
import static com.rnei.service.constants.RENIServiceConstant.VENDOR_ID_PATH_PARAM;
import static com.rnei.service.constants.RENIServiceConstant.USER_ID;
import static com.rnei.service.constants.RENIServiceConstant.ITEM_CODE_PARAM;
import static com.rnei.service.constants.RENIServiceConstant.PICKUP_SCHEDULED_ID_PARAM;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rnei.model.Compliance;
import com.rnei.model.Vendor;
import com.rnei.model.VendorItemRate;
import com.rnei.model.VendorSchedule;
import com.rnei.service.VendorService;
import com.rnei.service.exception.RENIServiceException;

@Path(VENDOR_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class VendorController extends CoreController {

  @Autowired
  VendorService vendorService;

  // fetch vendor details
  @GET
  @Path(ALL_PATH)
  public Response fetchVendorDetails(@HeaderParam(USER_ID) final String userId) throws RENIServiceException {
    validateUser(userId);

    return Response.ok(validateResponse(vendorService.fetchVendorDetails())).build();
  }

  @GET
  @Path("/{" + VENDOR_ID_PATH_PARAM + "}")
  public Response fetchVendor(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorId) throws RENIServiceException {
    validateUser(userId);
    validateInput(vendorId);

    return Response.ok(validateResponse(vendorService.fetchVendor(vendorId))).build();
  }

  @POST
  public Response createVendorDetails(@HeaderParam(USER_ID) final String userId, final Vendor vendor) throws RENIServiceException {
    validateUser(userId);
    validateInput(vendor);
    vendor.setCreatedBy(userId);
    final String vendorId = vendorService.createVendorDetails(vendor);

    return Response.ok(vendorId).build();
  }

  @PUT
  public Response updateVendorDetails(@HeaderParam(USER_ID) final String userId, final Vendor vendor) throws RENIServiceException {
    validateUser(userId);
    validateInput(vendor);
    vendorService.updateVendorDetails(vendor);

    return Response.ok().build();
  }

  @DELETE
  @Path("/{" + VENDOR_ID_PATH_PARAM + "}")
  public Response deleteVendor(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorId) throws RENIServiceException {
    validateUser(userId);
    validateInput(vendorId);
    vendorService.deleteVendor(vendorId);

    return Response.ok().build();
  }

  @POST
  @Path("/{" + VENDOR_ID_PATH_PARAM + "}" + UPLOAD_DOCUMENTS_PATH)
  public Response createDocuments(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorID, final Compliance compliance) throws RENIServiceException {
    return Response.ok().build();
  }

  @GET
  @Path("/{" + VENDOR_ID_PATH_PARAM + "}" + UPLOAD_DOCUMENTS_PATH)
  public Response getUploadDocuments(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorID) throws RENIServiceException {
    return Response.ok().build();
  }

  @POST
  @Path("/{" + VENDOR_ID_PATH_PARAM + "}" + ITEM_RATES_PATH)
  public Response createVendorItemRate(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorID, final VendorItemRate vendorItemRate) throws RENIServiceException {
    validateUser(userId);
    validateInput(vendorID);
    vendorItemRate.setCreatedBy(userId);
    vendorService.createVendorItemRates(vendorItemRate);
    return Response.ok().build();
  }

  @GET
  @Path("/{" + VENDOR_ID_PATH_PARAM + "}" + ITEM_RATES_PATH)
  public Response getVendorItemRate(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorId, @QueryParam(ITEM_CODE_PARAM) final String itemCode) throws RENIServiceException {
    validateUser(userId);
    validateInput(vendorId);
    final List<VendorItemRate> vendorItemRates = vendorService.getVendorItemRates(vendorId, itemCode);

    return Response.ok(vendorItemRates).build();
  }

  @PUT
  @Path("/{" + VENDOR_ID_PATH_PARAM + "}" + ITEM_RATES_PATH)
  public Response updateVendorItemRate(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorID, final VendorItemRate vendorItemRate) throws RENIServiceException {
    validateUser(userId);
    validateInput(vendorID);
    vendorItemRate.setUpdatedBy(userId);
    vendorService.updateVendorItemRates(vendorItemRate);
    return Response.ok().build();
  }

  @DELETE
  @Path("/{" + VENDOR_ID_PATH_PARAM + "}" + ITEM_RATES_PATH + "/{" + ITEM_CODE_PARAM + "}")
  public Response deleteVendorItemRate(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorId, @PathParam(VENDOR_ID_PATH_PARAM) final String itemCode) throws RENIServiceException {
    validateUser(userId);
    validateInput(vendorId, itemCode);
    vendorService.deleteVendorItemRate(userId, vendorId, itemCode);
    return Response.ok().build();
  }

  @POST
  @Path(PICKUP_SCHEDULE_PATH + "/{" + VENDOR_ID_PATH_PARAM + "}")
  public Response createVendorPickupSchedule(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorId, final VendorSchedule vendorSchedule) throws RENIServiceException {
    validateUser(userId);
    validateInput(vendorSchedule, vendorId);
    vendorSchedule.setCreatedBy(userId);
    vendorSchedule.setVendorId(vendorId);
    final String pickupScheduleId = vendorService.createVendorPickupSchedule(vendorSchedule);

    return Response.ok(pickupScheduleId).build();
  }

  @PUT
  @Path(PICKUP_SCHEDULE_PATH + "/{" + VENDOR_ID_PATH_PARAM + "}" + "/{" + PICKUP_SCHEDULED_ID_PARAM + "}")
  public Response updateVendorPickupSchedule(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorId, @PathParam(PICKUP_SCHEDULED_ID_PARAM) final String pickupScheduleId, final VendorSchedule vendorSchedule) throws RENIServiceException {
    validateUser(userId);
    validateInput(vendorSchedule, vendorId);
    vendorSchedule.setUpdatedBy(userId);
    vendorSchedule.setVendorId(vendorId);
    vendorService.updateVendorPickupSchedule(vendorSchedule);

    return Response.ok().build();
  }

  @GET
  @Path(PICKUP_SCHEDULE_PATH + "/{" + VENDOR_ID_PATH_PARAM + "}" + "/{" + PICKUP_SCHEDULED_ID_PARAM + "}")
  public Response getVendorPickupSchedule(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorId, @PathParam(PICKUP_SCHEDULED_ID_PARAM) final String pickupScheduleId) throws RENIServiceException {
    validateUser(userId);
    validateInput(pickupScheduleId, vendorId);
    vendorService.getVendorPickupSchedule(pickupScheduleId, vendorId);

    return Response.ok().build();
  }

  @GET
  @Path(PICKUP_SCHEDULE_PATH + "/{" + VENDOR_ID_PATH_PARAM + "}")
  public Response getVendorPickupSchedules(@HeaderParam(USER_ID) final String userId, @PathParam(VENDOR_ID_PATH_PARAM) final String vendorId) throws RENIServiceException {
    validateUser(userId);
    validateInput(vendorId);
    vendorService.getVendorPickupSchedules(vendorId);

    return Response.ok().build();
  }
}