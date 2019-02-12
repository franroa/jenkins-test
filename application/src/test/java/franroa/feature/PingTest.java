package franroa.feature;

import franroa.TestCase;
import franroa.helper.TestResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

public class PingTest extends TestCase {
    @Test
    public void ping_returns_200_for_authenticated_user() {
        TestResponse response = get("/ping");
        response.assertStatus(HttpStatus.OK_200);
    }
}
