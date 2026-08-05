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
        String httpArguments = "?location=" + request.getLocation()
            + "&stopId=" + request.getStopId()
            + "&regionName=" + request.getRegionName()
            + "&countryIso=" + request.getCountryIso();
        
        if (request.getRequestTime() != null) {
            httpArguments += "&requestTime=" + request.getRequestTime();
        }
        if (request.getRadius() != 0) {
            httpArguments += "&radius=" + request.getRadius();
        }
        if (request.getResults() != 0) {
            httpArguments += "&results=" + request.getResults();
        }
        if (request.getBarrierMode() != 0) {
            httpArguments += "&barrierMode=" + request.getBarrierMode();
        }
        if (request.getLang() != null) {
            httpArguments += "&lang=" + request.getLang();
        }
        
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
