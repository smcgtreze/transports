package com.example.transports.service;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

import com.example.transports.*;
public class RawVehiclePositionsServiceImpl extends VehiclePositionsServiceGrpc.VehiclePositionsServiceImplBase implements ServiceImpl<VehiclePositionsRequest, VehiclePositionsResponse> {
    @Override
    public void getVehiclePositions(VehiclePositionsRequest request, StreamObserver<VehiclePositionsResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(VehiclePositionsRequest request) {
        String httpArguments = "";

        httpArguments = appendQueryParam(httpArguments, "bounding_box", request.getBoundingBox());
        httpArguments = appendQueryParam(httpArguments, "regionName", request.getRegionName());
        httpArguments = appendQueryParam(httpArguments, "routeId", request.getRouteId());
        httpArguments = appendQueryParam(httpArguments, "countryIso", request.getCountryIso());
        httpArguments = appendQueryParam(httpArguments, "lang", request.getLang());

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


