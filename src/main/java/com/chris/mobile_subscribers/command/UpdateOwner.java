package com.chris.mobile_subscribers.command;

import com.chris.mobile_subscribers.command.type.ServiceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateOwner extends MsisdnCommand {

    private static final long serialVersionUID = -2007020128841790117L;
    private int ownerId;
    private int userId;

    public UpdateOwner(String msisdn, int ownerId, int userId) {
        super(msisdn);
        this.ownerId = ownerId;
        this.userId = userId;
    }
}
