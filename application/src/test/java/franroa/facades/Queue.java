package franroa.facades;


import franroa.container.Container;
import franroa.fakes.FakeQueue;
import franroa.queue.Job;


import java.util.Date;
import java.util.function.BiFunction;

public class Queue {
    private static FakeQueue queue;

    public Queue() {
    }

    public static void fake() {
        queue = new FakeQueue();
        Container.instance(franroa.queue.Queue.class, queue);
    }

    public <T extends Job> void assertPushed(Class<T> jobClass, BiFunction<T, Date, Boolean> compareFunction) {
        queue.assertPushed(jobClass, compareFunction);
    }
}
