package franroa.api.error;

import org.eclipse.jetty.http.HttpStatus;

import java.util.ArrayList;

public class MissingParameterResponse extends AbstractErrorResponse {

    public static final Integer HTTP_CODE = HttpStatus.UNPROCESSABLE_ENTITY_422;
    public static final String ERROR_CODE = "MISSING_PARAMETER";
    public static final String ERROR_MESSAGE = "The following mandatory fields are missing: %s";

    public MissingParameterResponse(String errorMessage, ArrayList<String> affectedFields) {
        this.errorCode = ERROR_CODE;
        this.errorMessage = errorMessage;
        this.affectedFields = affectedFields;
    }
}
