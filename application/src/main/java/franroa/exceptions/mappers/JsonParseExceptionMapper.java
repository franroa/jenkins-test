package franroa.exceptions.mappers;

import com.fasterxml.jackson.core.JsonParseException;
import franroa.api.error.MalformedPayloadResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.ArrayList;

public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {
    @Override
    public Response toResponse(JsonParseException exception) {
        return Response.status(MalformedPayloadResponse.HTTP_CODE)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new MalformedPayloadResponse(MalformedPayloadResponse.ERROR_MESSAGE, new ArrayList<>()))
                .build();
    }
}
