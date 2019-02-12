package franroa.testing.fakes;

import franroa.queue.Job;
import franroa.queue.Queue;
import org.junit.Assert;
import org.quartz.Trigger;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

public class FakeQueue implements Queue {
    private Map<Job, Date> queuedJobs = new HashMap();

    public FakeQueue() {
    }

    public void start() {
    }

    public void stop() {
    }

    public void push(Job job) {
        this.queuedJobs.put(job, new Date());
    }

    public void push(Job job, Date executionDate) {
        this.queuedJobs.put(job, executionDate);
    }

    public void push(Job job, Trigger trigger) {
        this.push(job);
    }

    public <T extends Job> void assertPushed(Class<T> jobClass, BiFunction<T, Date, Boolean> compareFunction) {
        Iterator var3 = this.queuedJobs.entrySet().iterator();

        Entry queuedJob;
        do {
            if (!var3.hasNext()) {
                Assert.fail("Job was not pushed into the queue!");
                return;
            }

            queuedJob = (Entry) var3.next();
        }
        while (!((Job) queuedJob.getKey()).getClass().equals(jobClass) || !((Boolean) compareFunction.apply((T) queuedJob.getKey(), (Date) queuedJob.getValue())).booleanValue());

    }
}
