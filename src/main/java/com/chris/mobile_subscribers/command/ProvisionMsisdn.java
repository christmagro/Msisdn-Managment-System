package com.chris.mobile_subscribers.command;

import com.chris.mobile_subscribers.command.type.ServiceType;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProvisionMsisdn extends MsisdnCommand {

    private static final long serialVersionUID = 9013609826565524814L;
    private int ownerId;
    private int userId;
    private ServiceType serviceType;

    public ProvisionMsisdn(String msisdn, int ownerId, int userId, ServiceType serviceType) {
        super(msisdn);
        this.ownerId = ownerId;
        this.userId = userId;
        this.serviceType = serviceType;
    }
}
