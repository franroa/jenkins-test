package franroa.dto.error;

import org.eclipse.jetty.http.HttpStatus;

import java.util.ArrayList;

public class ValueNotInListResponse extends AbstractErrorResponse {

    public static final Integer HTTP_CODE = HttpStatus.UNPROCESSABLE_ENTITY_422;
    public static final String ERROR_CODE = "INVALID_PARAMETER";
    public static final String ERROR_MESSAGE = "%s must be one of [%s]";

    public ValueNotInListResponse(String errorMessage, ArrayList<String> affectedFields) {
        this.errorCode = ERROR_CODE;
        this.errorMessage = errorMessage;
        this.affectedFields = affectedFields;
    }
}
