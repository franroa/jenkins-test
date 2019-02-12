package franroa.feature.offers;

import franroa.core.Offer;
import franroa.TestCase;
import franroa.helper.ModelFactory;
import franroa.helper.TestResponse;
import org.assertj.core.api.Assertions;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

public class DeleteOfferTest extends TestCase {
    @Test
    public void one_offer_can_be_cancelled() {
        Offer offer = ModelFactory.offer();

        TestResponse response = delete("/v1/offers/" + offer.getLongId());

        response.assertStatus(HttpStatus.OK_200);
        Assertions.assertThat(Offer.count()).isEqualTo(0);
    }

    @Test
    public void trying_to_cancel_a_non_existing_offer() {
        Long nonExistingOfferId = 12L;

        TestResponse response = delete("/v1/offers/" + nonExistingOfferId);

        response.assertResourceNotFound();
    }
}
