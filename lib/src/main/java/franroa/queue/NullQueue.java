package franroa.queue;

import org.quartz.Trigger;


import java.util.Date;

public class NullQueue implements Queue {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void push(Job job) {

    }

    @Override
    public void push(Job job, Date schedule) {

    }

    @Override
    public void push(Job job, Trigger trigger) {

    }
}
