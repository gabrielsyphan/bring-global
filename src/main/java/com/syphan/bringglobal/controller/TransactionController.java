package com.syphan.bringglobal.controller;

import com.syphan.bringglobal.client.OpenBankClient;
import com.syphan.bringglobal.model.dto.SimpleMessageDto;
import com.syphan.bringglobal.service.ITransactionService;
import com.syphan.bringglobal.service.TransactionService;
import com.syphan.bringglobal.util.constants.TransactionConstants;
import com.syphan.bringglobal.util.converter.JsonConverter;

import java.io.*;
import java.util.logging.Logger;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.core.MediaType;

@WebServlet(name = "transactionServlet", value = TransactionConstants.TRANSACTIONS_URI)
public class TransactionController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(TransactionController.class.getName());
    private final ITransactionService transactionService = new TransactionService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType(MediaType.APPLICATION_JSON);
            response.setCharacterEncoding("UTF-8");

            String verifierCode = request.getParameter("oauth_verifier");
            if (isValidVerifierCode(verifierCode)) {
                processAuthenticationByThird(request, response, verifierCode);
            } else if (isAuthenticated(request)) {
                processRequest(request, response);
            } else {
                authenticate(request, response);
            }
        } catch (Exception e) {
            this.logger.severe(e.getMessage());
            SimpleMessageDto simpleMessageDto = new SimpleMessageDto("Failed to get transactions. "+ e.getMessage());
            response.getWriter().write(JsonConverter.objToJson(simpleMessageDto));
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getRequestURI().contains(TransactionConstants.TRANSACTIONS_BY_TYPE)) {
            this.transactionService.getTransactionsByType(request, response);
        } else if (request.getRequestURI().contains(TransactionConstants.TOTAL_AMOUNT_BY_TYPE)) {
            this.transactionService.getAmountByType(request, response);
        } else {
            this.transactionService.getTransactions(request, response);
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authorizationUrl = getAuthorizationUrl(request);
        request.getSession().setAttribute("authorizationUrl", authorizationUrl);
        response.sendRedirect(authorizationUrl);
    }

    private void processAuthenticationByThird(HttpServletRequest request, HttpServletResponse response, String verifierCode) throws Exception {
        String oauthToken = request.getParameter("oauth_token");
        String accessToken = new OpenBankClient().getAccessToken(verifierCode, oauthToken);
        request.getSession().setAttribute("accessToken", accessToken);
        this.transactionService.getTransactions(request, response);
    }

    private static boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("accessToken") != null;
    }

    private static boolean isValidVerifierCode(String verifierCode) {
        return verifierCode != null && !verifierCode.isEmpty();
    }

    private String getAuthorizationUrl(HttpServletRequest request) throws Exception {
        return new OpenBankClient().getAuthorizationUrl(request);
    }
}