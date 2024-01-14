package com.syphan.bringglobal.controller;

import com.syphan.bringglobal.model.dto.SimpleMessageDto;
import com.syphan.bringglobal.util.converter.JsonConverter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@WebServlet("/ErrorHandler")
public class ErrorHandlerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleError(request, response);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String message = "Something went wrong. Please try again later. Status code: " + statusCode +
                " Request URI: " + requestUri + ".";

        response.getWriter().write(JsonConverter.objToJson(new SimpleMessageDto(message)));
    }
}
