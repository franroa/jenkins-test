package franroa.feature.offers;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import franroa.api.enums.Currency;
import franroa.core.Offer;
import franroa.feature.FeatureTestEnvironment;
import franroa.helper.RequestFactory;
import franroa.helper.TestRequest;
import franroa.helper.TestResponse;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class CreateOfferTest extends FeatureTestEnvironment {
    @Test
    public void stores_one_offer() throws IOException {
        Timestamp expiresAt = Timestamp.from(Instant.ofEpochMilli(System.currentTimeMillis() + 5 * 60 * 1000));
        TestRequest request = new TestRequest();
        request.set("name", "Offer Name");
        request.set("price", "4");
        request.set("currency", Currency.EUR.toString());
        request.set("expires_at", expiresAt.toString());

        TestResponse response = post("/v1/offers", request);

        List<Offer> offers = Offer.findAll();
        assertThat(offers).hasSize(1);
        response.assertHeader("location", "http://localhost:9998/v1/offers/" + offers.get(0).getLongId());
        response.assertStatus(HttpStatus.OK_200);
        response.assertData("name", "Offer Name");
        response.assertData("price", "4");
        response.assertData("currency", Currency.EUR.toString());
        assertThat(offers.get(0).getString("name")).isEqualTo("Offer Name");
        assertThat(offers.get(0).getLong("price")).isEqualTo(4);
        assertThat(offers.get(0).getString("currency")).isEqualTo(Currency.EUR.toString());
        assertThat(offers.get(0).getTimestamp("expires_at")).isEqualTo(expiresAt);
    }

    @Test
    @DataProvider({ "name", "price", "currency", "expires_at" })
    public void some_fields_are_required(String requiredField) {
        TestRequest request = RequestFactory.offer();
        request.remove(requiredField);

        TestResponse response = post("/v1/offers", request);

        response.assertUnprocessableEntityError(requiredField + " may not be null");
        Assertions.assertThat(Offer.count()).isEqualTo(0);
    }

    @Test
    public void expires_at_is_has_to_be_a_valid_TIMESTAMP() {
        TestRequest request = RequestFactory.offer();
        request.set("expires_at", "INVALID");

        TestResponse response = post("/v1/offers", request);

        response.assertUnprocessableEntityError("expires_at Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]");
        Assertions.assertThat(Offer.count()).isEqualTo(0);
    }

    @Test
    public void currency_has_to_be_one_of_the_enum() {
        TestRequest request = RequestFactory.offer();
        request.set("currency", "INVALID_VALUE");

        TestResponse response = post("/v1/offers", request);

        Assertions.assertThat(Offer.count()).isEqualTo(0);
        response.assertUnprocessableEntityError("currency Invalid value. This is not permitted");
    }

    @Test
    public void name_should_not_be_longer_than_255() {
        TestRequest request = RequestFactory.offer();
        request.set("name", StringUtils.rightPad("a", 256, "a").toString());

        TestResponse response = post("/v1/offers", request);

        Assertions.assertThat(Offer.count()).isEqualTo(0);
        response.assertUnprocessableEntityError("name size must be between 0 and 255");
    }

    @Test
    public void name_can_have_a_length_of_255() {
        TestRequest request = RequestFactory.offer();
        request.set("name", StringUtils.rightPad("a", 255, "a").toString());

        TestResponse response = post("/v1/offers", request);

        response.assertStatus(HttpStatus.OK_200);
        Assertions.assertThat(Offer.count()).isEqualTo(1);
    }
}
