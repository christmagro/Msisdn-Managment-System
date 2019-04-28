package com.chris.mobile_subscribers.data.service.impl;

import com.chris.mobile_subscribers.data.MsisdnMappingData;
import com.chris.mobile_subscribers.data.service.DataService;
import com.chris.mobile_subscribers.dto.MsisdnDetailSubscriptionDto;
import com.chris.mobile_subscribers.exception.GlobalException;
import com.chris.mobile_subscribers.model.MsisdnDetail;
import com.chris.mobile_subscribers.model.ServiceStatus;
import com.chris.mobile_subscribers.repository.MsisdnDetailRepository;
import com.chris.mobile_subscribers.config.CustomObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    private final CustomObjectMapper mapper;

    private final MsisdnDetailRepository msisdnDetailRepository;

    public DataServiceImpl(CustomObjectMapper mapper, MsisdnDetailRepository msisdnDetailRepository) {
        this.mapper = mapper;
        this.msisdnDetailRepository = msisdnDetailRepository;
    }

    @Override
    @Transactional
    public void registerMsisdn(String id, String msisdn) {
        Instant instant = Instant.now();
        msisdnDetailRepository.save(MsisdnDetail.builder()
                .entityId(id).msisdn(msisdn)
                .serviceStatus(ServiceStatus.REGISTERED.name())
                .startDate(instant.toEpochMilli())
                .build());
    }

    @Override
    @Transactional
    public void createUpdateService(MsisdnMappingData msisdnMappingData) {
        MsisdnDetail msisdnDetail = msisdnDetailRepository.findById(msisdnMappingData.getId())
                .map(detail -> {
                    detail.setOwnerId(msisdnMappingData.getOwnerId());
                    detail.setUserId(msisdnMappingData.getUserId());
                    detail.setServiceStatus(ServiceStatus.PROVISIONED.name());
                    detail.setServiceType(msisdnMappingData.getServiceType());
                    return detail;
                })
                .orElseGet(() -> {
                    Instant instant = Instant.now();
                    return MsisdnDetail.builder()
                            .entityId(msisdnMappingData.getId())
                            .msisdn(msisdnMappingData.getMsisdn())
                            .ownerId(msisdnMappingData.getOwnerId())
                            .userId(msisdnMappingData.getUserId())
                            .serviceStatus(ServiceStatus.PROVISIONED.name())
                            .serviceType(msisdnMappingData.getServiceType())
                            .startDate(instant.toEpochMilli())
                            .build();
                });

        msisdnDetailRepository.save(msisdnDetail);
    }


    @Override
    @Transactional
    public void changeStatus(String id, ServiceStatus status) {
        MsisdnDetail msisdnDetail = msisdnDetailRepository.findById(id).orElseThrow(() -> new GlobalException("No Msisdn found for id " + id));
        msisdnDetail.setServiceStatus(status.name());
        msisdnDetailRepository.save(msisdnDetail);
    }

    @Override
    @Transactional
    public void updateOwner(String id, int ownerId, int userId) {
        MsisdnDetail msisdnDetail = msisdnDetailRepository.findById(id).orElseThrow(() -> new GlobalException("No Msisdn found for id " + id));
        if (ownerId != 0) {
            msisdnDetail.setOwnerId(ownerId);
        }
        if (userId != 0) {
            msisdnDetail.setUserId(userId);
        }
        msisdnDetailRepository.save(msisdnDetail);
    }

    @Override
    @Transactional
    public void changeService(String id, String serviceType) {
        MsisdnDetail msisdnDetail = msisdnDetailRepository.findById(id).orElseThrow(() -> new GlobalException("No Msisdn found for id " + id));
        msisdnDetail.setServiceType(serviceType);
        msisdnDetailRepository.save(msisdnDetail);
    }

    @Override
    @Transactional
    public void terminateService(String id) {
        msisdnDetailRepository.delete(msisdnDetailRepository.findById(id).orElseThrow(() -> new GlobalException("No Msisdn found for id " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MsisdnDetailSubscriptionDto> getAllMsisdns() {
        return mapper.mapAsList(msisdnDetailRepository.findAll(), MsisdnDetailSubscriptionDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public MsisdnDetailSubscriptionDto getMsisdnById(String id) {
        return mapper.map(msisdnDetailRepository.findById(id).orElseThrow(() -> new GlobalException("Id " + id + " not found")), MsisdnDetailSubscriptionDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public MsisdnDetailSubscriptionDto getMsisdn(String msisdn) {
        return mapper.map(msisdnDetailRepository.findMsisdnDetailByMsisdn(msisdn).orElseThrow(() -> new GlobalException("No Msisdn " + msisdn + " found")), MsisdnDetailSubscriptionDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MsisdnDetailSubscriptionDto> searchMsisdn(String searchMsisdn) {
        return mapper.mapAsList(msisdnDetailRepository.findMsisdnDetailByMsisdnContaining(searchMsisdn).orElseThrow(() -> new GlobalException("No matching Msisdn " + searchMsisdn + " found")), MsisdnDetailSubscriptionDto.class);
    }

}
