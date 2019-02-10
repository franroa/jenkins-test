package franroa.simplehttp;


import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;


import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class SimpleResponse {
    private Response response = null;

    public SimpleResponse(Response response) {
        this.response = response;
    }

    private SimpleResponse checkStatus(ArrayList httpStatus) throws ResponseStatusNotExpectedException {
        if (httpStatus.contains(response.getStatus())) {
            return this;
        }

        throw new ResponseStatusNotExpectedException(
                "The given status { " + response.getStatus() + " } is not expected in response",
                response.getStatus(),
                this.asJsonNode()
        );
    }

    public SimpleResponse expectStatus(ArrayList httpStatus) throws ResponseStatusNotExpectedException {
        return checkStatus(httpStatus);
    }

    public SimpleResponse expectStatus(int httpStatus) throws ResponseStatusNotExpectedException {
        return checkStatus(Lists.newArrayList(httpStatus));
    }

    public JsonNode asJsonNode() {
        try {
            return response.readEntity(JsonNode.class);
        } catch (Exception e) {
            throw new UnableToReadEntityException("The entity could not be read", e);
        } finally {
            closeResponse(response);
        }
    }

    public void closeResponse(Response response) {
        if (response != null) {
            response.close();
        }
    }

    public int getStatus() {
        return response.getStatus();
    }

    public String getMessage() {
        return response.getEntity().toString();
    }

    public Response toResponse() {
        return response;
    }

    public <T> T asDto(Class<T> clazz) {
        try {
            return response.readEntity(clazz);
        } catch (Exception e) {
            throw new UnableToReadEntityException("The entity could not be read", e);
        } finally {
            closeResponse(response);
        }
    }

    public <T> List<T> asDtoList() {
        try {
            return response.readEntity(new GenericType<List<T>>() {
            });
        } catch (Exception e) {
            throw new UnableToReadEntityException("The entity could not be read", e);
        } finally {
            closeResponse(response);
        }
    }
}
