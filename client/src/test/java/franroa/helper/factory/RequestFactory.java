package franroa.helper.factory;

import com.github.javafaker.Faker;
import franroa.api.OfferRequest;
import franroa.api.OfferResponse;
import franroa.api.enums.Currency;
import franroa.testing.helper.TestRequest;


import java.sql.Timestamp;
import java.time.Instant;

public class RequestFactory {
    private static Faker faker = new Faker();

    public static OfferRequest offer() {
        Timestamp expiresAt = Timestamp.from(Instant.ofEpochMilli(System.currentTimeMillis() + faker.number().numberBetween(1, 1000) * 60 * 1000));

        OfferRequest offerRequest = new OfferRequest();
        offerRequest.name = faker.name().name();
        offerRequest.price = (long) faker.number().numberBetween(10, 1000);
        offerRequest.currency = Currency.EUR.toString();
        offerRequest.expires_at = expiresAt.toString();

        return offerRequest;
    }
}
