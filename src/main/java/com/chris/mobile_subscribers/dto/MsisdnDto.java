package com.chris.mobile_subscribers.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(value = "Msisdn Request Object", description = "Should contain a local Msisdn in E164 format such as 35679417658.")
public class MsisdnDto {
    @NotNull
    @Pattern(message = "provided msisdn does not conform with local E164 format", regexp = "^(35677\\d{6})|(35679(0\\d{5}|1([0-1]\\d{4}|[3-4]\\d{4}|[7-9]\\d{4})|[2-9]\\d{5}))|(969[6-7]\\d{4})|(35698((1[1-3]\\d{4})|(89\\d{4})|(35697\\d{4})))|(35699\\d{6})$")
    private String msisdn;


}
