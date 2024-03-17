package com.nttdata.creditcard.api.request;

import com.nttdata.creditcard.enums.DebitCardStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "El campo 'customerDocument' no puede ser vacío")
    private BigInteger customerDocument;
    @NotNull(message = "El campo 'creditNumber' no puede ser vacío")
    private BigInteger creditNumber;

    @Size(min = 16, max = 16)
    @Pattern(regexp = "^[45]\\d{15}$")
    private BigInteger cardNumber;
    @Size(min = 3, max = 3)
    private Integer cvv;
    private LocalDate expirationDate;
    private LocalDate activateDate;

}
