package com.nttdata.creditcard.api;

import com.nttdata.creditcard.api.request.CreditCardRequest;
import com.nttdata.creditcard.business.CreditCardService;
import com.nttdata.creditcard.model.CreditCard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/creditCards")
public class CreditCardController {
    private final CreditCardService creditCardService;

    @Autowired
    CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    /**
     * POST : Create a new Credit Card
     *
     * @param creditCard (required)
     * @return Created (status code 201)
     */
    @Operation(
        operationId = "creditCardPost",
        summary = "Create a new Credit Card",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCard.class))
            })
        }
    )
    @PostMapping(
        value = "",
        produces = {"application/json"},
        consumes = {"application/json"}
    )
    public Mono<CreditCard> creditCardPost(
        @Validated @RequestBody(required = false) CreditCardRequest creditCard
    ) {
        return creditCardService.saveCreditCard(creditCard);
    }

    /**
     * PUT : Update a Credit Card exists
     *
     * @param creditCardId (required)
     * @param creditCard   (required)
     * @return Ok (status code 200)
     */
    @Operation(
        operationId = "creditCardPut",
        summary = "Update a Credit Card",
        responses = {
            @ApiResponse(responseCode = "200", description = "Updated", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCard.class))
            })
        }
    )
    @PutMapping(
        value = "/{creditCardId}",
        produces = {"application/json"},
        consumes = {"application/json"}
    )
    public Mono<CreditCard> creditCardPut(
        @Parameter(name = "creditCardId", description = "", required = true, in = ParameterIn.PATH)
        @PathVariable("creditCardId") String creditCardId,
        @Validated @RequestBody CreditCardRequest creditCard
    ) {
        return creditCardService.updateCreditCard(creditCard, creditCardId);
    }

    /**
     * GET /{cardNumber} : Get information about a specific Credit Card
     *
     * @param cardNumber (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "creditCardGet",
        summary = "Get information about a specific Credit Card",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCard.class))
            })
        }
    )
    @GetMapping(
        value = "/{cardNumber}",
        produces = {"application/json"}
    )
    public Mono<CreditCard> creditCardGet(
        @Parameter(name = "cardNumber", description = "", required = true, in = ParameterIn.PATH)
        @PathVariable("cardNumber") BigInteger cardNumber
    ) {
        return creditCardService.getCreditCard(cardNumber);
    }


    /**
     * GET : Get a list of Credit Cards by customer
     *
     * @param customerDocument (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "creditCardsGet",
        summary = "Get a list of Credit Cards for the user",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation =
                    CreditCard.class)))
            })
        }
    )
    @GetMapping(
        value = "",
        produces = {"application/json"}
    )
    public Flux<CreditCard> creditCardsGet(
        @NotNull @Parameter(name = "customerDocument", description = "", required = true, in = ParameterIn.QUERY)
        @Validated @RequestParam(value = "customerDocument") BigInteger customerDocument
    ) {
        return creditCardService.getCreditCards(customerDocument);
    }


}
