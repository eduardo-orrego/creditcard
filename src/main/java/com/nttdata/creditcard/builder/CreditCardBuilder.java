package com.nttdata.creditcard.builder;

import com.nttdata.creditcard.api.request.CreditCardRequest;
import com.nttdata.creditcard.model.CreditCard;
import java.time.LocalDateTime;

public class CreditCardBuilder {
    CreditCardBuilder() {
    }

    public static CreditCard toCreditCardEntity(CreditCardRequest creditCardRequest, String creditCardId) {
        return CreditCard.builder()
            .id(creditCardId)
            .cardNumber(creditCardRequest.getCardNumber())
            .status(creditCardRequest.getStatus().name())
            .expirationDate(creditCardRequest.getExpirationDate())
            .activateDate(creditCardRequest.getActivateDate())
            .cvv(creditCardRequest.getCvv())
            .customerId(creditCardRequest.getCustomerId())
            .availableBalance(creditCardRequest.getAvailableBalance())
            .creditLimit(creditCardRequest.getCreditLimit())
            .lastTransactionDate(LocalDateTime.now())
            .dateCreated(LocalDateTime.now())
            .lastUpdated(LocalDateTime.now())
            .build();
    }
}
