package com.example.transports;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

public class PedestrianRouteServiceImpl extends PedestrianRouteServiceGrpc.PedestrianRouteServiceImplBase implements ServiceImpl<PedestrianRouteRequest, PedestrianRouteResponse> {
    @Override
    public void getPedestrianRoute(PedestrianRouteRequest request, StreamObserver<PedestrianRouteResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(PedestrianRouteRequest request) {
        String httpArguments = "";

        if (request.getAlternatives() != 0) {
            httpArguments += "?alternatives=" + request.getAlternatives();
        }
        if (request.getOverview() != null && !request.getOverview().isEmpty()) {
            httpArguments += (httpArguments.isEmpty() ? "?" : "&") + "overview=" + request.getOverview();
        }
        if (request.getGeometries() != null && !request.getGeometries().isEmpty()) {
            httpArguments += (httpArguments.isEmpty() ? "?" : "&") + "geometries=" + request.getGeometries();
        }
        if (request.getSteps()) {
            httpArguments += (httpArguments.isEmpty() ? "?" : "&") + "steps=" + request.getSteps();
        }
        if (request.getAnnotations() != null && !request.getAnnotations().isEmpty()) {
            httpArguments += (httpArguments.isEmpty() ? "?" : "&") + "annotations=" + request.getAnnotations();
        }
        if (request.getSkipWaypoints()) {
            httpArguments += (httpArguments.isEmpty() ? "?" : "&") + "skip_waypoints=" + request.getSkipWaypoints();
        }
        if (request.getRegionName() != null && !request.getRegionName().isEmpty()) {
            httpArguments += (httpArguments.isEmpty() ? "?" : "&") + "regionName=" + request.getRegionName();
        }
        if (request.getLang() != null && !request.getLang().isEmpty()) {
            httpArguments += (httpArguments.isEmpty() ? "?" : "&") + "lang=" + request.getLang();
        }

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
