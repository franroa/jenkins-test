package franroa.exceptions.mappers;

import com.fasterxml.jackson.databind.JsonMappingException;
import franroa.api.error.MalformedPayloadResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
    @Override
    public Response toResponse(JsonMappingException exception) {
        MalformedPayloadResponse response = new MalformedPayloadResponse(MalformedPayloadResponse.ERROR_MESSAGE, null);
        return Response.status(MalformedPayloadResponse.HTTP_CODE).entity(response).build();
    }
}
