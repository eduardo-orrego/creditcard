package com.nttdata.creditcard.repository;

import com.nttdata.creditcard.model.CreditCard;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: CreditCardRepository. <br/>
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
public interface CreditCardRepository {

    Mono<CreditCard> findCreditCard(BigInteger cardNumber);

    Mono<CreditCard> findCreditCard(String creditCardId);

    Flux<CreditCard> findCreditCards(BigInteger customerDocument);

    Mono<CreditCard> saveCreditCard(CreditCard creditCard);

    Mono<Boolean> findExistsCreditCard(BigInteger cardNumber);
}
