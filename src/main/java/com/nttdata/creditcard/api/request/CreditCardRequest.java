package com.nttdata.creditcard.api.request;

import com.nttdata.creditcard.enums.DebitCardStatusEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class: CreditCardRequest. <br/>
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
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardRequest {

  @NotNull(message = "El campo 'status' no puede ser nulo")
  private DebitCardStatusEnum status;
  @NotNull(message = "El campo 'customerDocument' no puede ser vacío")
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
