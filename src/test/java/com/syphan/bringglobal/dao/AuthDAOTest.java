package com.syphan.bringglobal.dao;

import com.syphan.bringglobal.model.entity.Auth;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthDAOTest {

    private final AuthDAO authDAO = new AuthDAO();

    @Test
    public void saveAuth() {
        try {
            String oauthToken = "oauthToken-save-teste";
            String oauthTokenSecret = "oauthTokenSecret-save-teste";
            authDAO.saveToken(oauthToken, oauthTokenSecret);
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void selectAuth() {
        try {
            String oauthToken = "oauthToken-select-teste";
            String oauthTokenSecret = "oauthTokenSecret-select-teste";
            authDAO.saveToken(oauthToken, oauthTokenSecret);

            Auth auth = authDAO.getAuthByToken(oauthToken);
            assertNotNull(auth);
            assertNotNull(auth.getId());
            assertTrue(auth.getId() > 0);
            assertEquals(oauthToken, auth.getOauthToken());
            assertEquals(oauthTokenSecret, auth.getOauthTokenSecret());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
