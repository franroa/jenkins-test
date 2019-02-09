package franroa.feature.offers;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import franroa.core.Offer;
import franroa.api.enums.Currency;
import franroa.feature.FeatureTestEnvironment;
import franroa.helper.RequestFactory;
import franroa.helper.TestRequest;
import franroa.helper.TestResponse;
import org.assertj.core.api.Assertions;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.IOException;
import java.util.List;


import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class CreateOfferTest extends FeatureTestEnvironment {
    @Test
    public void stores_one_offer() throws IOException {
        TestRequest request = new TestRequest();
        request.set("name", "Offer Name");
        request.set("price", "4");
        request.set("currency", Currency.EURO.toString());

        TestResponse response = post("/v1/offers", request);

        response.assertStatus(HttpStatus.OK_200);
        response.assertData("name", "Offer Name");
        response.assertData("price", "4");
        response.assertData("currency", Currency.EURO.toString());
        List<Offer> offers = Offer.findAll();
        assertThat(offers).hasSize(1);
        assertThat(offers.get(0).getString("name")).isEqualTo("Offer Name");
        assertThat(offers.get(0).getLong("price")).isEqualTo(4);
        assertThat(offers.get(0).getString("currency")).isEqualTo(Currency.EURO.toString());
    }

    @Test
    @DataProvider({ "name", "price", "currency" })
    public void some_fields_are_required(String requiredField) {
        TestRequest request = RequestFactory.offer();
        request.remove(requiredField);

        TestResponse response = post("/v1/offers", request);

        response.assertUnprocessableEntityError(requiredField + " may not be null");
        Assertions.assertThat(Offer.count()).isEqualTo(0);
    }
}
