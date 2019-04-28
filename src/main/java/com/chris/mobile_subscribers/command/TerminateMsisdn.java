package com.chris.mobile_subscribers.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TerminateMsisdn extends MsisdnCommand {
    private static final long serialVersionUID = -5579499608921192985L;

    public TerminateMsisdn(String msisdn) {
        super(msisdn);
    }
}
