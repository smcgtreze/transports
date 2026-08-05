package com.example.transports;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

public class AlertsServiceImpl extends AlertsServiceGrpc.AlertsServiceImplBase implements ServiceImpl<AlertsRequest, AlertsResponse> {
    @Override
    public void getAlerts(AlertsRequest request, StreamObserver<AlertsResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(AlertsRequest request) {
        String httpArguments = "?regionName=" + request.getRegionName()
            + "&countryIso=" + request.getCountryIso();

        if (request.getRouteId() != null && !request.getRouteId().isEmpty()) {
            httpArguments += "&routeId=" + request.getRouteId();
        }
        if (request.getOperatorId() != null && !request.getOperatorId().isEmpty()) {
            httpArguments += "&operatorId=" + request.getOperatorId();
        }
        if (request.getLang() != null && !request.getLang().isEmpty()) {
            httpArguments += "&lang=" + request.getLang();
        }

        return httpArguments;
    }

    @Override
    public String getEndpoint() {
        return "alerts";
    }

    @Override
    public String getCapiHost(AlertsRequest request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(AlertsRequest request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return AlertsResponse.newBuilder();
    }
}
