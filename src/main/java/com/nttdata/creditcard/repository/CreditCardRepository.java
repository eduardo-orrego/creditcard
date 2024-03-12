package com.nttdata.creditcard.repository;

import com.nttdata.creditcard.model.CreditCard;
import java.math.BigInteger;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CreditCardRepository extends ReactiveMongoRepository<CreditCard, String> {

    Mono<CreditCard> findByCardNumber(BigInteger cardNumber);

    Flux<CreditCard> findByCustomerId(String customerId);

}
