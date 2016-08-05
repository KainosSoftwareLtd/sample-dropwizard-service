package com.sample;

import com.sample.api.PersonResponse;
import com.sample.client.PersonClient;
import com.sample.client.configuration.ClientConfiguration;

import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertTrue;

public class SampleServiceIntegrationTest {

    private static PersonClient client;

    @ClassRule
    public static final DropwizardAppRule<SampleConfiguration> RULE =
            new DropwizardAppRule<SampleConfiguration>(SampleApplication.class, ResourceHelpers.resourceFilePath("config.yml"));

    @BeforeClass
    public static void setupClass() {

        // setup service
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.host = "http://localhost";
        clientConfiguration.port = RULE.getLocalPort();
        clientConfiguration.serviceContext = "people";

        JerseyClientConfiguration jerseyConfiguration = new JerseyClientConfiguration();

        client = new PersonClient(RULE.getEnvironment(), clientConfiguration, jerseyConfiguration);
    }

    @Test
    public void getAll_returnsListWithAxelInIt() {
        final String expectedName = "John Smith";

        List<PersonResponse> resultList = client.getAll();

        Predicate<PersonResponse> expected = person -> person.getName().equals(expectedName);
        assertTrue(resultList.stream().anyMatch(expected));
    }
}
