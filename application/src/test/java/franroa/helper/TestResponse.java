package franroa.helper;

import com.fasterxml.jackson.databind.JsonNode;
import franroa.api.error.ResourceNotFoundResponse;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.IterableAssert;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Assert;


import javax.ws.rs.core.Response;

public class TestResponse<T> {
    private final JsonNode jsonResponse;
    private Response response;

    public TestResponse(Response response) {
        this.jsonResponse = response.readEntity(JsonNode.class);
        this.response = response;
    }

    public T assertStatus(int status) {
        Assertions.assertThat(this.response.getStatus()).isEqualTo(status);
        return (T) this;
    }

    public T assertDataInArrayNode(String value) {
        for (int i = 0; i < jsonResponse.size(); i++) {
            if (jsonResponse.get(i) != null && jsonResponse.get(i).asText().equals(value)) {
                return (T) this;
            }
        }

        Assert.fail("value : " + value + " was not found in the array");
        return (T) this;
    }

    public T assertDataInArrayNode(String key, String value) {
        if (!jsonResponse.get(key).isArray()) {
            Assert.fail("value : " + value + " was not found in the array");
            return (T) this;
        }

        if (isValueInJsonNode(value, jsonResponse.get(key))) return (T) this;

        Assert.fail("value : " + value + " was not found in the array");
        return (T) this;
    }

    private boolean isValueInJsonNode(String value, JsonNode node) {
        for (int i = 0; i < node.size(); i++) {
            if (node.get(i) != null && node.get(i).asText().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public T assertDataInArrayNode(Integer index, String value) {
        if (jsonResponse.get(index) != null && jsonResponse.get(index).asText().equals(value)) {
            return (T) this;
        }

        Assert.fail("value : " + value + " was not found in the array");
        return (T) this;
    }

    public T assertData(String key, String value) {
        this.assertKeyExists(key);
        Assertions.assertThat(this.jsonResponse.get(key).asText()).isEqualTo(value);

        return (T) this;
    }

    public T assertData(String keyParent, Integer index, String key, String value) {
        Assertions.assertThat(this.jsonResponse.get(keyParent).get(index).get(key).asText()).isEqualTo(value);

        return (T) this;
    }

    public T assertKeyExists(String key) {
        ((IterableAssert) Assertions.assertThat(this.jsonResponse).overridingErrorMessage("The key '" + key + "' does not exist in {}", new Object[0])).isNotNull();
        ((IterableAssert) Assertions.assertThat(this.jsonResponse.get(key)).overridingErrorMessage("The key '" + key + "' does not exist in " + this.jsonResponse.toString(), new Object[0])).isNotNull();

        return (T) this;
    }

    public void assertUnprocessableEntityError(String errors) {
        assertStatus(HttpStatus.UNPROCESSABLE_ENTITY_422);
        assertDataInArrayNode("errors", errors);
    }

    public void assertHeader(String key, String value) {
        Assertions.assertThat(this.response.getHeaderString(key)).isEqualTo(value);
    }

    public T assertResourceNotFound() {
        assertStatus(ResourceNotFoundResponse.HTTP_CODE);
        assertData("error_code", ResourceNotFoundResponse.ERROR_CODE);
        assertData("error_message", ResourceNotFoundResponse.ERROR_MESSAGE);

        return (T) this;
    }
}
