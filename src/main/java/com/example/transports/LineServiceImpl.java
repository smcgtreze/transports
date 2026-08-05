package com.example.transports;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

public class LineServiceImpl extends LineServiceGrpc.LineServiceImplBase implements ServiceImpl<LineRequest, LineResponse> {
    @Override
    public void getLine(LineRequest request, StreamObserver<LineResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(LineRequest request) {
        String httpArguments = "?routeHash2=" + request.getRouteHash2()
            + "&countryIso=" + request.getCountryIso()
            + "&regionName=" + request.getRegionName();

        if (request.getLang() != null && !request.getLang().isEmpty()) {
            httpArguments += "&lang=" + request.getLang();
        }
        if (request.getDate() != null && !request.getDate().isEmpty()) {
            httpArguments += "&date=" + request.getDate();
        }

        return httpArguments;
    }

    @Override
    public String getEndpoint() {
        return "line";
    }

    @Override
    public String getCapiHost(LineRequest request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(LineRequest request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return LineResponse.newBuilder();
    }
}
