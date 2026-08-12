package com.example.transports.service;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

import com.example.transports.*;
public class PedestrianRouteServiceImpl extends PedestrianRouteServiceGrpc.PedestrianRouteServiceImplBase implements ServiceImpl<PedestrianRouteRequest, PedestrianRouteResponse> {
    @Override
    public void getPedestrianRoute(PedestrianRouteRequest request, StreamObserver<PedestrianRouteResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(PedestrianRouteRequest request) {
        String httpArguments = "";

        httpArguments = appendQueryParam(httpArguments, "alternatives", request.getAlternatives(), false);
        httpArguments = appendQueryParam(httpArguments, "overview", request.getOverview());
        httpArguments = appendQueryParam(httpArguments, "geometries", request.getGeometries());
        httpArguments = appendQueryParam(httpArguments, "steps", request.getSteps());
        httpArguments = appendQueryParam(httpArguments, "annotations", request.getAnnotations());
        httpArguments = appendQueryParam(httpArguments, "skip_waypoints", request.getSkipWaypoints());
        httpArguments = appendQueryParam(httpArguments, "regionName", request.getRegionName());
        httpArguments = appendQueryParam(httpArguments, "lang", request.getLang());

        return httpArguments;
    }

    @Override
    public String getEndpoint() {
        return "pedestrian/route";
    }

    @Override
    public String getCapiHost(PedestrianRouteRequest request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(PedestrianRouteRequest request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return PedestrianRouteResponse.newBuilder();
    }
}


