package com.example.transports;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;


public class NextDeparturesServiceImpl extends NextDeparturesServiceGrpc.NextDeparturesServiceImplBase implements ServiceImpl<NextDeparturesRequest, NextDeparturesResponse> {
    @Override
    public void getNextDepartures(NextDeparturesRequest request, StreamObserver<NextDeparturesResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(NextDeparturesRequest request) {
        String httpArguments = "";
        httpArguments = appendQueryParam(httpArguments, "location", request.getLocation());
        httpArguments = appendQueryParam(httpArguments, "countryIso", request.getCountryIso());
        httpArguments = appendQueryParam(httpArguments, "stopId", request.getStopId());
        httpArguments = appendQueryParam(httpArguments, "regionName", request.getRegionName());
        httpArguments = appendQueryParam(httpArguments, "requestTime", request.getRequestTime());
        httpArguments = appendQueryParam(httpArguments, "radius", request.getRadius(), false);
        httpArguments = appendQueryParam(httpArguments, "results", request.getResults(), false);
        httpArguments = appendQueryParam(httpArguments, "barrierMode", request.getBarrierMode(), false);
        httpArguments = appendQueryParam(httpArguments, "lang", request.getLang());

        return httpArguments;
    }

    @Override
    public String getEndpoint() {
        return "nextDepartures";
    }

    @Override
    public String getCapiHost(NextDeparturesRequest request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(NextDeparturesRequest request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return NextDeparturesResponse.newBuilder();
    }
}
