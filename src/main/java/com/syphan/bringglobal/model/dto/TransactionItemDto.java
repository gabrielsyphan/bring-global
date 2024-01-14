package com.syphan.bringglobal.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionItemDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("details")
    private TransactionDetailsDto details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransactionDetailsDto getDetails() {
        return details;
    }

    public void setDetails(TransactionDetailsDto details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "TransactionItemDto{" +
                "id='" + id + '\'' +
                ", details=" + details +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionItemDto)) return false;

        TransactionItemDto that = (TransactionItemDto) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }
}
