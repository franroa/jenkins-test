package franroa;


import franroa.api.OfferListResponse;
import franroa.api.OfferRequest;
import franroa.api.OfferResponse;
import franroa.api.enums.Currency;
import franroa.exception.InterviewClientException;


import javax.validation.Validation;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FakeClient implements InterviewClient {
    private OfferRequest caughtRequest;
    private String caughtCorrelationId;
    private Map<Long, OfferResponse> fakeOffers = new HashMap<>();
    public boolean fakeConnectionError = false;
    private List<Long> caughtFetchOfferCalls = new ArrayList<>();

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
        response.id = (long) fakeOffers.size();
        response.currency = request.currency;
        response.name = request.name;
        response.price = request.price;

        fakeOffers.put(response.id, response);

        return response;
    }

    @Override
    public OfferListResponse getAllOffers() throws InterviewClientException {
        return null;
    }

    @Override
    public OfferResponse getOffer(Long offerId) throws InterviewClientException {
        caughtFetchOfferCalls.add(offerId);

        guard();

        return fakeOffers.get(offerId);
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

    public void fakeOffer(long offerId, String name, long price, Currency currency, Timestamp expiresAt) {
        OfferResponse offer = new OfferResponse();

        offer.id = offerId;
        offer.name = name;
        offer.price = price;
        offer.currency = currency.toString();
        offer.expires_at = expiresAt.toString();

        fakeOffers.putIfAbsent(offerId, offer);
    }

    public List<Long> getCaughtFetchOfferCalls() {
        return caughtFetchOfferCalls;
    }
}
