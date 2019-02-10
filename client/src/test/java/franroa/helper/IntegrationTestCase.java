package franroa.helper;

import franroa.FakeClient;
import franroa.HttpClient;
import franroa.config.InterviewClientConfiguration;
import franroa.config.SupportedClients;

public abstract class IntegrationTestCase {
    protected HttpClient createHttpClient(String scenario) {
        InterviewClientConfiguration config = new InterviewClientConfiguration();

        if (scenario.equals("connection-error")) {
            config.host = "http://localhost:8228";
        } else {
            config.host = "http://localhost:8080";
        }

        config.client = SupportedClients.HTTP;

        return new HttpClient(config);
    }

    protected FakeClient createFakeClient(String scenario) {
        FakeClient client = new FakeClient();

        return client;
    }
}
