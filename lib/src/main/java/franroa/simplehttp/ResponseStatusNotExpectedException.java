package franroa.simplehttp;

import com.fasterxml.jackson.databind.JsonNode;

public class ResponseStatusNotExpectedException extends Exception {
    private String message;
    private Integer statusCode = null;
    private JsonNode jsonResponse = null;

    public ResponseStatusNotExpectedException(String message, int statusCode, JsonNode entityResponse) {
        this.statusCode = statusCode;
        this.message = message;
        this.jsonResponse = entityResponse;
    }

    public ResponseStatusNotExpectedException(String message, int statusCode) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getResponseMessage() {
        if (jsonResponse != null && jsonResponse.get("error_message") != null) {
            return jsonResponse.get("error_message").textValue();
        }

        return getMessage();
    }
}
