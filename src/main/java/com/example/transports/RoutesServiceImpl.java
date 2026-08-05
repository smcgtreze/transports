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
        String httpArguments = "?origin=" + request.getOrigin()
                + "&destination=" + request.getDestination();
        
        if (request.getArrivalTime() != null) {
            httpArguments += "&arrivalTime=" + request.getArrivalTime();
        }
        if (request.getDepartureTime() != null) {
            httpArguments += "&departureTime=" + request.getDepartureTime();
        }
        if (request.getTransfers() != null) {
            httpArguments += "&transfers=" + request.getTransfers();
        }
        if (request.getGeometry() != null) {
            httpArguments += "&geometry=" + request.getGeometry();
        }
        if (request.getTransport() != null) {
            httpArguments += "&transport=" + request.getTransport();
        }
        if (request.getMaxRoutes() != null) {
            httpArguments += "&maxRoutes=" + request.getMaxRoutes();
        }
        if (request.getLang() != null) {
            httpArguments += "&lang=" + request.getLang();
        }

        return httpArguments;
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
