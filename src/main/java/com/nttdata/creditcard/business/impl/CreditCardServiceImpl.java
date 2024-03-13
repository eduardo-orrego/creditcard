package com.nttdata.creditcard.business.impl;

import com.nttdata.creditcard.api.request.CreditCardRequest;
import com.nttdata.creditcard.builder.CreditCardBuilder;
import com.nttdata.creditcard.business.CreditCardService;
import com.nttdata.creditcard.model.CreditCard;
import com.nttdata.creditcard.repository.CreditCardRepository;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public Mono<CreditCard> saveCreditCard(CreditCardRequest creditCardRequest) {
        return Mono.just(creditCardRequest)
            .map(creditCard -> CreditCardBuilder.toCreditCardEntity(creditCard, null))
            .flatMap(creditCardRepository::save)
            .doOnSuccess(customer -> log.info("Successful save - creditCardId: ".concat(customer.getId())));
    }

    @Override
    public Mono<CreditCard> updateCreditCard(CreditCardRequest creditCardRequest, String creditCardId) {
        return creditCardRepository.existsById(creditCardId)
            .flatMap(aBoolean -> {
                if (Boolean.TRUE.equals(aBoolean)) {
                    return creditCardRepository.save(
                        CreditCardBuilder.toCreditCardEntity(creditCardRequest, creditCardId));
                }
                return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit Card not found - "
                    + "creditCardId: ".concat(creditCardId)));
            })
            .doOnSuccess(account -> log.info("Successful update - creditCardId: ".concat(creditCardId)));
    }

    @Override
    public Mono<CreditCard> getCreditCard(BigInteger cardNumber) {
        return creditCardRepository.findByCardNumber(cardNumber)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit Card not found - "
                + "creditNumber: ".concat(cardNumber.toString()))))
            .doOnSuccess(customer -> log.info("Successful search - cardNumber: ".concat(cardNumber.toString())));
    }

    @Override
    public Flux<CreditCard> getCreditCards(String customerId) {
        return creditCardRepository.findByCustomerId(customerId)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit Card not found - "
                + "customerId: ".concat(customerId))))
            .doOnComplete(() -> log.info("Successful search - customerId: ".concat(customerId)));
    }

}
