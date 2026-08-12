package com.example.transports.service;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;


import com.example.transports.*;
public class RoutesServiceImpl extends RouteServiceGrpc.RouteServiceImplBase implements ServiceImpl<RoutesRequest, RoutesResponse> {
    @Override
    public void getRoutes(RoutesRequest request, StreamObserver<RoutesResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(RoutesRequest request) {
        String httpArguments = "";
        httpArguments = appendQueryParam(httpArguments, "origin", request.getOrigin());
        httpArguments = appendQueryParam(httpArguments, "destination", request.getDestination());
        httpArguments = appendQueryParam(httpArguments, "arrivalTime", request.getArrivalTime());
        httpArguments = appendQueryParam(httpArguments, "departureTime", request.getDepartureTime());
        httpArguments = appendQueryParam(httpArguments, "transfers", request.getTransfers());
        httpArguments = appendQueryParam(httpArguments, "geometry", request.getGeometry());
        httpArguments = appendQueryParam(httpArguments, "transport", request.getTransport());
        httpArguments = appendQueryParam(httpArguments, "maxRoutes", request.getMaxRoutes());
        httpArguments = appendQueryParam(httpArguments, "lang", request.getLang());

        return httpArguments;
    }

    @Override
    public String getEndpoint() {
        return "routes";
    }

    @Override
    public String getCapiHost(RoutesRequest request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(RoutesRequest request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return RoutesResponse.newBuilder();
    }
}


