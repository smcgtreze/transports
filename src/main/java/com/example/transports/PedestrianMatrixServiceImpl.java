package com.example.transports;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

public class PedestrianMatrixServiceImpl extends PedestrianMatrixServiceGrpc.PedestrianMatrixServiceImplBase implements ServiceImpl<PedestrianMatrixRequest, PedestrianMatrixResponse> {
    @Override
    public void getPedestrianMatrix(PedestrianMatrixRequest request, StreamObserver<PedestrianMatrixResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(PedestrianMatrixRequest request) {
        String httpArguments = "";

        if (!request.getSourceLocationsList().isEmpty()) {
            httpArguments = appendQueryParam(httpArguments, "sourceLocations", String.join(",", request.getSourceLocationsList()));
        }
        if (!request.getDestinationLocationsList().isEmpty()) {
            httpArguments = appendQueryParam(httpArguments, "destinationLocations", String.join(",", request.getDestinationLocationsList()));
        }
        httpArguments = appendQueryParam(httpArguments, "annotations", request.getAnnotations());

        return httpArguments;
    }

    @Override
    public String getEndpoint() {
        return "pedestrian/matrix";
    }

    @Override
    public String getCapiHost(PedestrianMatrixRequest request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(PedestrianMatrixRequest request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return PedestrianMatrixResponse.newBuilder();
    }
}
