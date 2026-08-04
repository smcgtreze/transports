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
import com.example.transports.NextDeparturesRequest;


public class NextDeparturesServiceImpl extends NextDeparturesServiceGrpc.NextDeparturesServiceImplBase {
    @Override
    public void getNextDepartures(NextDeparturesRequest request, StreamObserver<NextDeparturesResponse> responseObserver) {
        try {
            // Build your HTTPS URL
            String url = "https://capi." + request.getKey().getCapiHost() + ":8443/nextDepartures"
                + "?location=" + request.getLocation()
                + "&stopId=" + request.getStopId()
                + "&regionName=" + request.getRegionName()
                + "&countryIso=" + request.getCountryIso();

            var httpClient = HttpClient.newHttpClient();

            var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("capi-key", "Bearer " + request.getKey().getApiKey())
                .header("capi-host", request.getKey().getCapiHost())
                .GET()
                .build();

            var httpResponse = httpClient.send(
                httpRequest,
                HttpResponse.BodyHandlers.ofString()
            );

            String json = httpResponse.body();

            // Parse entire JSON into NextDeparturesResponse                
            NextDeparturesResponse.Builder builder = NextDeparturesResponse.newBuilder();
            JsonFormat.parser().ignoringUnknownFields().merge(json, builder);
            NextDeparturesResponse nextDeparturesResponse = builder.build();

            // Return the NextDeparturesResponse object
            responseObserver.onNext(nextDeparturesResponse);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
    
}
