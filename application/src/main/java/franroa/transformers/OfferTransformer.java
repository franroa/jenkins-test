package franroa.transformers;

import franroa.core.Offer;
import franroa.dto.OfferListResponse;
import franroa.dto.OfferResponse;


import java.util.List;

public class OfferTransformer {
    public static OfferListResponse transform(List<Offer> offers) {
        OfferListResponse response = new OfferListResponse();

        for (Offer offer : offers) {
            OfferResponse offerResponse = new OfferResponse();
            offerResponse.name = offer.getString("name");
            offerResponse.price = offer.getFloat("price");

            response.offers.add(offerResponse);
        }

        return response;
    }
}
