package com.chris.mobile_subscribers.service.impl;

import com.chris.mobile_subscribers.command.*;
import com.chris.mobile_subscribers.dto.MsisdnDetailSubscriptionDto;
import com.chris.mobile_subscribers.dto.MsisdnDto;
import com.chris.mobile_subscribers.dto.MsisdnSubscriptionDto;
import com.chris.mobile_subscribers.event.MsisdnEvent;
import com.chris.mobile_subscribers.event.query.FindAllMsisdns;
import com.chris.mobile_subscribers.event.query.FindMsisdn;
import com.chris.mobile_subscribers.event.query.FindMsisdnById;
import com.chris.mobile_subscribers.event.query.SearchMsisdn;
import com.chris.mobile_subscribers.exception.GlobalException;
import com.chris.mobile_subscribers.service.MsisdnService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class MsisdnServiceImpl implements MsisdnService<MsisdnDto, MsisdnSubscriptionDto, MsisdnDetailSubscriptionDto> {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public MsisdnServiceImpl(CommandGateway commandGateway, QueryGateway queryGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.eventStore = eventStore;
    }

    @Override
    public String registerMsisdn(MsisdnDto msisdnDto) {
        if (!eventStore.readEvents(msisdnDto.getMsisdn()).hasNext()) {
            return checkFuture(commandGateway.send(new RegisterMsisdn(msisdnDto.getMsisdn())));
        }
        throw new GlobalException("Msisdn already registered");
    }

    @Override
    public String provisionMsisdn(MsisdnSubscriptionDto msisdnSubscriptionDto) {
        checkIfRegistered(msisdnSubscriptionDto);
        return checkFuture(commandGateway.send(new ProvisionMsisdn(msisdnSubscriptionDto.getMsisdn(), msisdnSubscriptionDto.getOwnerId(), msisdnSubscriptionDto.getUserId(), msisdnSubscriptionDto.getServiceType())));
    }


    @Override
    public String deProvisionMsisdn(MsisdnSubscriptionDto msisdnSubscriptionDto) {
        checkIfRegistered(msisdnSubscriptionDto);
        return checkFuture(commandGateway.send(new DeProvisionMsisdn(msisdnSubscriptionDto.getMsisdn())));
    }

    @Override
    public String terminateMsisdn(MsisdnSubscriptionDto msisdnSubscriptionDto) {
        checkIfRegistered(msisdnSubscriptionDto);
        return checkFuture(commandGateway.send(new TerminateMsisdn(msisdnSubscriptionDto.getMsisdn())));
    }

    @Override
    public String updateSubscriptionMsisdn(MsisdnSubscriptionDto msisdnSubscriptionDto) {
        checkIfRegistered(msisdnSubscriptionDto);
        return checkFuture(commandGateway.send(new UpdateMsisdnSubscription(msisdnSubscriptionDto.getMsisdn(), msisdnSubscriptionDto.getServiceType())));
    }

    @Override
    public String updateOwner(MsisdnSubscriptionDto msisdnSubscriptionDto) {
        checkIfRegistered(msisdnSubscriptionDto);
        return checkFuture(commandGateway.send(new UpdateMsisdnSubscription(msisdnSubscriptionDto.getMsisdn(), msisdnSubscriptionDto.getServiceType())));
    }

    @Override
    public List<Object> getAllEventForMsisdn(String msisdn) {
        return eventStore.readEvents(msisdn)
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }

    @Override
    public List<Object> getAllEventForEntityId(String msisdn, String entityId) {
        return eventStore.readEvents(msisdn)
                .asStream()
                .map(Message::getPayload)
                .filter(t -> ((MsisdnEvent) t).getEntityId().toString().equals(entityId))
                .collect(Collectors.toList());
    }

    @Override
    public List<MsisdnDetailSubscriptionDto> getAllMsisdns() {
        return queryGateway.query(new FindAllMsisdns(), ResponseTypes.multipleInstancesOf(MsisdnDetailSubscriptionDto.class))
                .join();
    }

    @Override
    public MsisdnDetailSubscriptionDto getMsisdn(String msisdn) {
        return queryGateway.query(new FindMsisdn(msisdn), ResponseTypes.instanceOf(MsisdnDetailSubscriptionDto.class)).join();
    }

    @Override
    public MsisdnDetailSubscriptionDto getMsisdnById(String id) {
        return queryGateway.query(new FindMsisdnById(id), ResponseTypes.instanceOf(MsisdnDetailSubscriptionDto.class)).join();
    }

    @Override
    public List<MsisdnDetailSubscriptionDto> searchMsisdn(String searchValue) {
        return queryGateway.query(new SearchMsisdn(searchValue), ResponseTypes.multipleInstancesOf(MsisdnDetailSubscriptionDto.class))
                .join();
    }

    private <V, T extends CompletableFuture<V>> V checkFuture(T t) {
        try {
            return t.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new GlobalException(e.getCause().getMessage());
        }
    }

    private void checkIfRegistered(MsisdnSubscriptionDto msisdnSubscriptionDto) {
        if (!eventStore.readEvents(msisdnSubscriptionDto.getMsisdn()).hasNext()) {
            throw new GlobalException("Msisdn not yet registered");
        }
    }
}
