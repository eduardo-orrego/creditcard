package com.nttdata.creditcard.builder;

import com.nttdata.creditcard.api.request.CreditCardRequest;
import com.nttdata.creditcard.model.CreditCard;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class: CreditCardBuilder. <br/>
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
public class CreditCardBuilder {
  CreditCardBuilder() {
  }

  public static CreditCard toCreditCardEntity(CreditCardRequest creditCardRequest) {
    return CreditCard.builder()
      .status(creditCardRequest.getStatus().name())
      .customerDocument(creditCardRequest.getCustomerDocument())
      .creditNumber(creditCardRequest.getCreditNumber())
      .expirationDate(Objects.nonNull(creditCardRequest.getExpirationDate())
        ? creditCardRequest.getExpirationDate()
        : LocalDate.now().plusYears(1))
      .activateDate(Objects.nonNull(creditCardRequest.getActivateDate())
        ? creditCardRequest.getActivateDate()
        : LocalDate.now())
      .cvv(Objects.nonNull(creditCardRequest.getCvv())
        ? creditCardRequest.getCvv()
        : generateCvv())
      .cardNumber(Objects.nonNull(creditCardRequest.getCardNumber())
        ? creditCardRequest.getCardNumber()
        : generateCardNumber())
      .dateCreated(LocalDateTime.now())
      .lastUpdated(LocalDateTime.now())
      .build();
  }

  public static CreditCard toCreditCardEntity(CreditCardRequest creditCardRequest,
    CreditCard creditCard) {
    return CreditCard.builder()
      .id(creditCard.getId())
      .status(creditCardRequest.getStatus().name())
      .customerDocument(creditCardRequest.getCustomerDocument())
      .creditNumber(creditCardRequest.getCreditNumber())
      .expirationDate(Objects.nonNull(creditCardRequest.getExpirationDate())
        ? creditCardRequest.getExpirationDate()
        : creditCard.getExpirationDate())
      .activateDate(Objects.nonNull(creditCardRequest.getActivateDate())
        ? creditCardRequest.getActivateDate()
        : creditCard.getActivateDate())
      .cvv(Objects.nonNull(creditCardRequest.getCvv())
        ? creditCardRequest.getCvv()
        : creditCard.getCvv())
      .cardNumber(Objects.nonNull(creditCardRequest.getCreditNumber())
        ? creditCardRequest.getCreditNumber()
        : creditCard.getCardNumber())
      .dateCreated(creditCard.getDateCreated())
      .lastUpdated(LocalDateTime.now())
      .build();
  }

  private static BigInteger generateCardNumber() {
    String cardNumber = "50".concat(
      LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    return new BigInteger(cardNumber);
  }

  private static Integer generateCvv() {
    return ThreadLocalRandom.current().nextInt(100, 1000);
  }
}
