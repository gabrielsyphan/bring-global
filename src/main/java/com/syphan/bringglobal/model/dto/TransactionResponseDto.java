package com.syphan.bringglobal.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponseDto {

    @JsonProperty("transactions")
    private List<TransactionItemDto> transactions;

    public List<TransactionItemDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionItemDto> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "TransactionResponseDto{" +
                "transactions=" + transactions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionResponseDto)) return false;

        TransactionResponseDto that = (TransactionResponseDto) o;

        return Objects.equals(transactions, that.transactions);
    }

    @Override
    public int hashCode() {
        return transactions != null ? transactions.hashCode() : 0;
    }
}
