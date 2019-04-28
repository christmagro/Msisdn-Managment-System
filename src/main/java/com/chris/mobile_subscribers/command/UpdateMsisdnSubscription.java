package com.chris.mobile_subscribers.command;

import com.chris.mobile_subscribers.command.type.ServiceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateMsisdnSubscription extends MsisdnCommand {

    private static final long serialVersionUID = -2007020128841790117L;
    private ServiceType serviceType;

    public UpdateMsisdnSubscription(String msisdn, ServiceType serviceType) {
        super(msisdn);
        this.serviceType = serviceType;
    }
}
