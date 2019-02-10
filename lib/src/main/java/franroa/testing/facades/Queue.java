package franroa.testing.facades;


import franroa.container.Container;
import franroa.testing.fakes.FakeQueue;
import franroa.queue.Job;


import java.util.Date;

public class Queue {
    private static FakeQueue queue;

    public Queue() {
    }

    public static void fake() {
        queue = new FakeQueue();
        Container.instance(franroa.queue.Queue.class, queue);
    }

    public static <T extends Job> void assertPushed(Class<T> jobClass, Date delay) {
        queue.assertPushed(jobClass, delay);
    }
}
