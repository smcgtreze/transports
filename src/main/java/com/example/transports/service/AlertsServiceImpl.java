package com.example.transports.service;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

import com.example.transports.*;
public class AlertsServiceImpl extends AlertsServiceGrpc.AlertsServiceImplBase implements ServiceImpl<AlertsRequest, AlertsResponse> {
    @Override
    public void getAlerts(AlertsRequest request, StreamObserver<AlertsResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(AlertsRequest request) {
        String httpArguments = "";
        httpArguments = appendQueryParam(httpArguments, "regionName", request.getRegionName());
        httpArguments = appendQueryParam(httpArguments, "countryIso", request.getCountryIso());
        httpArguments = appendQueryParam(httpArguments, "routeId", request.getRouteId());
        httpArguments = appendQueryParam(httpArguments, "operatorId", request.getOperatorId());
        httpArguments = appendQueryParam(httpArguments, "lang", request.getLang());

        return httpArguments;
    }

    @Override
    public String getEndpoint() {
        return "alerts";
    }

    @Override
    public String getCapiHost(AlertsRequest request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(AlertsRequest request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return AlertsResponse.newBuilder();
    }
}


