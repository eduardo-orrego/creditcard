package com.nttdata.creditcard.repository;

import com.nttdata.creditcard.model.CreditCard;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardRepository {

    Mono<CreditCard> findCreditCard(BigInteger cardNumber);

    Mono<CreditCard> findCreditCard(String creditCardId);

    Flux<CreditCard> findCreditCards(BigInteger customerDocument);

    Mono<CreditCard> saveCreditCard(CreditCard creditCard);

    Mono<Boolean> findExistsCreditCard(BigInteger cardNumber);
}
