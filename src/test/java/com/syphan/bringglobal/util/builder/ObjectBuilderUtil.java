package com.syphan.bringglobal.util.builder;

import com.syphan.bringglobal.model.dto.*;

import java.util.ArrayList;
import java.util.List;

public class ObjectBuilderUtil {

    public static TransactionResponseDto buildTransactionResponseDto() {
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setTransactions(buildTransactionItemListDto());
        return transactionResponseDto;
    }

    public static List<TransactionItemDto> buildTransactionItemListDto() {
        List<TransactionItemDto> transactionItemDtos = new ArrayList<>();
        transactionItemDtos.add(buildTransactionItemDto("1", "test"));
        transactionItemDtos.add(buildTransactionItemDto("2", "sandbox-payment"));
        transactionItemDtos.add(buildTransactionItemDto("3", "sandbox-payment"));
        return transactionItemDtos;
    }

    public static TransactionItemDto buildTransactionItemDto(String id, String type) {
        TransactionItemDto transactionItemDto = new TransactionItemDto();
        transactionItemDto.setId(id);
        transactionItemDto.setDetails(buildTransactionDetailsDto(type));
        return transactionItemDto;
    }

    public static TransactionDetailsDto buildTransactionDetailsDto(String type) {
        TransactionDetailsDto transactionDetailsDto = new TransactionDetailsDto();
        transactionDetailsDto.setType(type);
        transactionDetailsDto.setCompleted("Completed test");
        transactionDetailsDto.setDescription("Description test");
        transactionDetailsDto.setPosted("Posted test");
        transactionDetailsDto.setNewBalance(buildNewBalanceDto());
        transactionDetailsDto.setValue(buildMoneyDto());
        return transactionDetailsDto;
    }

    public static MoneyDto buildMoneyDto() {
        MoneyDto moneyDto = new MoneyDto();
        moneyDto.setAmount("10");
        moneyDto.setCurrency("15");
        return moneyDto;
    }

    public static BalanceDto buildNewBalanceDto() {
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setAmount("2");
        balanceDto.setCurrency("Currency test");
        return balanceDto;
    }

    public static UserResponseDto buildUserResponseDto(boolean withEntitlements) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId("123456");
        userResponseDto.setEmail("Email example");
        userResponseDto.setProvider("Provider test");
        userResponseDto.setUsername("Username test");
        userResponseDto.setViews(new ViewsDto());
        userResponseDto.setEntitlements(withEntitlements ? buildEntitlements() : new EntitlementsDto());
        return userResponseDto;
    }

    public static EntitlementsDto buildEntitlements() {
        EntitlementsDto entitlementsDto = new EntitlementsDto();
        entitlementsDto.setList(buildEntitlementItemListDto());
        return entitlementsDto;
    }

    public static List<EntitlementItemDto> buildEntitlementItemListDto() {
        List<EntitlementItemDto> entitlementItemDtos = new ArrayList<>();
        entitlementItemDtos.add(buildEntitlementItemDto("1", "19823", "role name 1"));
        entitlementItemDtos.add(buildEntitlementItemDto("2", "214", "role name 2"));
        entitlementItemDtos.add(buildEntitlementItemDto("3", "9981", "role name 3"));
        return entitlementItemDtos;
    }

    public static EntitlementItemDto buildEntitlementItemDto(String entitlementId, String bankId, String roleName) {
        EntitlementItemDto entitlementItemDto = new EntitlementItemDto();
        entitlementItemDto.setEntitlementId(entitlementId);
        entitlementItemDto.setBankId(bankId);
        entitlementItemDto.setRoleName(roleName);
        return entitlementItemDto;
    }

    public static List<TransactionItemDto> buildTransactionByTypeSandbox() {
        List<TransactionItemDto> items = new ArrayList<>();

        List<TransactionItemDto> itemsByType = ObjectBuilderUtil.buildTransactionItemListDto();
        itemsByType.remove(0);

        items.addAll(itemsByType);
        items.addAll(itemsByType);
        items.addAll(itemsByType);

        return items;
    }
}
