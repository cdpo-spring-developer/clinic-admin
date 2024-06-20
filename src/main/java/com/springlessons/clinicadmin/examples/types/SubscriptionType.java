package com.springlessons.clinicadmin.examples.types;

import jakarta.persistence.Entity;

public enum SubscriptionType {
    ONCE("once"), WEEKLY("weekly"), MONTHLY("monthly");

    private String name;
    private String code;

    SubscriptionType(String code) {
        this.code = code;
    }

    public static SubscriptionType of(String subscriptionCode) {
        for (SubscriptionType type : SubscriptionType.values()) {
            if (type.code.equals(subscriptionCode)) return type;
        }
        throw new IllegalArgumentException("Invalid subscription code: " + subscriptionCode);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
