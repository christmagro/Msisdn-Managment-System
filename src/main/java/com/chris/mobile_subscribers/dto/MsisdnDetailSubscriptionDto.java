package com.chris.mobile_subscribers.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class MsisdnDetailSubscriptionDto extends MsisdnSubscriptionDto {

    private String entityId;
    private String serviceStatus;
    private long startDate;
}
