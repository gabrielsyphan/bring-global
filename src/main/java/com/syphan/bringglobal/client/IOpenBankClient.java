package com.syphan.bringglobal.client;

import com.syphan.bringglobal.model.dto.TransactionResponseDto;
import com.syphan.bringglobal.model.dto.UserResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface IOpenBankClient {

    String getAuthorizationUrl(HttpServletRequest request) throws Exception;
    String getAccessToken(String verifier, String oauth_token) throws Exception;
    UserResponseDto getUserInfo(String accessToken);
    TransactionResponseDto getTransactionsByBankId(String accessToken, String bankId, String userId);
}
