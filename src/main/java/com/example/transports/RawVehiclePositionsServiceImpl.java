package com.example.transports;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

public class RawVehiclePositionsServiceImpl extends VehiclePositionsServiceGrpc.VehiclePositionsServiceImplBase implements ServiceImpl<VehiclePositionsRequest, VehiclePositionsResponse> {
    @Override
    public void getVehiclePositions(VehiclePositionsRequest request, StreamObserver<VehiclePositionsResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(VehiclePositionsRequest request) {
        String httpArguments = "";

        if (request.getBoundingBox() != null && !request.getBoundingBox().isEmpty()) {
            httpArguments += "?bounding_box=" + request.getBoundingBox();
        }
        if (request.getRegionName() != null && !request.getRegionName().isEmpty()) {
            httpArguments += (httpArguments.isEmpty() ? "?" : "&") + "regionName=" + request.getRegionName();
        }
        if (request.getRouteId() != null && !request.getRouteId().isEmpty()) {
            httpArguments += (httpArguments.isEmpty() ? "?" : "&") + "routeId=" + request.getRouteId();
        }
        if (request.getCountryIso() != null && !request.getCountryIso().isEmpty()) {
            httpArguments += (httpArguments.isEmpty() ? "?" : "&") + "countryIso=" + request.getCountryIso();
        }
        if (request.getLang() != null && !request.getLang().isEmpty()) {
            httpArguments += (httpArguments.isEmpty() ? "?" : "&") + "lang=" + request.getLang();
        }

        return httpArguments;
    }

    @Override
    public String getEndpoint() {
        return "rawVehiclePositions";
    }

    @Override
    public String getCapiHost(VehiclePositionsRequest request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(VehiclePositionsRequest request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return VehiclePositionsResponse.newBuilder();
    }
}
