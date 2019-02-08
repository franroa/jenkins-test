package franroa.core;

import franroa.dto.OfferRequest;
import org.javalite.activejdbc.Model;

public class Offer extends Model {
    public static Offer buildFromRequest(OfferRequest request) {
        Offer offer = new Offer();
        offer.set("name", request.name);
        offer.set("price", request.price);
        offer.saveIt();
        return offer;
    }
}
