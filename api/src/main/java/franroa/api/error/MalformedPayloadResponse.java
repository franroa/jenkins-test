package franroa.api.error;

import org.eclipse.jetty.http.HttpStatus;

import java.util.ArrayList;

public class MalformedPayloadResponse extends AbstractErrorResponse {
    public static final Integer HTTP_CODE = HttpStatus.BAD_REQUEST_400;
    public static final String ERROR_CODE = "MALFORMED_PAYLOAD";
    public static final String ERROR_MESSAGE = "Invalid JSON";

    public MalformedPayloadResponse(String errorMessage, ArrayList<String> affectedFields) {
        this.errorCode = ERROR_CODE;
        this.errorMessage = errorMessage;
        this.affectedFields = affectedFields;
    }
}
