package com.sample;

import com.sample.health.BasicHealthCheck;
import com.sample.resource.PersonResource;
import com.sample.service.PersonService;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class SampleApplication extends Application<SampleConfiguration> {
    public static void main(String[] args) throws Exception {
        new SampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "sample-dropwizard-service";
    }

    @Override
    public void initialize(Bootstrap<SampleConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<SampleConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(SampleConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(SampleConfiguration configuration, Environment environment) throws ClassNotFoundException {
        final PersonService personService = new PersonService();
        final PersonResource personResource = new PersonResource(personService);

        environment.jersey().register(personResource);

        environment.healthChecks().register("sample-service", new BasicHealthCheck());
    }
}
