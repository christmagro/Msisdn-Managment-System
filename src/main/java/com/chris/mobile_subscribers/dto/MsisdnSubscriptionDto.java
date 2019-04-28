package com.chris.mobile_subscribers.dto;

import com.chris.mobile_subscribers.command.type.ServiceType;
import com.chris.mobile_subscribers.validation.ProvisionValidation;
import com.chris.mobile_subscribers.validation.UpdateTermValidation;
import com.chris.mobile_subscribers.validation.UpdateValidation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "Msisdn Subscription Object")
public class MsisdnSubscriptionDto extends MsisdnDto {
    @Min(value = 1, message = "OwnerId not provided", groups = {ProvisionValidation.class, UpdateValidation.class})
    private int ownerId;
    @Min(value = 1, message = "UserId not provided", groups = {ProvisionValidation.class, UpdateValidation.class})
    private int userId;
    @NotNull(message = "Service Type is required for provision", groups = {ProvisionValidation.class, UpdateTermValidation.class})
    @ApiModelProperty(dataType = "string", allowableValues = "MOBILE_POSTPAID, MOBILE_PREPAID", notes = "Defines if subscription is Prepaid or Postpaid")
    private ServiceType serviceType;
}
