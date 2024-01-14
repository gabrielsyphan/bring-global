package com.syphan.bringglobal.service;

import com.syphan.bringglobal.client.OpenBankClient;
import com.syphan.bringglobal.model.dto.*;
import com.syphan.bringglobal.util.builder.ObjectBuilderUtil;
import com.syphan.bringglobal.util.converter.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private OpenBankClient openBankClient;
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() throws IOException {
        request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        when(session.getAttribute("accessToken")).thenReturn("abcde");
        when(request.getSession()).thenReturn(session);

        response = Mockito.mock(HttpServletResponse.class);
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        doNothing().when(printWriter).write(anyString());
        when(response.getWriter()).thenReturn(printWriter);

        openBankClient = Mockito.mock(OpenBankClient.class);
        transactionService = new TransactionService(openBankClient);
    }

    @Test
    public void getTransactionsFromUserButWithoutEntitlements() {
        try {
            when(openBankClient.getUserInfo(anyString())).thenReturn(ObjectBuilderUtil.buildUserResponseDto(false));
            transactionService.getTransactions(request, response);
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void getTransactionsFromUser() {
        try {
            TransactionResponseDto transactionResponseDto = ObjectBuilderUtil.buildTransactionResponseDto();
            when(openBankClient.getTransactionsByBankId(anyString(), anyString(), anyString())).thenReturn(transactionResponseDto);
            when(openBankClient.getUserInfo(anyString())).thenReturn(ObjectBuilderUtil.buildUserResponseDto(true));
            transactionService.getTransactions(request, response);

            List<TransactionItemDto> mockResponse = new ArrayList<>();
            mockResponse.addAll(transactionResponseDto.getTransactions());
            mockResponse.addAll(transactionResponseDto.getTransactions());
            mockResponse.addAll(transactionResponseDto.getTransactions());

            verify(response.getWriter(), times(1)).write(
                    JsonConverter.objToJson(mockResponse)
            );
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void getTransactionsByType() {
        try {
            setUpTransactionByType();
            transactionService.getTransactionsByType(request, response);
            List<TransactionItemDto> items = ObjectBuilderUtil.buildTransactionByTypeSandbox();
            verify(response.getWriter(), times(1)).write(
                    JsonConverter.objToJson(items)
            );
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void getAmountByType() {
        try {
            setUpTransactionByType();
            transactionService.getAmountByType(request, response);
            double amount = 0.0;
            for (TransactionItemDto item : ObjectBuilderUtil.buildTransactionByTypeSandbox()) {
                amount += Double.parseDouble(item.getDetails().getValue().getAmount());
            }

            verify(response.getWriter(), times(1)).write(
                    JsonConverter.objToJson(amount)
            );
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    private void setUpTransactionByType() {
        when(openBankClient.getTransactionsByBankId(anyString(), anyString(), anyString())).thenReturn(
                ObjectBuilderUtil.buildTransactionResponseDto()
        );
        when(openBankClient.getUserInfo(anyString())).thenReturn(ObjectBuilderUtil.buildUserResponseDto(true));
        when(request.getParameter("type")).thenReturn("sandbox-payment");
    }
}
