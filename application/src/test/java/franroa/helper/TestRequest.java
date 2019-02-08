package franroa.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import java.io.File;

public class TestRequest {
    private ObjectNode data;
    private ObjectNode currentNode;

    public TestRequest() {
        this(new ObjectMapper().createObjectNode());
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

    public FormDataMultiPart getEpubMultipart() {
        final FileDataBodyPart filePart = new FileDataBodyPart("file", new File("src/test/resources/test-book.epub"));
        final FormDataMultiPart multipart = new FormDataMultiPart();
        multipart.bodyPart(filePart);
        return multipart;
    }
}
