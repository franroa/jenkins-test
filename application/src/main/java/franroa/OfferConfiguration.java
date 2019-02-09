package franroa;

import com.fasterxml.jackson.annotation.JsonProperty;
import franroa.queue.config.QueueConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OfferConfiguration extends Configuration {
    private static final String DATABASE_PASSWORD_PATH = "secrets/database";

    @JsonProperty("queue")
    private QueueConfiguration queue;

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();


    public DataSourceFactory getDataSourceFactory() {
        database.setPassword(readDatabasePassword().trim());

        return database;
    }

    public QueueConfiguration getQueueConfiguration() {
        return queue != null ? queue : new QueueConfiguration();
    }

    private String readDatabasePassword() {
        try {
            return new String(Files.readAllBytes(Paths.get(DATABASE_PASSWORD_PATH)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
