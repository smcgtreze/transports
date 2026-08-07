package com.example.transports;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface ServiceImpl<T, V extends Message> {
    final int PORT = 8443;

    String getHttpArguments(final T request);
    String getEndpoint();
    String getCapiHost(final T request);
    String getApiKey(final T request);
    Message.Builder newResponseBuilder();

    default String appendQueryParam(String existing, String name, String value) {
        if (value == null || value.isBlank()) {
            return existing;
        }
        return existing + (existing.isEmpty() ? "?" : "&") + name + "=" + encode(value);
    }

    default String appendQueryParam(String existing, String name, boolean value) {
        if (!value) {
            return existing;
        }
        return existing + (existing.isEmpty() ? "?" : "&") + name + "=" + value;
    }

    default String appendQueryParam(String existing, String name, int value) {
        return existing + (existing.isEmpty() ? "?" : "&") + name + "=" + value;
    }

    default String appendQueryParam(String existing, String name, int value, boolean includeWhenZero) {
        if (!includeWhenZero && value == 0) {
            return existing;
        }
        return existing + (existing.isEmpty() ? "?" : "&") + name + "=" + value;
    }

    default String encode(String value) {
        return java.net.URLEncoder.encode(value, java.nio.charset.StandardCharsets.UTF_8);
    }

    default void getService(final T request, StreamObserver<V> responseObserver) {
        try {
            String url = "https://capi." + getCapiHost(request) + ":" + PORT + "/" + getEndpoint()
                + getHttpArguments(request);

            String json = sendHttpResponse(url, getApiKey(request), getCapiHost(request));
            
            Message.Builder builder = newResponseBuilder();
            JsonFormat.parser().ignoringUnknownFields().merge(json, builder);

            @SuppressWarnings("unchecked")
            V response = (V) builder.build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    private static String sendHttpResponse(String url, String apiKey, String capiHost) throws Exception {
        var httpClient = HttpClient.newHttpClient();

        var httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("capi-key", "Bearer " + apiKey)
            .header("capi-host", capiHost)
            .GET()
            .build();

        var httpResponse = httpClient.send(
            httpRequest,
            HttpResponse.BodyHandlers.ofString()
        );

        return httpResponse.body();
    }
}
