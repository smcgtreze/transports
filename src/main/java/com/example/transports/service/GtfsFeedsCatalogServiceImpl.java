package com.example.transports.service;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;

import com.example.transports.*;
public class GtfsFeedsCatalogServiceImpl extends GtfsFeedsCatalogServiceGrpc.GtfsFeedsCatalogServiceImplBase implements ServiceImpl<GtfsFeedsCatalogRequest, GtfsFeedsCatalogResponse> {
    @Override
    public void getGtfsFeedsCatalog(GtfsFeedsCatalogRequest request, StreamObserver<GtfsFeedsCatalogResponse> responseObserver) {
        getService(request, responseObserver);
    }

    @Override
    public String getHttpArguments(GtfsFeedsCatalogRequest request) {
        return appendQueryParam("", "countryIso", request.getCountryIso());
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


