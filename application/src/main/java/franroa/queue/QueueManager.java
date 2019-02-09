package franroa.queue;

import franroa.container.Container;
import io.dropwizard.lifecycle.Managed;

public class QueueManager implements Managed {
    @Override
    public void start() throws Exception {
        Container.resolve(Queue.class).start();
    }

    @Override
    public void stop() throws Exception {
        Container.resolve(Queue.class).stop();
    }
}
