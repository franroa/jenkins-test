package franroa.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import franroa.api.enums.Currency;
import franroa.api.validation.annotations.Enum;


import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferRequest {
    @NotNull
    @JsonProperty("name")
    public String name;

    @NotNull
    @JsonProperty("price")
    public Long price;

    @NotNull
    @Enum(enumClass = Currency.class)
    @JsonProperty("currency")
    public String currency;
}
