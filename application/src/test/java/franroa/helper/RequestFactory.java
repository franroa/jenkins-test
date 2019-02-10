package franroa.helper;

import com.github.javafaker.Faker;
import franroa.api.enums.Currency;
import franroa.testing.helper.TestRequest;


import java.sql.Timestamp;
import java.time.Instant;

public class RequestFactory {
    private static Faker faker = new Faker();

    public static TestRequest offer() {
        Timestamp expiresAt = Timestamp.from(Instant.ofEpochMilli(System.currentTimeMillis() + faker.number().numberBetween(1, 1000) * 60 * 1000));

        return new TestRequest()
                .set("name", faker.name().name())
                .set("price", faker.number().numberBetween(10, 1000))
                .set("currency", Currency.EUR.toString())
                .set("expires_at", expiresAt.toString());
    }
}
