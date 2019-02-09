package franroa.jobs;


import franroa.queue.Job;

public class TestJob extends Job {
    @Override
    public void handle() {
        System.out.println("TEST");
    }
}
