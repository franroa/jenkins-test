package franroa.jobs;

import franroa.core.Offer;
import franroa.queue.Job;

public class DeleteOfferJob extends Job {
    @Override
    public void handle() {
        Offer.findById(getData().get("offerId")).delete();
    }

    public DeleteOfferJob setOfferId(Long offerId) {
        return (DeleteOfferJob) set("offerId", offerId);
    }
}
