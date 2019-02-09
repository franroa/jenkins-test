package franroa.helper;

import com.github.javafaker.Faker;
import franroa.api.enums.Currency;
import franroa.core.Offer;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ModelFactory {
    private static Faker faker = new Faker();

    public static List<Offer> offer(Integer amount) {
        List<Offer> offers = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            offers.add(offer());
        }

        return offers;
    }

    public static Offer offer() {
        Timestamp expiresAt = Timestamp.from(Instant.ofEpochMilli(System.currentTimeMillis() + faker.number().numberBetween(1, 1000) * 60 * 1000));

        Offer offer = new Offer();
        offer.set("name", faker.name().firstName());
        offer.set("price", faker.number().randomNumber());
        offer.set("currency", Currency.EUR.toString());
        offer.set("expires_at", expiresAt);
        offer.saveIt();

        return offer;
    }
}
