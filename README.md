# transports

A Java transport data client and sample application using Maven, Protocol Buffers, and gRPC.

## Overview

This repository contains a Java project that consumes transport-related services using generated protobuf classes and gRPC. The sample `Main` class demonstrates both a direct HTTPS API request and a gRPC-style service call for next departures.

## Project Structure

- `pom.xml` - Maven build configuration with protobuf and gRPC plugin setup.
- `src/main/java` - Java source code.
- `src/main/proto` - Protocol Buffer service and message definitions.

## Requirements

- Java 17
- Maven 3.8+ (or compatible)
- Internet access for dependency download

## Build

From the project root:

```bash
mvn clean package
```

This will:
- compile Java sources
- run the protobuf plugin to generate Java classes from `.proto` files
- package project artifacts

## Run

After building, run the application with Maven:

```bash
mvn exec:java -Dexec.mainClass="com.example.transports.Main"
```

Or run the packaged application if you add an executable jar packaging step.

## Usage

`Main` includes a couple of sample actions:

- `executeSimpleGET()` performs a simple HTTPS GET request against a configured transport API host.
- `main(...)` creates a `NextDeparturesServiceImpl` instance and requests next departures for a fixed location.

Important configuration values are currently hardcoded in `Main`:
- `httpPort` = `8443`
- `capiKey` = `825858535684745631cd5fef5c1626ee`
- `capiHost` = `busmaps.com`

## Protobuf and gRPC

The project uses `protobuf-maven-plugin` to generate Java classes from the `.proto` files under `src/main/proto`.

Generated classes are placed in:
- `target/generated-sources/protobuf/java`

The plugin also generates gRPC service stubs using `protoc-gen-grpc-java`.

## Dependencies

Key dependencies include:

- `io.grpc:grpc-netty-shaded`
- `io.grpc:grpc-protobuf`
- `io.grpc:grpc-stub`
- `com.google.protobuf:protobuf-java`
- JUnit Jupiter for unit testing

## Notes

- The project is configured for Java 17.
- If running behind a proxy or different environment, ensure the Maven settings and network access allow dependency downloads.
- Update hardcoded API/host values in `Main` before using in production.
