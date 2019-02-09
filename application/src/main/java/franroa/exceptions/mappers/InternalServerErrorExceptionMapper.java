package franroa.exceptions.mappers;

import franroa.api.error.InternalServerErrorResponse;
import org.slf4j.LoggerFactory;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InternalServerErrorExceptionMapper implements ExceptionMapper<InternalServerErrorException> {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(InternalServerErrorExceptionMapper.class);

    @Override
    public Response toResponse(InternalServerErrorException e) {
        LOGGER.error("An Exception was thrown", e);

        return Response.status(InternalServerErrorResponse.HTTP_CODE).entity(new InternalServerErrorResponse()).build();
    }
}
