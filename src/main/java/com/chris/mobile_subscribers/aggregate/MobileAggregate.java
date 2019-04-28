package com.chris.mobile_subscribers.aggregate;

import com.chris.mobile_subscribers.command.*;
import com.chris.mobile_subscribers.command.type.ServiceType;
import com.chris.mobile_subscribers.command.type.Status;
import com.chris.mobile_subscribers.event.*;
import com.chris.mobile_subscribers.exception.MsisdnAlreadyDeProvisioned;
import com.chris.mobile_subscribers.exception.MsisdnAlreadyProvisioned;
import com.chris.mobile_subscribers.exception.MsisdnAlreadyTerminated;
import com.chris.mobile_subscribers.exception.MsisdnNotProvisioned;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Slf4j
public class MobileAggregate {


    @AggregateIdentifier
    private String msisdn;
    private UUID entityId;
    private Status status;
    private ServiceType serviceType;


    @CommandHandler
    public MobileAggregate(final RegisterMsisdn command) {
        this.msisdn = command.getMsisdn();
        this.entityId = UUID.randomUUID();
        AggregateLifecycle.apply(new RegisterMsisdnEvent(command.getMsisdn(), entityId));
    }

    @CommandHandler
    public void handle(final ProvisionMsisdn command) throws MsisdnAlreadyProvisioned {
        if (status.equals(Status.ASSIGNED)) {
            log.error("Msisdn {} is already {} provision cannot be completed", command.msisdn, status);
            throw new MsisdnAlreadyProvisioned("MSISDN Already Provisioned to another customer");
        }
        if (this.status.equals(Status.TERMINATED)) {
            this.entityId = UUID.randomUUID();
        }
        AggregateLifecycle.apply(new ProvisionMsisdnEvent(command.getMsisdn(), entityId, command.getOwnerId(), command.getUserId(), command.getServiceType()));
    }

    @CommandHandler
    public void handle(final DeProvisionMsisdn command) {
        if (!status.equals(Status.ASSIGNED)) {
            log.error(" Msisdn {} is already {} no change is required", command.msisdn, status);
            throw new MsisdnAlreadyDeProvisioned("MSISDN Already not provisioned");
        }
        AggregateLifecycle.apply(new DeProvisionMsisdnEvent(command.getMsisdn(), entityId));
    }

    @CommandHandler
    public void handle(final UpdateMsisdnSubscription command) {
        if (!status.equals(Status.ASSIGNED)) {
            log.error("Subscriber with Msisdn {} is not available", command.msisdn);
            throw new MsisdnNotProvisioned("MSISDN Not Provisioned");
        }
        if (serviceType.equals(command.getServiceType())) {
            log.error("Subscriber with Msisdn {} is already on {} no change is required", command.msisdn, command.getServiceType().name());
        }
        AggregateLifecycle.apply(new UpdateMsisdnSubscriptionEvent(command.getMsisdn(), entityId, command.getServiceType()));
    }

    @CommandHandler
    public void handle(final UpdateOwner command) {
        if (!status.equals(Status.ASSIGNED)) {
            log.error("Subscriber with Msisdn {} is not available", command.msisdn);
            throw new MsisdnNotProvisioned("MSISDN Not Provisioned");
        }

        AggregateLifecycle.apply(new UpdateOwnerEvent(command.getMsisdn(), entityId, command.getOwnerId(), command.getUserId()));
    }

    @CommandHandler
    public void handle(final TerminateMsisdn command) {
        if (status.equals(Status.TERMINATED)) {
            log.error("Msisdn {} is already {} no action required ", command.msisdn, status);
            throw new MsisdnAlreadyTerminated("MSISDN Already Terminated");
        }
        AggregateLifecycle.apply(new TerminateMsisdnEvent(command.getMsisdn(), entityId));
    }

    @EventSourcingHandler
    public void on(RegisterMsisdnEvent event) {
        this.msisdn = event.getMsisdn();
        this.status = Status.UNASSIGNED;
        this.entityId = event.entityId;
    }

    @EventSourcingHandler
    public void on(ProvisionMsisdnEvent event) {
        this.status = Status.ASSIGNED;
        this.serviceType = event.getServiceType();
        this.entityId = event.entityId;
    }

    @EventSourcingHandler
    public void on(DeProvisionMsisdnEvent event) {
        this.status = Status.UNASSIGNED;
        this.serviceType = null;
        this.entityId = event.entityId;
    }

    @EventSourcingHandler
    public void on(UpdateMsisdnSubscriptionEvent event) {
        this.serviceType = event.getServiceType();
    }

    @EventSourcingHandler
    public void on(TerminateMsisdnEvent event) {
        this.status = Status.TERMINATED;
        this.serviceType = null;
    }


}


