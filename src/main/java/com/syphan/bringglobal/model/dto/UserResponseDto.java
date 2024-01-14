package com.syphan.bringglobal.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDto {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("provider_id")
    private String providerId;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("username")
    private String username;

    @JsonProperty("entitlements")
    private EntitlementsDto entitlements;

    @JsonProperty("views")
    private ViewsDto views;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EntitlementsDto getEntitlements() {
        return entitlements;
    }

    public void setEntitlements(EntitlementsDto entitlements) {
        this.entitlements = entitlements;
    }

    public ViewsDto getViews() {
        return views;
    }

    public void setViews(ViewsDto views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", providerId='" + providerId + '\'' +
                ", provider='" + provider + '\'' +
                ", username='" + username + '\'' +
                ", entitlements=" + entitlements +
                ", views=" + views +
                '}';
    }
}
