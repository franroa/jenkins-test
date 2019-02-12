package franroa.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OfferResponse {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("price")
    public Long price;

    @JsonProperty("currency")
    public String currency;

    @JsonProperty("expires_at")
    public String expires_at;
}
