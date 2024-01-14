package com.syphan.bringglobal.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntitlementItemDto {

    @JsonProperty("entitlement_id")
    private String entitlementId;

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("bank_id")
    private String bankId;

    public String getEntitlementId() {
        return entitlementId;
    }

    public void setEntitlementId(String entitlementId) {
        this.entitlementId = entitlementId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "EntitlementItemDto{" +
                "entitlementId='" + entitlementId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", bankId='" + bankId + '\'' +
                '}';
    }
}
