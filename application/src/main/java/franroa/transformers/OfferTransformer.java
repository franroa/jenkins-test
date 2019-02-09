package franroa.transformers;

import franroa.api.OfferListResponse;
import franroa.core.Offer;
import franroa.api.OfferResponse;


import java.util.List;

public class OfferTransformer {
    public static OfferResponse transform(Offer offer) {
        OfferResponse response = new OfferResponse();

        response.id = offer.getLongId();
        response.name = offer.getString("name");
        response.price = offer.getLong("price");
        response.currency = offer.getString("currency");

        return response;
    }

    public static OfferListResponse transform(List<Offer> offers) {
        OfferListResponse responseList = new OfferListResponse();

        offers.forEach(offer -> {
            OfferResponse response = new OfferResponse();

            response.id = offer.getLongId();
            response.name = offer.getString("name");
            response.price = offer.getLong("price");
            response.currency = offer.getString("currency");

            responseList.offers.add(response);
        });

        return responseList;
    }
}
