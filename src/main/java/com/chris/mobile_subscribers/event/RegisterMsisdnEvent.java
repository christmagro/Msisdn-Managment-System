package com.chris.mobile_subscribers.event;

import com.chris.mobile_subscribers.event.type.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterMsisdnEvent extends MsisdnEvent {
    private static final long serialVersionUID = 7395309302041540873L;

    public RegisterMsisdnEvent(String msisdn, UUID entityId) {
        super(msisdn, entityId, EventType.REGISTER);
    }
}
