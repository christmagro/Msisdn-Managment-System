package com.chris.mobile_subscribers.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterMsisdn extends MsisdnCommand {
    private static final long serialVersionUID = -751543552830202087L;

    public RegisterMsisdn(String msisdn) {
        super(msisdn);
    }
}
