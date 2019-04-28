package com.chris.mobile_subscribers.service;

import com.chris.mobile_subscribers.dto.MsisdnDetailSubscriptionDto;
import com.chris.mobile_subscribers.dto.MsisdnSubscriptionDto;

import java.util.List;

public interface MsisdnService<MsisdnDto, MsisdnSubscriptionDto, MsisdnDetailSubscriptionDto> {

    String registerMsisdn(MsisdnDto msisdnDto);

    String provisionMsisdn(MsisdnSubscriptionDto msisdnSubscriptionDto);

    String deProvisionMsisdn(MsisdnSubscriptionDto msisdnSubscriptionDto);

    String terminateMsisdn(MsisdnSubscriptionDto msisdnSubscriptionDto);

    String updateSubscriptionMsisdn(MsisdnSubscriptionDto msisdnSubscriptionDto);

    String updateOwner(com.chris.mobile_subscribers.dto.MsisdnSubscriptionDto msisdnSubscriptionDto);

    List<Object> getAllEventForMsisdn(String msisdn);

    List<Object> getAllEventForEntityId(String msisdn, String entityId);

    List<MsisdnDetailSubscriptionDto> getAllMsisdns();

    MsisdnDetailSubscriptionDto getMsisdn(String msisdn);

    MsisdnDetailSubscriptionDto getMsisdnById(String id);

    List<MsisdnDetailSubscriptionDto> searchMsisdn(String searchValue);

}
