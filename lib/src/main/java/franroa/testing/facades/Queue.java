package franroa.testing.facades;


import franroa.container.Container;
import franroa.queue.Job;
import franroa.testing.fakes.FakeQueue;


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

    public static <T extends Job> void assertPushed(Class<T> jobClass, BiFunction<T, Date, Boolean> comparisonFunction) {
        queue.assertPushed(jobClass, comparisonFunction);
    }
}
