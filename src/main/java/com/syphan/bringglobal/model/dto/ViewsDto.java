package com.syphan.bringglobal.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewsDto {

    @JsonProperty("list")
    private List<ViewItemDto> list;

    public List<ViewItemDto> getList() {
        return list;
    }

    public void setList(List<ViewItemDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ViewsDto{" +
                "list=" + list +
                '}';
    }
}
