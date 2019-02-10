package franroa;


import franroa.api.OfferListResponse;
import franroa.api.OfferRequest;
import franroa.api.OfferResponse;
import franroa.exception.InterviewClientException;


import javax.validation.Validation;


public class FakeClient implements InterviewClient {
    private OfferRequest caughtRequest;
    private String caughtCorrelationId;
    public boolean fakeConnectionError = false;

    @Override
    public boolean ping() {
        return !fakeConnectionError;
    }

    @Override
    public OfferResponse createOffer(OfferRequest request, String correlationId) throws InterviewClientException {
        guard(request);

        caughtRequest = request;
        caughtCorrelationId = correlationId;

        OfferResponse response = new OfferResponse();
        response.id = 123L;
        response.currency = request.currency;
        response.name = request.name;
        response.price = request.price;

        return response;
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

    public OfferRequest getCaughtRequest() {
        return caughtRequest;
    }

    public String getCaughtCorrelationId() {
        return caughtCorrelationId;
    }

    private void guard() throws InterviewClientException {
        if (fakeConnectionError) {
            throw new InterviewClientException();
        }
    }

    private void guard(Object request) throws InterviewClientException {
        guard();

        if (! Validation.buildDefaultValidatorFactory().getValidator().validate(request).isEmpty()) {
            throw new InterviewClientException();
        }
    }
}
