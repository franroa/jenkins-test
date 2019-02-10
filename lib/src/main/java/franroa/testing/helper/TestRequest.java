package franroa.testing.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestRequest {
    private ObjectNode data;
    private ObjectNode currentNode;

    public TestRequest() {
        this.data = new ObjectMapper().createObjectNode();
        resetCurrentNodeToOriginNode();
    }

    public TestRequest(ObjectNode data) {
        this.data = data;

        resetCurrentNodeToOriginNode();
    }

    public TestRequest set(String key, String value) {
        currentNode.put(key, value);

        resetCurrentNodeToOriginNode();

        return this;
    }

    public TestRequest set(String key, Integer value) {
        currentNode.put(key, value);

        resetCurrentNodeToOriginNode();

        return this;
    }

    private void resetCurrentNodeToOriginNode() {
        currentNode = data;
    }

    public Object getJson() {
        return data;
    }

    public TestRequest remove(String requiredField) {
        currentNode.remove(requiredField);

        resetCurrentNodeToOriginNode();

        return this;
    }
}
