package franroa.feature;

import franroa.OfferConfiguration;
import franroa.container.Container;
import franroa.exceptions.mappers.ResourceNotFoundExceptionMapper;
import franroa.helper.TestResponse;
import franroa.resources.OfferResource;
import io.dropwizard.configuration.ConfigurationException;
import io.dropwizard.configuration.YamlConfigurationFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.ResourceTestRule;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.logging.LogFactory;
import liquibase.logging.LogLevel;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;


import javax.validation.Validation;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class FeatureTestEnvironment {
    protected static OfferConfiguration config;
    private static boolean migrated = false;

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new OfferResource())
            .addResource(new ResourceNotFoundExceptionMapper())
            .build();

    @BeforeClass
    public static void setUp() throws Exception {
        loadConfiguration();
        Container.instance(OfferConfiguration.class, config);
        openDatabaseConnection();
        migrate();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        closeDatabaseConnection();
    }

    @Before
    public void startTransaction() {
        Base.openTransaction();
    }

    @After
    public void rollbackTransaction() {
        Base.rollbackTransaction();
    }

    private static void openDatabaseConnection() {
        Base.open("org.postgresql.Driver", "jdbc:postgresql://localhost:54322/interviewDB-test", "sa", "sa");
    }

    private static void loadConfiguration() {
        if(config == null) {
            try {
                config = (OfferConfiguration)(new YamlConfigurationFactory(OfferConfiguration.class, Validation.buildDefaultValidatorFactory().getValidator(), Jackson.newObjectMapper(), "dw")).build(new File(ResourceHelpers.resourceFilePath("config.yml")));
            } catch (IOException | ConfigurationException var2) {
                throw new RuntimeException(var2);
            }
        }
    }

    private static void closeDatabaseConnection() {
        Base.close();
    }

    private static void migrate() throws LiquibaseException, SQLException {
        if (migrated) {
            return;
        }

        LogFactory.getInstance().getLog().setLogLevel(LogLevel.WARNING);
        new Liquibase("changelog/master.xml", new ClassLoaderResourceAccessor(), new JdbcConnection(Base.connection())).update("");
        Base.connection().setAutoCommit(true);
        migrated = true;
    }

    protected <T> TestResponse put(String target, Object request) {
        return new TestResponse(requestTo(target).put(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE)));
    }

    protected <T> TestResponse post(String target, Entity<T> entity) {
        return new TestResponse(requestTo(target, entity).post(entity));
    }

    protected <T> TestResponse post(String target, Object request) {
        return new TestResponse(requestTo(target).post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE)));
    }

    protected TestResponse get(String target) {
        return new TestResponse(requestTo(target).get());
    }

    private Invocation.Builder requestTo(String target) {
        return resources.client().target(target).request();
    }

    private <T> Invocation.Builder requestTo(String target, Entity<T> entity) {
        return resources.client().register(MultiPartFeature.class).target(target).request();
    }
}
