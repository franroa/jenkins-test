package franroa.helper;

import franroa.api.enums.Currency;

public class RequestFactory {
    public static TestRequest offer() {
        return new TestRequest()
                .set("name", "New Offer")
                .set("price", "4")
                .set("currency", Currency.EURO.toString());
    }
}
