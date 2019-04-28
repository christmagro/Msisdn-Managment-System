package com.chris.mobile_subscribers.event;

import com.chris.mobile_subscribers.event.type.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeProvisionMsisdnEvent extends MsisdnEvent {
    private static final long serialVersionUID = 6974275876238500781L;

    public DeProvisionMsisdnEvent(String msisdn, UUID entityId) {
        super(msisdn, entityId, EventType.DEPROVISION);
    }
}
