package com.chris.mobile_subscribers.data.service;

import com.chris.mobile_subscribers.data.MsisdnMappingData;
import com.chris.mobile_subscribers.dto.MsisdnDetailSubscriptionDto;
import com.chris.mobile_subscribers.model.ServiceStatus;

import java.util.List;

public interface DataService {


    void registerMsisdn(String id, String msisdn);

    void createUpdateService(MsisdnMappingData msisdnMappingData);

    void changeStatus(String id, ServiceStatus status);

    void updateOwner(String id, int ownerId, int userId);

    void changeService(String id, String serviceType);

    void terminateService(String id);

    List<MsisdnDetailSubscriptionDto> getAllMsisdns();

    MsisdnDetailSubscriptionDto getMsisdnById(String id);

    MsisdnDetailSubscriptionDto getMsisdn(String msisdn);

    List<MsisdnDetailSubscriptionDto> searchMsisdn(String msisdn);
}
