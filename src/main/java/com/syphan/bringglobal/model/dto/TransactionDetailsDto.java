package com.syphan.bringglobal.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDetailsDto {

    @JsonProperty("type")
    private String type;

    @JsonProperty("description")
    private String description;

    @JsonProperty("posted")
    private String posted;

    @JsonProperty("completed")
    private String completed;

    @JsonProperty("new_balance")
    private BalanceDto newBalance;

    @JsonProperty("value")
    private MoneyDto value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public BalanceDto getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BalanceDto newBalance) {
        this.newBalance = newBalance;
    }

    public MoneyDto getValue() {
        return value;
    }

    public void setValue(MoneyDto value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TransactionDetailsDto{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", posted='" + posted + '\'' +
                ", completed='" + completed + '\'' +
                ", newBalance=" + newBalance +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDetailsDto)) return false;

        TransactionDetailsDto that = (TransactionDetailsDto) o;

        if (!Objects.equals(type, that.type)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(posted, that.posted)) return false;
        if (!Objects.equals(completed, that.completed)) return false;
        if (!Objects.equals(newBalance, that.newBalance)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (posted != null ? posted.hashCode() : 0);
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        result = 31 * result + (newBalance != null ? newBalance.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
