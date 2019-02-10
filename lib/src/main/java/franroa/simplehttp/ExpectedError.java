package franroa.simplehttp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.ws.rs.WebApplicationException;
import java.io.IOException;

public class ExpectedError {
    public Integer statusCode = null;
    public JsonNode jsonResponse = null;
    public String message = null;

    public static ExpectedError unhandled(int statusCode, String message) {
        ExpectedError error = new ExpectedError();
        error.statusCode = statusCode;
        error.message = message;

        return error;
    }

    public ExpectedError() {
    }

    public ExpectedError(Integer status, String message) {
        this.statusCode = status;
        this.message = message;
        try {
            this.jsonResponse = new ObjectMapper().readTree("{ \"error_message\": \"" + message + "\" }");
        } catch (IOException e) {
            throw new WebApplicationException("The object 'ExpectedError' could not be created");
        }
    }
}
