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

/**
 * Class: CreditCardServiceImpl. <br/>
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
@Service
public class CreditCardServiceImpl implements CreditCardService {

  @Autowired
  private CreditCardRepository creditCardRepository;

  @Override
  public Mono<CreditCard> saveCreditCard(CreditCardRequest creditCardRequest) {

    return creditCardRepository.findExistsCreditCard(creditCardRequest.getCardNumber())
      .flatMap(aBoolean -> {
        if (Boolean.FALSE.equals(aBoolean)) {
          return creditCardRepository.saveCreditCard(
            CreditCardBuilder.toCreditCardEntity(creditCardRequest));

        }
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "There is another Credit Card with the same Credit Number: "
            .concat(creditCardRequest.getCardNumber().toString())));
      });

  }

  @Override
  public Mono<CreditCard> updateCreditCard(CreditCardRequest creditCardRequest,
    String creditCardId) {
    return creditCardRepository.findCreditCard(creditCardId)
      .flatMap(creditCardCurrent -> {
        if (creditCardRequest.getCardNumber().compareTo(creditCardCurrent.getCardNumber()) == 0) {
          return creditCardRepository.saveCreditCard(
            CreditCardBuilder.toCreditCardEntity(creditCardRequest, creditCardCurrent));
        }

        return this.saveCreditCard(creditCardRequest);
      })
      .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Credit Card not found - creditCardId: ".concat(creditCardId)))));
  }

  @Override
  public Mono<CreditCard> getCreditCard(BigInteger cardNumber) {
    return creditCardRepository.findCreditCard(cardNumber)
      .switchIfEmpty(
        Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit Card not found - "
          + "creditNumber: ".concat(cardNumber.toString()))));

  }

  @Override
  public Flux<CreditCard> getCreditCards(BigInteger customerDocument) {
    return creditCardRepository.findCreditCards(customerDocument)
      .switchIfEmpty(
        Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit Card not found - "
          + "customerDocument: ".concat(customerDocument.toString()))));
  }

}
