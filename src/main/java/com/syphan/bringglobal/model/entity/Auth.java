package com.syphan.bringglobal.model.entity;

public class Auth {

    private Integer id;
    private String oauthToken;
    private String oauthTokenSecret;

    public Auth() {}

    public Auth(Integer id, String oauthToken, String oauthTokenSecret) {
        this.id = id;
        this.oauthToken = oauthToken;
        this.oauthTokenSecret = oauthTokenSecret;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public String getOauthTokenSecret() {
        return oauthTokenSecret;
    }

    public void setOauthTokenSecret(String oauthTokenSecret) {
        this.oauthTokenSecret = oauthTokenSecret;
    }
}
