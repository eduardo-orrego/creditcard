package com.nttdata.creditcard.api.request;

import com.nttdata.creditcard.enums.DebitCardStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardRequest {

    @NotNull(message = "El campo 'status' no puede ser nulo")
    private DebitCardStatusEnum status;
    private LocalDate expirationDate;
    private LocalDate activateDate;
    private String cvv;
    private BigInteger cardNumber;
    @NotBlank(message = "El campo 'customerId' no puede ser vacío")
    private String customerId;
    private BigDecimal interestRate;
    private BigDecimal availableBalance;
    @NotNull(message = "El campo 'creditLimit' no puede ser nulo")
    private BigDecimal creditLimit;

}
