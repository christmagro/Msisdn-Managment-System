package com.chris.mobile_subscribers.handling;

import com.chris.mobile_subscribers.data.MsisdnMappingData;
import com.chris.mobile_subscribers.data.service.DataService;
import com.chris.mobile_subscribers.dto.MsisdnDetailSubscriptionDto;
import com.chris.mobile_subscribers.event.*;
import com.chris.mobile_subscribers.event.query.FindAllMsisdns;
import com.chris.mobile_subscribers.event.query.FindMsisdn;
import com.chris.mobile_subscribers.event.query.FindMsisdnById;
import com.chris.mobile_subscribers.event.query.SearchMsisdn;
import com.chris.mobile_subscribers.model.ServiceStatus;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MsisdnEventHandler {

    private final DataService dataService;

    public MsisdnEventHandler(DataService dataService) {
        this.dataService = dataService;
    }

    @EventHandler
    public void on(RegisterMsisdnEvent event) {
        log.info("Registered msisdn {}", event.getMsisdn());
        dataService.registerMsisdn(event.getEntityId().toString(), event.getMsisdn());
    }

    @EventHandler
    public void on(ProvisionMsisdnEvent event) {
        log.info("Provisioning msisdn {}", event.getMsisdn());
        dataService.createUpdateService(MsisdnMappingData.builder()
                .id(event.getEntityId().toString())
                .msisdn(event.getMsisdn())
                .ownerId(event.getOwnerId())
                .userId(event.getUserId())
                .serviceType(event.getServiceType().name())
                .build());
    }

    @EventHandler
    public void on(DeProvisionMsisdnEvent event) {
        log.info("DeProvisioning msisdn {}", event.getMsisdn());
        dataService.changeStatus(event.getEntityId().toString(), ServiceStatus.DEPROVISIONED);
    }

    @EventHandler
    public void on(TerminateMsisdnEvent event) {
        log.info("Terminating msisdn {}", event.getMsisdn());
        dataService.terminateService(event.getEntityId().toString());
    }

    @EventHandler
    public void on(UpdateMsisdnSubscriptionEvent event) {
        log.info("Updating subscription for msisdn {}", event.getMsisdn());
        dataService.changeService(event.getEntityId().toString(), event.getServiceType().name());
    }

    @EventHandler
    public void on(UpdateOwnerEvent event) {
        log.info("Updating owner details for msisdn {}", event.getMsisdn());
        dataService.updateOwner(event.getEntityId().toString(), event.getOwnerId(), event.getUserId());

    }

    @QueryHandler
    public List<MsisdnDetailSubscriptionDto> handle(FindAllMsisdns findAllMsisdns) {
        return dataService.getAllMsisdns();
    }

    @QueryHandler
    public MsisdnDetailSubscriptionDto handle(FindMsisdn findMsisdn) {
        return dataService.getMsisdn(findMsisdn.getMsisdn());
    }

    @QueryHandler
    public MsisdnDetailSubscriptionDto handle(FindMsisdnById findMsisdnById) {
        return dataService.getMsisdnById(findMsisdnById.getId());
    }

    @QueryHandler
    public List<MsisdnDetailSubscriptionDto> handle(SearchMsisdn searchMsisdn) {
        return dataService.searchMsisdn(searchMsisdn.getSearchMsisdn());
    }

}