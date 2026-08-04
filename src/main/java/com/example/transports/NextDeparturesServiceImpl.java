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
        return "?location=" + request.getLocation()
            + "&stopId=" + request.getStopId()
            + "&regionName=" + request.getRegionName()
            + "&countryIso=" + request.getCountryIso();
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
