package com.chris.mobile_subscribers.event;

import com.chris.mobile_subscribers.command.type.ServiceType;
import com.chris.mobile_subscribers.event.type.EventType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProvisionMsisdnEvent extends MsisdnEvent {

    private static final long serialVersionUID = 7239636623631823391L;
    private int ownerId;
    private int userId;
    private ServiceType serviceType;

    public ProvisionMsisdnEvent(String msisdn, UUID uuid, int ownerId, int userId, ServiceType serviceType) {
        super(msisdn, uuid, EventType.PROVISION);
        this.ownerId = ownerId;
        this.userId = userId;
        this.serviceType = serviceType;
    }
}
