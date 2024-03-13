package com.nttdata.creditcard.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "creditCard")
public class CreditCard {

    @Id
    private String id;
    private BigInteger cardNumber;
    private String status;
    private LocalDate expirationDate;
    private LocalDate activateDate;
    private String cvv;
    private String customerId;
    private BigDecimal interestRate;
    private BigDecimal availableBalance;
    private BigDecimal creditLimit;
    private LocalDateTime lastTransactionDate;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

}
