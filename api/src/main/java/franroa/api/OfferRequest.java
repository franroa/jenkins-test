package franroa.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import franroa.api.enums.Currency;
import franroa.api.validation.annotations.Enum;
import franroa.api.validation.annotations.PresentOrFutureDate;
import franroa.api.validation.annotations.Timestamp;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferRequest {
    @NotNull
    @Size(max = 255)
    @JsonProperty("name")
    public String name;

    @NotNull
    @JsonProperty("price")
    public Long price;

    @NotNull
    @Enum(enumClass = Currency.class)
    @JsonProperty("currency")
    public String currency;

    @NotNull
    @Timestamp
    @PresentOrFutureDate(plusSeconds = 5)
    @JsonProperty("expires_at")
    public String expires_at;
}
