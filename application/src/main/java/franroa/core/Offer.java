package franroa.core;

import franroa.api.OfferRequest;

public class Offer extends Model {
    public static Offer buildFromRequest(OfferRequest request) {
        Offer offer = new Offer();
        offer.set("name", request.name);
        offer.set("price", request.price);
        offer.set("currency", request.currency);
        offer.saveIt();
        return offer;
    }
}
