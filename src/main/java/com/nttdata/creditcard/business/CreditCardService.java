package com.nttdata.creditcard.business;

import com.nttdata.creditcard.api.request.CreditCardRequest;
import com.nttdata.creditcard.model.CreditCard;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: CreditCardService. <br/>
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
public interface CreditCardService {
  Mono<CreditCard> saveCreditCard(CreditCardRequest creditCardRequest);

  Mono<CreditCard> updateCreditCard(CreditCardRequest creditCardRequest, String creditCardId);

  Mono<CreditCard> getCreditCard(BigInteger cardNumber);

  Flux<CreditCard> getCreditCards(BigInteger customerDocument);


}
