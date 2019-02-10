package franroa.feature.fetchOneOffer;

import franroa.InterviewClient;
import franroa.api.OfferRequest;
import franroa.api.OfferResponse;
import franroa.exception.InterviewClientException;
import franroa.helper.IntegrationTestCase;
import franroa.helper.factory.RequestFactory;
import org.junit.Test;


import static org.assertj.core.api.Java6Assertions.assertThat;

public abstract class InterviewClientTest extends IntegrationTestCase {
    protected abstract InterviewClient createClient(String scenario);

    @Test
    public void fetching_the_offer() throws InterviewClientException {
        OfferRequest request = RequestFactory.offer();
        InterviewClient client = createClient("valid");
        OfferResponse offer = client.createOffer(request, "my-correlation-id");

        OfferResponse response = client.getOffer(offer.id);

        assertThat(response.price).isEqualTo(request.price);
    }

    @Test(expected = InterviewClientException.class)
    public void if_there_is_a_connection_error_it_throws_an_exception() throws InterviewClientException {
        createClient("connection-error").getOffer(155L);
    }
}
