package com.syphan.bringglobal.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewItemDto {

    @JsonProperty("bank_id")
    private String bankId;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("view_id")
    private String viewId;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    @Override
    public String toString() {
        return "ViewItemDto{" +
                "bankId='" + bankId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", viewId='" + viewId + '\'' +
                '}';
    }
}