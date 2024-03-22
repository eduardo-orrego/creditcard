package com.nttdata.creditcard.repository.impl;

import com.nttdata.creditcard.model.CreditCard;
import com.nttdata.creditcard.repository.CreditCardReactiveMongodb;
import com.nttdata.creditcard.repository.CreditCardRepository;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: CreditCardRepositoryImpl. <br/>
 * <b>Bootcamp NTTDATA</b><br/>
 *
 * @author NTTDATA
 * @version 1.0
 *   <u>Developed by</u>:
 *   <ul>
 *   <li>Developer Carlos</li>
 *   </ul>
 * @since 1.0
 */
@Slf4j
@Repository
public class CreditCardRepositoryImpl implements CreditCardRepository {

    @Autowired
    private CreditCardReactiveMongodb creditCardReactiveMongodb;

    @Override
    public Mono<CreditCard> findCreditCard(BigInteger cardNumber) {
        return creditCardReactiveMongodb.findByCardNumber(cardNumber)
            .doOnSuccess(customer -> log.info("Successful find - cardNumber: ".concat(cardNumber.toString())));
    }

    @Override
    public Mono<CreditCard> findCreditCard(String creditCardId) {
        return creditCardReactiveMongodb.findById(creditCardId)
            .doOnSuccess(customer -> log.info("Successful find - creditCardId: ".concat(creditCardId)));
    }

    @Override
    public Flux<CreditCard> findCreditCards(BigInteger customerDocument) {
        return creditCardReactiveMongodb.findByCustomerDocument(customerDocument)
            .doOnComplete(() -> log.info("Successful find exists - customerDocument: "
                .concat(customerDocument.toString())));
    }

    @Override
    public Mono<CreditCard> saveCreditCard(CreditCard creditCard) {
        return creditCardReactiveMongodb.save(creditCard)
            .doOnSuccess(customer -> log.info("Successful save - creditCardId: ".concat(customer.getId())));
    }

    @Override
    public Mono<Boolean> findExistsCreditCard(BigInteger cardNumber) {
        return creditCardReactiveMongodb.existsByCardNumber(cardNumber)
            .doOnSuccess(result -> log.info("Successful find exists - cardNumber: ".concat(cardNumber.toString())));
    }


}
