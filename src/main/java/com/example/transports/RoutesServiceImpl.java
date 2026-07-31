package com.example.transports;

import io.grpc.stub.StreamObserver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.util.JsonFormat;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.example.transports.*;
import com.example.transports.Path;


public class RoutesServiceImpl extends RouteServiceGrpc.RouteServiceImplBase {
        @Override
    public void getRoutes(Path request, StreamObserver<Root> responseObserver) {

        try {
            // Build your HTTPS URL
            String url = "https://capi." + request.getCapiHost() + ":8443/routes"
                + "?origin=" + request.getOrigin()
                + "&destination=" + request.getDestination();

            var httpClient = HttpClient.newHttpClient();

            var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("capi-key", "Bearer " + request.getApiKey())
                .header("capi-host", request.getCapiHost())
                .GET()
                .build();

            var httpResponse = httpClient.send(
                httpRequest,
                HttpResponse.BodyHandlers.ofString()
            );

            String json = httpResponse.body();

            // Parse entire JSON into Root
            Root.Builder builder = Root.newBuilder();
            JsonFormat.parser().ignoringUnknownFields().merge(json, builder);
            Root root = builder.build();

            // Return the Root object
            responseObserver.onNext(root);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

}