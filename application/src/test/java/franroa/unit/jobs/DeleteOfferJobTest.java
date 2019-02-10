package franroa.unit.jobs;

import franroa.core.Offer;
import franroa.feature.TestCase;
import franroa.helper.ModelFactory;
import franroa.jobs.DeleteOfferJob;
import org.junit.Test;


import static org.assertj.core.api.Java6Assertions.assertThat;

public class DeleteOfferJobTest extends TestCase {
    @Test
    public void an_expired_offer_should_be_removed_from_the_database() {
        Offer offer = ModelFactory.offer();

        new DeleteOfferJob()
                .setOfferId(offer.getLongId())
                .handle();

        assertThat(Offer.count()).isEqualTo(0);
    }
}
