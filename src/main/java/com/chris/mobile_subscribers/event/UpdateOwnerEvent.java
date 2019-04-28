package com.chris.mobile_subscribers.event;

import com.chris.mobile_subscribers.event.type.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateOwnerEvent extends MsisdnEvent {

    private static final long serialVersionUID = 5588402708270167590L;
    private int ownerId;
    private int userId;

    public UpdateOwnerEvent(String msisdn, UUID entityId, int ownerId, int userId) {
        super(msisdn, entityId, EventType.UPDATE_OWNER);
        this.ownerId = ownerId;
        this.userId = userId;
    }
}
