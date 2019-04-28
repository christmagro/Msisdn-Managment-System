package com.chris.mobile_subscribers.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

@Data
@AllArgsConstructor
public abstract class MsisdnCommand implements Serializable {

    private static final long serialVersionUID = 2365845156775012891L;

    @TargetAggregateIdentifier
    public final String msisdn;
}

