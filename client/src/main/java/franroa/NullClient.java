package franroa;


import franroa.api.OfferListResponse;
import franroa.api.OfferRequest;
import franroa.api.OfferResponse;
import franroa.exception.InterviewClientException;


public class NullClient implements InterviewClient {
    @Override
    public boolean ping() {
        return false;
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
