package franroa.simplehttp;


import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


public class SimpleHttp {
    private final Client client;
    private final String host;
    private String method = "";
    private String url = "";
    private String correlationIdKey = "";
    private String correlationId = "";
    private Object request = null;
    private String mediaType = APPLICATION_JSON;

    public SimpleHttp(String host, String correlationIdKey, String correlationId) {
        this.client = createClient();
        this.host = host;
        this.correlationIdKey = correlationIdKey;
        this.correlationId = correlationId;
    }

    public SimpleResponse post(String url, Object request) {
        this.method = "POST";
        this.url = url;
        this.request = request;

        return getSimpleResponse();
    }

    public SimpleResponse patch(String url, Object request) {
        this.method = "PATCH";
        this.url = url;
        this.request = request;

        return getSimpleResponse();
    }

    public SimpleResponse put(String url, Object request) {
        this.method = "PUT";
        this.url = url;
        this.request = request;

        return getSimpleResponse();
    }

    public SimpleResponse get(String url) {
        this.method = "GET";
        this.url = url;
        this.request = null;

        return getSimpleResponse();
    }

    public SimpleResponse delete(String url) {
        this.method = "DELETE";
        this.url = url;
        this.request = null;

        return getSimpleResponse();
    }

    public SimpleHttp mediaType(String mediaType) {
        this.mediaType = mediaType;

        return this;
    }

    private SimpleResponse getSimpleResponse() {
        if (request == null) {
            return new SimpleResponse(requestTo(url).method(method));
        } else {
            return new SimpleResponse(requestTo(url).method(method, Entity.entity(request, mediaType)));
        }
    }

    private Invocation.Builder requestTo(String endpoint) {
        return client.target(host + "/" + endpoint)
                .request()
                // .header("Authorization", "Bearer " + authorizationClient.getAccessToken())
                .header(correlationIdKey, correlationId)
                .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
    }

    private Client createClient() {
        return ClientBuilder.newClient()
                .property(ClientProperties.CONNECT_TIMEOUT, 10000)
                .property(ClientProperties.READ_TIMEOUT, 10000);
    }
}
