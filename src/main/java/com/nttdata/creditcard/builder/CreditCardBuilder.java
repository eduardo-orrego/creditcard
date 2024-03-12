package com.nttdata.creditcard.builder;

import com.nttdata.creditcard.api.request.CreditCardRequest;
import com.nttdata.creditcard.model.CreditCard;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CreditCardBuilder {
    CreditCardBuilder() {
    }

    public static CreditCard toCreditCardEntity(CreditCardRequest creditCardRequest, String creditCardId) {
        return CreditCard.builder()
            .id(creditCardId)
            .cardNumber(Objects.nonNull(creditCardRequest.getCardNumber())
                ? creditCardRequest.getCardNumber()
                : generateCardNumber())
            .status(creditCardRequest.getStatus().name())
            .expirationDate(creditCardRequest.getExpirationDate())
            .activateDate(creditCardRequest.getActivateDate())
            .cvv(creditCardRequest.getCvv())
            .customerId(creditCardRequest.getCustomerId())
            .interestRate(Objects.nonNull(creditCardRequest.getInterestRate())
                ? creditCardRequest.getInterestRate()
                : BigDecimal.valueOf(0.00))
            .availableBalance(Objects.nonNull(creditCardRequest.getAvailableBalance())
                ? creditCardRequest.getAvailableBalance()
                : BigDecimal.valueOf(0.00))
            .creditLimit(creditCardRequest.getCreditLimit())
            .lastTransactionDate(LocalDateTime.now())
            .dateCreated(LocalDateTime.now())
            .lastUpdated(LocalDateTime.now())
            .build();
    }

    private static BigInteger generateCardNumber() {
        String cardNumber = "20".concat(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        return new BigInteger(cardNumber);
    }
}
