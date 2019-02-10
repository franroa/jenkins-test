package franroa.config;

import javax.validation.constraints.NotNull;

public class InterviewClientConfiguration {
    @NotNull
    public SupportedClients client;

    public String host;
}
