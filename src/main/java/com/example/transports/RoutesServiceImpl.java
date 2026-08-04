package com.example.transports;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;


public class RoutesServiceImpl extends RouteServiceGrpc.RouteServiceImplBase implements ServiceImpl<Path, Root> {
    @Override
    public void getRoutes(Path request, StreamObserver<Root> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(Path request) {
        return "?origin=" + request.getOrigin()
                + "&destination=" + request.getDestination();
    }

    @Override
    public String getEndpoint() {
        return "routes";
    }

    @Override
    public String getCapiHost(Path request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(Path request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return Root.newBuilder();
    }
}
