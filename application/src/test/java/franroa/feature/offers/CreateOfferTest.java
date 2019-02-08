package franroa.feature.offers;

import franroa.feature.FeatureTestEnvironment;
import franroa.helper.TestRequest;
import franroa.helper.TestResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;


import java.io.IOException;

public class CreateOfferTest extends FeatureTestEnvironment {
    @Test
    public void stores_one_offer() throws IOException {
        TestRequest request = new TestRequest();
        request.set("name", "Offer Name");
        request.set("price", "4€");

        TestResponse response = post("/v1/offers", request);

        response.assertStatus(HttpStatus.OK_200);
        response.assertData("name", "Offer Name");
        response.assertData("price", "4€");
    }
}
