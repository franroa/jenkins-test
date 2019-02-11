package franroa.jobs;

import franroa.core.Offer;
import franroa.queue.Job;
import org.slf4j.LoggerFactory;

public class DeleteOfferJob extends Job {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DeleteOfferJob.class);

    @Override
    public void handle() {
        LOGGER.info("job is being handle for id " + getData().get("offerId"));

        Offer.findById(getData().get("offerId")).delete();
    }

    public DeleteOfferJob setOfferId(Long offerId) {
        return (DeleteOfferJob) set("offerId", offerId);
    }
}
