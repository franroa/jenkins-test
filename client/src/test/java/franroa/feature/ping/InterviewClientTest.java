package franroa.feature.ping;

import franroa.InterviewClient;
import franroa.helper.IntegrationTestCase;
import org.assertj.core.api.Assertions;
import org.junit.Test;


public abstract class InterviewClientTest extends IntegrationTestCase {
    protected abstract InterviewClient createClient(String scenario);

    @Test
    public void returns_true_on_successful_ping() {
        Assertions.assertThat(createClient("valid").ping()).isTrue();
    }

    @Test
    public void returns_false_when_using_invalid_port() {
       Assertions.assertThat(createClient("connection-error").ping()).isFalse();
    }
}
