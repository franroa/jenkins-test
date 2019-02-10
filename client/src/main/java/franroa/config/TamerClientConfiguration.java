package franroa.config;

import javax.validation.constraints.NotNull;

public class TamerClientConfiguration {
    @NotNull
    public SupportedClients client;

    public String host;
}
