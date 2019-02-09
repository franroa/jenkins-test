package franroa.feature.offers;

import franroa.core.Offer;
import franroa.feature.FeatureTestEnvironment;
import franroa.helper.ModelFactory;
import franroa.helper.TestResponse;
import org.assertj.core.api.Assertions;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

public class DeleteOfferTest extends FeatureTestEnvironment {
    @Test
    public void deleting_one_offer() {
        Offer offer = ModelFactory.offer();

        TestResponse response = delete("/v1/offers/" + offer.getLongId());

        response.assertStatus(HttpStatus.OK_200);
        Assertions.assertThat(Offer.count()).isEqualTo(0);
    }

    @Test
    public void trying_to_delete_a_non_existing_offer() {
        Long nonExistingOfferId = 12L;

        TestResponse response = delete("/v1/offers/" + nonExistingOfferId);

        response.assertResourceNotFound();
    }
}
