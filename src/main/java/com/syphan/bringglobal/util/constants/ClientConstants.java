package com.syphan.bringglobal.util.constants;

public class ClientConstants {

    public static final String HOST = "https://apisandbox.openbankproject.com";
    public static final String API_VERSION = "/obp/v5.1.0";

    // Authentication
    public static final String REQUEST_TOKEN_URL = HOST + "/oauth/initiate";
    public static final String ACCESS_TOKEN_URL = HOST + "/oauth/token";
    public static final String AUTHORIZE_URL = HOST + "/oauth/authorize";

    // General endpoints
    public static final String BASE_PATH = HOST + API_VERSION;
    public static final String CURRENT_USER = BASE_PATH + "/users/current";
    public static final String BANKS = BASE_PATH + "/my/banks/";

}
