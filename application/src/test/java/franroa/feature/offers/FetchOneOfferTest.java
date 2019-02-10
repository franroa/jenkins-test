package franroa.feature.offers;

import franroa.core.Offer;
import franroa.feature.TestCase;
import franroa.helper.ModelFactory;
import franroa.helper.TestResponse;
import org.junit.Test;

public class FetchOneOfferTest extends TestCase {
    @Test
    public void all_offers_are_fetched() {
        Offer offer = ModelFactory.offer();

        TestResponse response = get("/v1/offers/" + offer.getId());

        response.assertData("name", offer.getString("name"));
        response.assertData("price", offer.getString("price"));
        response.assertData("currency", offer.getString("currency"));
        response.assertData("expires_at", offer.getString("expires_at"));
    }

    @Test
    public void try_to_fetch_a_non_existing_offer() {
        Long nonExistingOfferId = 12L;

        TestResponse response = get("/v1/offers/" + nonExistingOfferId);

        response.assertResourceNotFound();
    }
}
