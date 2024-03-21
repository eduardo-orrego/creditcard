package com.nttdata.creditcard.repository;

import com.nttdata.creditcard.model.CreditCard;
import java.math.BigInteger;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: CreditCardReactiveMongodb. <br/>
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
@Repository
public interface CreditCardReactiveMongodb extends ReactiveMongoRepository<CreditCard, String> {

  Mono<CreditCard> findByCardNumber(BigInteger cardNumber);

  Flux<CreditCard> findByCustomerDocument(BigInteger customerDocument);

  Mono<Boolean> findExistsByCardNumber(BigInteger cardNumber);

}
