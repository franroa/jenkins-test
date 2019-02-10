package franroa;


import franroa.api.OfferListResponse;
import franroa.api.OfferRequest;
import franroa.api.OfferResponse;
import franroa.exception.InterviewClientException;
import franroa.simplehttp.ResponseStatusNotExpectedException;

public interface InterviewClient {
    boolean ping();
    OfferResponse createOffer(OfferRequest request, String correlationId) throws InterviewClientException;
    OfferListResponse getAllOffers() throws InterviewClientException;
    OfferResponse getOffer(Long offerId) throws InterviewClientException;
    boolean cancelOffer(Long offerId) throws InterviewClientException;
}
