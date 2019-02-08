package franroa.transformers;

import franroa.core.Offer;
import franroa.dto.OfferResponse;

public class OfferTransformer {
    public static OfferResponse transform(Offer offer) {
        OfferResponse response = new OfferResponse();
        response.id = offer.getLongId();
        response.name = offer.getString("name");
        response.price = offer.getString("price");

        return response;
    }
}
