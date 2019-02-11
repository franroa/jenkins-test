package franroa;

import franroa.config.Connection;
import franroa.container.Container;
import franroa.exceptions.mappers.ResourceNotFoundExceptionMapper;
import franroa.filter.CorrelationIdRequestFilter;
import franroa.filter.CorrelationIdResponseFilter;
import franroa.listeners.DatabaseApplicationListener;
import franroa.queue.Queue;
import franroa.queue.QueueFactory;
import franroa.queue.QueueManager;
import franroa.resources.OfferResource;
import franroa.resources.PingResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.glassfish.jersey.logging.LoggingFeature;


import java.util.logging.Level;
import java.util.logging.Logger;

public class OfferApplication extends Application<OfferConfiguration> {
    private static final String MIGRATIONS_MASTER_FILE_PATH = "changelog/master.xml";

    private Environment environment;
    private OfferConfiguration configuration;

    public static void main(final String[] args) throws Exception {
        new OfferApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<OfferConfiguration> bootstrap) {
    }

    @Override
    public String getName() {
        return "interview";
    }

    @Override
    public void run(final OfferConfiguration configuration, final Environment environment) {
        this.configuration = configuration;
        this.environment = environment;

        initializeDb();
        runMigrations();
        registerEventListeners();
        registerResources();
        registerExceptionMappers();
        registerInversionOfControl();
        registerFeatures();
        registerFilters();
        bindClasses(configuration);
    }

    private void registerFilters() {
        environment.jersey().register(new CorrelationIdRequestFilter(configuration.getCorrelationIdHeaderKey()));
        environment.jersey().register(new CorrelationIdResponseFilter(configuration.getCorrelationIdHeaderKey()));
    }

    private void registerFeatures() {
        environment.jersey().register(
                new LoggingFeature(Logger.getLogger("inbound"), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 8192)
        );
    }

    private void registerInversionOfControl() {
        environment.lifecycle().manage(new QueueManager());
        Container.singleton(Queue.class, new QueueFactory(configuration.getQueueConfiguration()));
    }

    private void bindClasses(OfferConfiguration configuration) {
        Container.instance(OfferConfiguration.class, configuration);
    }

    private void registerExceptionMappers() {
        environment.jersey().register(new ResourceNotFoundExceptionMapper());
    }

    private void registerResources() {
        environment.jersey().register(new OfferResource());
        environment.jersey().register(new PingResource());
    }

    private void registerEventListeners() {
        environment.jersey().register(new DatabaseApplicationListener());
    }

    private void initializeDb() {
        DataSourceFactory dataSourceFactory = configuration.getDataSourceFactory();
        ManagedDataSource ds = dataSourceFactory.build(environment.metrics(), "pool");
        Connection.setDatasource(ds);
    }

    private void runMigrations() {
        try {
            Connection.open();
            new Liquibase(MIGRATIONS_MASTER_FILE_PATH, new ClassLoaderResourceAccessor(), new JdbcConnection(Connection.get())).update("");
        } catch (LiquibaseException e) {
            e.printStackTrace();
        } finally {
            Connection.close();
        }
    }
}
