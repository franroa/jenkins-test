package franroa.feature.offers;

import franroa.feature.FeatureTestEnvironment;
import franroa.helper.TestResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;


import java.io.IOException;

public class FetchOffersTest extends FeatureTestEnvironment {
    @Test
    public void fetching_all_offers() throws IOException {
        TestResponse response = get("/v1/offers");

        response.assertStatus(HttpStatus.OK_200);
        response.assertData("name", "Offer Name");
        response.assertData("price", "4€");
    }
}
