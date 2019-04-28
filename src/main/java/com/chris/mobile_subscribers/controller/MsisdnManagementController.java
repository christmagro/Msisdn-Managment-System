package com.chris.mobile_subscribers.controller;

import com.chris.mobile_subscribers.dto.MsisdnDetailSubscriptionDto;
import com.chris.mobile_subscribers.dto.MsisdnDto;
import com.chris.mobile_subscribers.dto.MsisdnSubscriptionDto;
import com.chris.mobile_subscribers.exception.GlobalException;
import com.chris.mobile_subscribers.service.MsisdnService;
import com.chris.mobile_subscribers.validation.ProvisionValidation;
import com.chris.mobile_subscribers.validation.UpdateTermValidation;
import com.chris.mobile_subscribers.validation.UpdateValidation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MsisdnManagementController {

    private final MsisdnService<MsisdnDto, MsisdnSubscriptionDto, MsisdnDetailSubscriptionDto> msisdnService;

    public MsisdnManagementController(MsisdnService<MsisdnDto, MsisdnSubscriptionDto, MsisdnDetailSubscriptionDto> msisdnService) {
        this.msisdnService = msisdnService;
    }

    @PostMapping("/")
    @ApiOperation(
            value = "Register a new Msisdn to the system",
            notes = "Api will register a never used MSISDN")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The newly added msisdn"),
            @ApiResponse(code = 400, message = "Provided Msisdn already registered"),
    })
    public String registerMsisdn(@RequestBody @Valid MsisdnDto msisdnDto) {
        return msisdnService.registerMsisdn(msisdnDto);
    }

    @PutMapping("/provision")
    @PostMapping("/")
    @ApiOperation(
            value = "Provision a registered Msisdn to the system",
            notes = "Api will only provision if it is not already provisioned and if msisdn is already registered")
    @ApiResponses({
            @ApiResponse(code = 200, message = "If request successful HTTP Response 200 will be given "),
            @ApiResponse(code = 400, message = "Msisdn not Registered or Msisdn already provisioned")
    })
    public String provisionMsisdn(@RequestBody @Validated(ProvisionValidation.class) MsisdnSubscriptionDto msisdnSubscriptionDto) {
        return msisdnService.provisionMsisdn(msisdnSubscriptionDto);
    }

    @ApiOperation(
            value = "DeProvision a registered Msisdn from the system",
            notes = "Api will only deprovision if it is already provisioned and if msisdn is already registered")
    @ApiResponses({
            @ApiResponse(code = 200, message = "If request successful HTTP Response 200 will be given "),
            @ApiResponse(code = 400, message = "Msisdn not Registered or Msisdn already deprovisioned")
    })
    @PutMapping("/deprovision")
    public String deProvisionMsisdn(@RequestBody @Valid MsisdnSubscriptionDto msisdnSubscriptionDto) {
        return msisdnService.deProvisionMsisdn(msisdnSubscriptionDto);
    }

    @PutMapping("/terminate")
    @ApiOperation(
            value = "Terminate a registered Msisdn from the system",
            notes = "Api will only Terminate if it is not already terminated and if msisdn is already registered")
    @ApiResponses({
            @ApiResponse(code = 200, message = "If request successful HTTP Response 200 will be given "),
            @ApiResponse(code = 400, message = "Msisdn not Registered or Msisdn already terminated")
    })
    public String terminateMsisdn(@RequestBody @Valid MsisdnSubscriptionDto msisdnSubscriptionDto) {
        return msisdnService.terminateMsisdn(msisdnSubscriptionDto);
    }

    @PutMapping("/updateTerm")
    @ApiOperation(
            value = "Updated msisdn subscription type from Prepaid to Postpaid or vice versa",
            notes = "Api will only update type if subscription is not already the same as requested and if msisdn is already registered")
    @ApiResponses({
            @ApiResponse(code = 200, message = "If request successful HTTP Response 200 will be given "),
            @ApiResponse(code = 400, message = "Msisdn not Registered or Msisdn not provisioned")
    })
    public String updateSubscriptionMsisdn(@RequestBody @Validated(UpdateTermValidation.class) MsisdnSubscriptionDto msisdnSubscriptionDto) {
        return msisdnService.updateSubscriptionMsisdn(msisdnSubscriptionDto);
    }

    @PutMapping("/updateOwner")
    @ApiOperation(
            value = "Updated msisdn subscription owner or user",
            notes = "Api will only update user or owner if msisdn is provisioned and if msisdn is already registered")
    @ApiResponses({
            @ApiResponse(code = 200, message = "If request successful HTTP Response 200 will be given "),
            @ApiResponse(code = 400, message = "Msisdn not Registered or Msisdn not provisioned")
    })
    public String updateOwner(@RequestBody @Validated(UpdateValidation.class) MsisdnSubscriptionDto msisdnSubscriptionDto) {
        return msisdnService.updateOwner(msisdnSubscriptionDto);
    }

}
