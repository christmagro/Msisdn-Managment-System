package com.chris.mobile_subscribers.event;

import com.chris.mobile_subscribers.event.type.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class TerminateMsisdnEvent extends MsisdnEvent {
    private static final long serialVersionUID = 6173942320100250690L;

    public TerminateMsisdnEvent(String msisdn, UUID entityId) {
        super(msisdn, entityId, EventType.TERMINATE);
    }
}
