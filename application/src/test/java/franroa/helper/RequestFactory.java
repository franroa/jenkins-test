package franroa.helper;

public class RequestFactory {
    public static TestRequest offer() {
        return new TestRequest()
                .set("name", "New Offer")
                .set("price", "4€");
    }
}
