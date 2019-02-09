package franroa.helper;

import com.github.javafaker.Faker;
import franroa.api.enums.Currency;
import franroa.core.Offer;


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
        Offer offer = new Offer();
        offer.set("name", faker.name().firstName());
        offer.set("price", faker.number().randomNumber());
        offer.set("currency", Currency.EUR.toString());
        offer.saveIt();

        return offer;
    }
}
