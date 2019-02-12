package franroa.api.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import franroa.filter.CorrelationIdRequestFilter;


import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbstractErrorResponse {
    @JsonProperty("error_code")
    public String errorCode = "";

    @JsonProperty("error_message")
    public String errorMessage = "";

    @JsonProperty("affected_fields")
    public ArrayList<String> affectedFields;
}
