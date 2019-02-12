package franroa.transformers;

import franroa.api.OfferListResponse;
import franroa.api.OfferResponse;
import franroa.core.Offer;


import java.util.List;

public class OfferTransformer {
    public static OfferResponse transform(Offer offer) {
        OfferResponse response = new OfferResponse();

        response.id = offer.getLongId();
        response.name = offer.getString("name");
        response.price = offer.getLong("price");
        response.currency = offer.getString("currency");
        response.expires_at = offer.getString("expires_at");

        return response;
    }

    public static OfferListResponse transform(List<Offer> offers) {
        OfferListResponse responseList = new OfferListResponse();

        offers.forEach(offer -> {
            responseList.offers.add(OfferTransformer.transform(offer));
        });

        return responseList;
    }
}
