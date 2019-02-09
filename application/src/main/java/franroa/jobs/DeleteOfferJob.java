package franroa.jobs;

import franroa.queue.Job;


import java.sql.Timestamp;

public class DeleteOfferJob extends Job {
    @Override
    protected void handle() {

    }

    public DeleteOfferJob setExpirationTime(Timestamp expirationTime) {
        return (DeleteOfferJob) set("expirationTime", expirationTime);
    }
}
