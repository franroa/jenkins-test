package franroa.feature.offers;

import franroa.core.Offer;
import franroa.feature.FeatureTestEnvironment;
import franroa.helper.ModelFactory;
import franroa.helper.TestResponse;
import org.junit.Test;


import java.util.List;

public class FetchAllOffersTest extends FeatureTestEnvironment {
    @Test
    public void all_offers_are_fetched() {
        List<Offer> offers = ModelFactory.offer(2);

        TestResponse response = get("/v1/offers");

        response.assertData("offers", 0, "name", offers.get(0).getString("name"));
        response.assertData("offers", 0, "price", offers.get(0).getString("price"));
        response.assertData("offers", 0, "currency", offers.get(0).getString("currency"));
        response.assertData("offers", 1, "name", offers.get(1).getString("name"));
        response.assertData("offers", 1, "price", offers.get(1).getString("price"));
        response.assertData("offers", 1, "currency", offers.get(1).getString("currency"));
    }
}
