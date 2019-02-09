package franroa.core;

import franroa.api.OfferRequest;


import java.sql.Timestamp;

public class Offer extends Model {
    public static Offer buildFromRequest(OfferRequest request) {
        Offer offer = new Offer();
        offer.set("name", request.name);
        offer.set("price", request.price);
        offer.set("currency", request.currency);
        offer.set("expires_at", Timestamp.valueOf(request.expires_at));
        offer.saveIt();
        return offer;
    }
}
