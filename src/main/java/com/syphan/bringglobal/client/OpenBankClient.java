package com.syphan.bringglobal.client;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.syphan.bringglobal.model.dto.TransactionResponseDto;
import com.syphan.bringglobal.model.dto.UserResponseDto;
import com.syphan.bringglobal.model.entity.Auth;
import com.syphan.bringglobal.dao.AuthDAO;
import com.syphan.bringglobal.util.constants.ClientConstants;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

import java.util.Objects;
import java.util.logging.Logger;

public class OpenBankClient implements IOpenBankClient {

    private final Logger logger = Logger.getLogger(OpenBankClient.class.getName());

    private final String consumerKey;
    private final String consumerSecret;

    public OpenBankClient() {
        consumerKey = System.getenv("CONSUMER_KEY");
        consumerSecret = System.getenv("CONSUMER_SECRET");
    }

    public String getAuthorizationUrl(HttpServletRequest request) throws Exception {
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        OAuthProvider provider = new CommonsHttpOAuthProvider(
                ClientConstants.REQUEST_TOKEN_URL,
                ClientConstants.ACCESS_TOKEN_URL,
                ClientConstants.AUTHORIZE_URL
        );
        provider.setOAuth10a(true);

        String callback = "http://localhost" + request.getRequestURI();
        String authUrl = provider.retrieveRequestToken(consumer, callback);

        if(consumer.getTokenSecret() != null) {
            AuthDAO authDAO = new AuthDAO();
            authDAO.saveToken(consumer.getToken(), consumer.getTokenSecret());
        }

        return authUrl;
    }

    public String getAccessToken(String verifier, String oauth_token) throws Exception {
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);

        Auth auth = new AuthDAO().getAuthByToken(oauth_token);
        consumer.setTokenWithSecret(auth.getOauthToken(), auth.getOauthTokenSecret());

        OAuthProvider provider = new CommonsHttpOAuthProvider(
                ClientConstants.REQUEST_TOKEN_URL,
                ClientConstants.ACCESS_TOKEN_URL,
                ClientConstants.AUTHORIZE_URL
        );
        provider.setOAuth10a(true);
        provider.retrieveAccessToken(consumer, verifier);

        return consumer.getToken();
    }

    public UserResponseDto getUserInfo(String accessToken) {
        try (Response response = doGetRequest(accessToken, ClientConstants.CURRENT_USER)) {
            return Objects.requireNonNull(response).readEntity(UserResponseDto.class);
        } catch (Exception e) {
            this.logger.severe("getUserInfo(): failed to get user info. " + e.getMessage());
            return null;
        }
    }

    public TransactionResponseDto getTransactionsByBankId(String accessToken, String bankId, String userId) {
        try (Response response = doGetRequest(accessToken, ClientConstants.BANKS + bankId + "/accounts/" + userId + "/transactions")) {
            return Objects.requireNonNull(response).readEntity(TransactionResponseDto.class);
        } catch (Exception e) {
            this.logger.severe("getTransactionsByBankId(): failed to get transactions. " + e.getMessage());
            return null;
        }
    }

    private Response doGetRequest(String accessToken, String uri) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(uri);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "DirectLogin token=\"" + accessToken + "\"");
        Response response = builder.get();

        client.close();

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            this.logger.severe("doGetRequest(): failed to get transactions. Status code: " + response.getStatus());
            return null;
        }

        return response;
    }
}
