package franroa.exceptions.mappers;


import franroa.api.error.ResourceNotFoundResponse;
import franroa.exceptions.ResourceNotFoundException;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
    @Override
    public Response toResponse(ResourceNotFoundException e) {
        return Response.status(ResourceNotFoundResponse.HTTP_CODE).entity(new ResourceNotFoundResponse()).build();
    }
}
