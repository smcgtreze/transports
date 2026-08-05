package com.example.transports;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

public class GtfsFeedsCatalogServiceImpl extends GtfsFeedsCatalogServiceGrpc.GtfsFeedsCatalogServiceImplBase implements ServiceImpl<GtfsFeedsCatalogRequest, GtfsFeedsCatalogResponse> {
    @Override
    public void getGtfsFeedsCatalog(GtfsFeedsCatalogRequest request, StreamObserver<GtfsFeedsCatalogResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(GtfsFeedsCatalogRequest request) {
        String httpArguments = "";

        if (request.getCountryIso() != null && !request.getCountryIso().isEmpty()) {
            httpArguments = "?countryIso=" + request.getCountryIso();
        }

        return httpArguments;
    }

    @Override
    public String getEndpoint() {
        return "getGtfsFeedsDownloads";
    }

    @Override
    public String getCapiHost(GtfsFeedsCatalogRequest request) {
        return request.getKey().getCapiHost();
    }

    @Override
    public String getApiKey(GtfsFeedsCatalogRequest request) {
        return request.getKey().getApiKey();
    }

    @Override
    public Message.Builder newResponseBuilder() {
        return GtfsFeedsCatalogResponse.newBuilder();
    }
}
