package com.springlessons.clinicadmin.examples.types;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
// @Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // дополнительная информация
    @JdbcTypeCode(SqlTypes.STRUCT)
    private AdditionalInfo additionalInfo;

    // адрес выдачи
    @Convert(converter = AddressConverter.class)
    @Column(name = "issue_address")
    private Address address;

    // тип абонемента
    @Basic
    @Column(name = "subscription_code")
    private String subscriptionCode;

    @Basic
    @Column(name = "subscription_name")
    private String subscriptionName;

    @Transient
    private SubscriptionType subscriptionType;

    @PostLoad
    void fillTransient() {
        if (subscriptionCode != null) {
            this.subscriptionType = SubscriptionType.of(subscriptionCode);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (subscriptionType != null) {
            this.subscriptionCode = subscriptionType.getCode();
            this.subscriptionName = subscriptionType.getName();
        }
    }

}
