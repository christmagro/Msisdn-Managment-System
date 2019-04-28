package com.chris.mobile_subscribers.data;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MsisdnMappingData implements Serializable {

    private static final long serialVersionUID = -1863609121189655978L;
    public String msisdn;
    public String id;
    private int ownerId;
    private int userId;
    private String serviceType;
    private String serviceStatus;
    private long startDate;
}
