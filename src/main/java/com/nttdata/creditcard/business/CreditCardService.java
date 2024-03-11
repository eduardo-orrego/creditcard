package com.nttdata.creditcard.business;

import com.nttdata.creditcard.api.request.CreditCardRequest;
import com.nttdata.creditcard.model.CreditCard;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardService {
    Mono<CreditCard> saveCreditCard(CreditCardRequest creditCardRequest);

    Mono<CreditCard> updateCreditCard(CreditCardRequest creditCardRequest, String creditCardId);

    Mono<CreditCard> getCreditCard(BigInteger cardNumber);

    Flux<CreditCard> getCreditCards(String customerId);

    Mono<Void> deleteCreditCard(String creditCardId);

}
