package com.chris.mobile_subscribers.repository;

import com.chris.mobile_subscribers.model.MsisdnDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MsisdnDetailRepository extends JpaRepository<MsisdnDetail, String> {

    Optional<MsisdnDetail> findMsisdnDetailByMsisdn(String msisdn);

    Optional<List<MsisdnDetail>> findMsisdnDetailByMsisdnContaining(String msisdn);
}
