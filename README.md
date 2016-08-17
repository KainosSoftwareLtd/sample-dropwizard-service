# Dropwizard Courtesy Client Sample App

Sample project for [Dropwizard 1.0.0](http://www.dropwizard.io/1.0.0/docs/manual/index.html) demonstrating the [courtesy client pattern](http://willhamill.com/2016/07/17/dropwizard-courtesy-client-design-pattern).

The sample service requires Java 1.8 and [Gradle 2.9 or higher](https://gradle.org/gradle-download/)

For a sample service compatible with [Dropwizard 0.9.3](http://www.dropwizard.io/0.9.3/docs/manual/index.html) check out the [dropwizard-0.9.3](https://github.com/KainosSoftwareLtd/sample-dropwizard-service/releases/tag/dropwizard-0.9.3) release

## Project Structure

The project contains three subprojects hosting the API, the application (service), and a courtesy client.
Individual project structure is based on the Dropwizard recommended model. This results in the following
package layout:
```
com.sample
    |- api
    |- client
        |- configuration
    |- service
        |- domain
        |- health
        |- resource
        |- service
```

## Build Artifacts
There are three artifacts produced during a standard build:
1. sample-service
2. sample-client
3. sample-api

The dependencies are set up as follows: service -> api <- client. This allows us to provide downstream
services with the client as a courtesy, saving effort implementing the service's interface but without
depending on it directly. Downstream services need only update their version of the client if a new version
of the client has been produced because the API interface implemented by the service has changed.


## Run Dropwizard service

Build, package and run the service:

```
./go
```

To unit test:

```
gradle test
```

To integration test:

```
gradle integrationTest
```

The integration test provided currently does only the following:
1. Run the service
2. Call the service HTTP API using the client


## Auto Generated Documentation

[Swagger](http://swagger.io/) documentation is available for this service by visiting the following file path in your browser:
`http://localhost:4560/swagger`

See the PersonResource class for information on how to annotate the methods so they are displayed in the documentation.

[RAML](http://raml.org) API documentation is also hosted inside the sample-service, available on the following path:
`http://localhost:4560/api`

The RAML API documentation is generated from a RAML API specification file, `apispec.raml`, in the root of the project directory.
