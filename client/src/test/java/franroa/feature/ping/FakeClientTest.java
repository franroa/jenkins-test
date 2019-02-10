package franroa.feature.ping;

import franroa.InterviewClient;

public class FakeClientTest extends InterviewClientTest {
    @Override
    protected InterviewClient createClient(String scenario) {
        return createFakeClient(scenario);
    }
}
