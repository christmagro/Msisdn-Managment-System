package com.chris.mobile_subscribers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "msisdn_detail")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MsisdnDetail {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String entityId;


    @Basic
    @Column(name = "msisdn", length = 16, nullable = false)
    private String msisdn;

    @Basic
    @Column(name = "customer_id_owner", length = 11)
    private int ownerId;

    @Basic
    @Column(name = "customer_id_user", length = 11)
    private int userId;

    @Basic
    @Column(name = "service_type")
    private String serviceType;

    @Basic
    @Column(name = "service_status")
    private String serviceStatus;

    @Basic
    @Column(name = "service_start_date")
    private long startDate;
}