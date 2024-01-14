package com.syphan.bringglobal.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntitlementsDto {

    @JsonProperty("list")
    private List<EntitlementItemDto> list;

    public List<EntitlementItemDto> getList() {
        return list;
    }

    public void setList(List<EntitlementItemDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "EntitlementsDto{" +
                "list=" + list +
                '}';
    }
}
