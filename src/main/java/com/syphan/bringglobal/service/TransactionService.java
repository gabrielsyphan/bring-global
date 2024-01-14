package com.syphan.bringglobal.service;

import com.syphan.bringglobal.client.IOpenBankClient;
import com.syphan.bringglobal.client.OpenBankClient;
import com.syphan.bringglobal.model.dto.SimpleMessageDto;
import com.syphan.bringglobal.model.dto.TransactionItemDto;
import com.syphan.bringglobal.model.dto.TransactionResponseDto;
import com.syphan.bringglobal.model.dto.UserResponseDto;
import com.syphan.bringglobal.util.converter.JsonConverter;
import com.syphan.bringglobal.util.exceptions.ClientDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TransactionService implements ITransactionService {

    private static final Logger logger = Logger.getLogger(TransactionService.class.getName());
    private final IOpenBankClient openBankClient ;

    public TransactionService() {
        this.openBankClient = new OpenBankClient();
    }

    public TransactionService(IOpenBankClient openBankClient) {
        this.openBankClient = openBankClient;
    }

    @Override
    public void getTransactions(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            List<TransactionResponseDto> transactionResponse = getTransactionsFromUser(request);
            logger.fine("getTransactions() -> transactions: " + transactionResponse);

            List<TransactionItemDto> transactions = transactionResponse.stream()
                    .flatMap(transaction -> transaction.getTransactions().stream())
                    .collect(Collectors.toList());
            response.getWriter().write(JsonConverter.objToJson(transactions));
        } catch (ClientDataException cd) {
            response.getWriter().write(JsonConverter.objToJson(new SimpleMessageDto("No transactions found.")));
        }
    }

    @Override
    public void getTransactionsByType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<TransactionItemDto> transactions = validateTransactionsByType(request);
        response.getWriter().write(JsonConverter.objToJson(transactions));
    }

    @Override
    public void getAmountByType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<TransactionItemDto> transactions = validateTransactionsByType(request);
        Double amount = transactions.stream().mapToDouble(item -> {
                return Double.parseDouble(item.getDetails().getValue().getAmount());
            }).sum();

        logger.fine("getAmountByType() -> amount: " + amount);
        response.getWriter().write(JsonConverter.objToJson(amount));
    }

    private List<TransactionItemDto> validateTransactionsByType(HttpServletRequest request) throws Exception {
        List<TransactionItemDto> transactions = new ArrayList<>();
        try {
            String transactionType = request.getParameter("type");
            List<TransactionResponseDto> userTransactions = getTransactionsFromUser(request);
            List<TransactionItemDto> transactionItems = userTransactions.stream()
                    .flatMap(transaction -> transaction.getTransactions().stream())
                    .collect(Collectors.toList());


            logger.fine("validateTransactionsByType() -> transactionType: " + transactionType);
            transactionItems.forEach(item -> {
                boolean sameType = Objects.equals(item.getDetails().getType(), transactionType);

                if(sameType) {
                    transactions.add(item);
                }
            });
        } catch (Exception e) {
            throw new Exception(e);
        }
        return transactions;
    }

    private List<TransactionResponseDto> getTransactionsFromUser(HttpServletRequest request) throws Exception {
        try {
            String accessToken = (String) request.getSession().getAttribute("accessToken");
            UserResponseDto user = openBankClient.getUserInfo(accessToken);

            logger.fine("getTransactionsFromUser() -> user: " + user);

            if(user == null) {
                throw new ClientDataException("User not found");
            }

            if(itHasElements(user)) {
                throw new ClientDataException("User has no entitlements");
            }

            return getTransactionsFromUser(user, accessToken);
        } catch (ClientDataException e) {
            String message = "getTransactions() -> ClientDataException: " + e.getMessage();
            logger.warning(message);
            throw new ClientDataException(message);
        } catch (Exception e) {
            String message = "getTransactions() -> Exception: " + e.getMessage();
            logger.severe(message);
            throw new Exception(message);
        }
    }

    private List<TransactionResponseDto> getTransactionsFromUser(UserResponseDto user, String accessToken) {
        return user.getEntitlements().getList().stream()
                .map(entitlementItemDto -> openBankClient.getTransactionsByBankId(
                        accessToken,
                        entitlementItemDto.getBankId(),
                        user.getUserId()
                )).collect(Collectors.toList());
    }

    private static boolean itHasElements(UserResponseDto user) {
        boolean hasElements = user.getEntitlements().getList() == null || user.getEntitlements().getList().isEmpty();
        logger.fine("itHasElements() -> hasElements: " + hasElements);
        return hasElements;
    }
}
