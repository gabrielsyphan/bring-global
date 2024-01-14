package com.syphan.bringglobal.dao;

import com.syphan.bringglobal.model.entity.Auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AuthDAO {

    private final Logger logger = Logger.getLogger(AuthDAO.class.getName());

    public void saveToken(String oauthToken, String oauthTokenSecret) throws SQLException {
        try {
            Connection connection = DbConnection.getConnection();
            String query = "INSERT INTO auth_manager (oauth_token, oauth_token_secret) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, oauthToken);
            preparedStatement.setString(2, oauthTokenSecret);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.severe("saveToken() -> SQLException: " + e.getMessage());
            throw new SQLException(e);
        }
    }

    public Auth getAuthByToken(String oauthToken) throws SQLException {
        Auth auth = null;
        try {
            Connection connection = DbConnection.getConnection();
            String query = "SELECT * FROM auth_manager WHERE oauth_token = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, oauthToken);
            preparedStatement.execute();

            if (preparedStatement.getResultSet().next()) {
                auth = new Auth();
                auth.setId(preparedStatement.getResultSet().getInt("id"));
                auth.setOauthToken(preparedStatement.getResultSet().getString("oauth_token"));
                auth.setOauthTokenSecret(preparedStatement.getResultSet().getString("oauth_token_secret"));
                return auth;
            }
        } catch (SQLException e) {
            logger.severe("getAuthByToken() -> SQLException: " + e.getMessage());
            throw new SQLException(e);
        }
        return null;
    }
}
