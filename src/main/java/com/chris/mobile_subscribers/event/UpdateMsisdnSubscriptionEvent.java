package com.chris.mobile_subscribers.event;

import com.chris.mobile_subscribers.command.type.ServiceType;
import com.chris.mobile_subscribers.event.type.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateMsisdnSubscriptionEvent extends MsisdnEvent {

    private static final long serialVersionUID = -8527786307542814380L;
    private ServiceType serviceType;


    public UpdateMsisdnSubscriptionEvent(String msisdn, UUID entityId, ServiceType serviceType) {
        super(msisdn, entityId, EventType.UPDATE_TERM);
        this.serviceType = serviceType;
    }
}
