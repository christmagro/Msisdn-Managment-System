package com.chris.mobile_subscribers.command;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeProvisionMsisdn extends MsisdnCommand {
    private static final long serialVersionUID = -2289956637716557444L;

    public DeProvisionMsisdn(String msisdn) {
        super(msisdn);
    }
}
