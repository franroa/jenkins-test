package franroa;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import franroa.config.Connection;
import franroa.container.Container;
import franroa.exceptions.mappers.InternalServerErrorExceptionMapper;
import franroa.exceptions.mappers.JsonParseExceptionMapper;
import franroa.exceptions.mappers.ResourceNotFoundExceptionMapper;
import franroa.listeners.DatabaseApplicationListener;
import franroa.queue.Queue;
import franroa.queue.QueueFactory;
import franroa.resources.OfferResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

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
        bindClasses(configuration);
    }

    private void registerInversionOfControl() {
        Container.singleton(Queue.class, new QueueFactory(configuration.getQueueConfiguration()));
    }

    private void bindClasses(OfferConfiguration configuration) {
        Container.instance(OfferConfiguration.class, configuration);
    }

    private void registerExceptionMappers() {
        environment.jersey().register(new ResourceNotFoundExceptionMapper());
        environment.jersey().register(new InternalServerErrorExceptionMapper());
        environment.jersey().register(new JsonMappingExceptionMapper());
        environment.jersey().register(new JsonParseExceptionMapper());
    }

    private void registerResources() {
        environment.jersey().register(new OfferResource());
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
            Liquibase migrator = new Liquibase(MIGRATIONS_MASTER_FILE_PATH, new ClassLoaderResourceAccessor(), new JdbcConnection(Connection.get()));
            migrator.update("");
        } catch (LiquibaseException e) {
            e.printStackTrace();
        } finally {
            Connection.close();
        }
    }
}
