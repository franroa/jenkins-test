package franroa;

import franroa.api.OfferListResponse;
import franroa.api.OfferRequest;
import franroa.api.OfferResponse;
import franroa.config.InterviewClientConfiguration;
import franroa.exception.InterviewClientException;
import franroa.simplehttp.SimpleHttp;


public class HttpClient implements InterviewClient {
    SimpleHttp client;

    public HttpClient(InterviewClientConfiguration config) {
        client = new SimpleHttp(config.host, "correlation-id", "key");
    }

    @Override
    public boolean ping() {
        try {
            return client.get("ping").getStatus() == 200;
        } catch (RuntimeException exception) {
            return false;
        }
    }

    @Override
    public OfferResponse createOffer(OfferRequest request, String correlationId) throws InterviewClientException {
        return null;
    }

    @Override
    public OfferListResponse getAllOffers() throws InterviewClientException {
        return null;
    }

    @Override
    public OfferResponse getOffer(Long offerId) throws InterviewClientException {
        return null;
    }

    @Override
    public boolean cancelOffer(Long offerId) throws InterviewClientException {
        return false;
    }
}
