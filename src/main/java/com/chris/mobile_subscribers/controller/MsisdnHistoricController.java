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
@RequestMapping("/event")
public class MsisdnHistoricController {

    private final MsisdnService<MsisdnDto, MsisdnSubscriptionDto, MsisdnDetailSubscriptionDto> msisdnService;

    public MsisdnHistoricController(MsisdnService<MsisdnDto, MsisdnSubscriptionDto, MsisdnDetailSubscriptionDto> msisdnService) {
        this.msisdnService = msisdnService;
    }

    @GetMapping("/msisdn/{msisdn}")
    @ApiOperation(
            value = "Get all event history for a specific msisdn",
            notes = "History will also include terminated details even if it was deleted from MsisdnDetail database table")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return list of events for a specific msisdn")
    })
    public List<Object> getEvents(@PathVariable String msisdn) {
        return msisdnService.getAllEventForMsisdn(msisdn);
    }

    @GetMapping("/entityId/{entityId}/msisdn/{msisdn}")
    @ApiOperation(
            value = "Get all event history for a specific entityId",
            notes = "History will also include terminated details even if it was purged")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return list of events for a specific entityId")
    })
    public List<Object> getEventsByEntityId(@PathVariable String entityId, @PathVariable String msisdn) {
        return msisdnService.getAllEventForEntityId(msisdn, entityId);
    }
}
