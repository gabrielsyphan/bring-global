package com.syphan.bringglobal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ITransactionService {

    void getTransactions(HttpServletRequest request, HttpServletResponse response) throws Exception;

    void getTransactionsByType(HttpServletRequest request, HttpServletResponse response) throws Exception;

    void getAmountByType(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
