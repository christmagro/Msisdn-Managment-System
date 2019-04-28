package com.chris.mobile_subscribers.event;

import com.chris.mobile_subscribers.event.type.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public abstract class MsisdnEvent implements Serializable {

    private static final long serialVersionUID = -4039223032014085506L;

    public final String msisdn;

    public final UUID entityId;

    public EventType eventType;

}

