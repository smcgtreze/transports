package com.example.transports;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

public class StopsInRadiusServiceImpl extends StopsInRadiusServiceGrpc.StopsInRadiusServiceImplBase implements ServiceImpl<StopsInRadiusRequest, StopsInRadiusResponse> {
    @Override
    public void getStopsInRadius(StopsInRadiusRequest request, StreamObserver<StopsInRadiusResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(StopsInRadiusRequest request) {
        String httpArguments = "";
        httpArguments = appendQueryParam(httpArguments, "location", request.getLocation());
        httpArguments = appendQueryParam(httpArguments, "radius", request.getRadius());
        httpArguments = appendQueryParam(httpArguments, "limit", request.getLimit());

        return httpArguments;
    }

    @Override
    public String getEndpoint() {
        return "stopsInRadius";
    }

    @Override
    public String getCapiHost(StopsInRadiusRequest request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(StopsInRadiusRequest request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return StopsInRadiusResponse.newBuilder();
    }
}
