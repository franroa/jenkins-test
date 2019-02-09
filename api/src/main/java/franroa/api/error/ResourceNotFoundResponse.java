package franroa.api.error;

import org.eclipse.jetty.http.HttpStatus;

public class ResourceNotFoundResponse extends AbstractErrorResponse {
    public static final Integer HTTP_CODE = HttpStatus.NOT_FOUND_404;
    public static final String ERROR_CODE = "RESOURCE_NOT_FOUND";
    public static final String ERROR_MESSAGE = "The requested merchant resource could not been found.";

    public ResourceNotFoundResponse() {
        this.errorCode = ERROR_CODE;
        this.errorMessage = ERROR_MESSAGE;
    }
}
