package com.chris.mobile_subscribers.controller;

import com.chris.mobile_subscribers.dto.MsisdnDetailSubscriptionDto;
import com.chris.mobile_subscribers.dto.MsisdnDto;
import com.chris.mobile_subscribers.dto.MsisdnSubscriptionDto;
import com.chris.mobile_subscribers.service.MsisdnService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/msisdn")
public class MsisdnQueryController {

    private final MsisdnService<MsisdnDto, MsisdnSubscriptionDto, MsisdnDetailSubscriptionDto> msisdnService;

    public MsisdnQueryController(MsisdnService<MsisdnDto, MsisdnSubscriptionDto, MsisdnDetailSubscriptionDto> msisdnService) {
        this.msisdnService = msisdnService;
    }

    @GetMapping("/")
    @ApiOperation(
            value = "Get all Msisdns Details",
            notes = "Get all registered, provisioned and de-provisioned. Terminated msisdns will not be included since once terminated relevant data is purged")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return list of msisdn details")
    })
    public List<MsisdnDetailSubscriptionDto> getMsisdns() {
        return msisdnService.getAllMsisdns();
    }

    @GetMapping("/{msisdn}")
    @ApiOperation(
            value = "Get specific Msisdn details",
            notes = "Get specific registered, provisioned and de-provisioned Msisdn. A terminated msisdn will not be included since once terminated relevant data is purged")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns a specific msisdn")
    })
    public MsisdnDetailSubscriptionDto getMsisdns(@PathVariable String msisdn) {
        return msisdnService.getMsisdn(msisdn);
    }

    @GetMapping("/entityId/{entityId}")
    @ApiOperation(
            value = "Get specific Msisdn details by EntityId",
            notes = "Get specific registered, provisioned and de-provisioned Msisdn. A terminated msisdn will not be included since once terminated relevant data is purged")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns a specific msisdn")
    })
    public MsisdnDetailSubscriptionDto getMsisdnsById(@PathVariable String entityId) {
        return msisdnService.getMsisdnById(entityId);
    }

    @GetMapping("/search/{search}")
    @ApiOperation(
            value = "Get list of Msisdns based on search",
            notes = "Get specific registred, providioned and deprovisioned Msisdns. A terminated msisdn will not be included since once terminated relevant data is purged")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns a list of matching msisdns")
    })
    public List<MsisdnDetailSubscriptionDto> SearchMsisdn(@PathVariable String search) {
        return msisdnService.searchMsisdn(search);
    }
}
