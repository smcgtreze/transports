package com.example.transports.service;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

import com.example.transports.*;
public class LineServiceImpl extends LineServiceGrpc.LineServiceImplBase implements ServiceImpl<LineRequest, LineResponse> {
    @Override
    public void getLine(LineRequest request, StreamObserver<LineResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(LineRequest request) {
        String httpArguments = "";
        httpArguments = appendQueryParam(httpArguments, "routeHash2", request.getRouteHash2());
        httpArguments = appendQueryParam(httpArguments, "countryIso", request.getCountryIso());
        httpArguments = appendQueryParam(httpArguments, "regionName", request.getRegionName());
        httpArguments = appendQueryParam(httpArguments, "lang", request.getLang());
        httpArguments = appendQueryParam(httpArguments, "date", request.getDate());

        return httpArguments;
    }

    @Override
    public String getEndpoint() {
        return "line";
    }

    @Override
    public String getCapiHost(LineRequest request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(LineRequest request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return LineResponse.newBuilder();
    }
}


