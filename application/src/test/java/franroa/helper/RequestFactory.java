package franroa.helper;

public class RequestFactory {
    public static TestRequest withExpressionTranslations() {
        return new TestRequest()
                .set("from_id", 10)
                .set("to_id", 21)
                .set("translation", "translated text")
                .set("original_text", "original text to be translated");
    }
}
