package com.example.transports.app;

import com.example.transports.Key;
import com.example.transports.NextDeparturesRequest;
import com.example.transports.NextDeparturesResponse;
import com.example.transports.app.Util;
import com.example.transports.service.NextDeparturesServiceImpl;

public class Main {
    private static int httpPort = 8443;
    private static String capiKey = "825858535684745631cd5fef5c1626ee";
    private static String capiHost = "busmaps.com";
    
    public static void main(String[] args) throws Exception {
        NextDeparturesServiceImpl nextDeparturesService = new NextDeparturesServiceImpl();
        nextDeparturesService.getNextDepartures(
            NextDeparturesRequest.newBuilder()
                .setLocation("53.535402,-2.523009")
                .setCountryIso("GBR")
                .setKey(Key.newBuilder()
                    .setCapiHost(capiHost)
                    .setApiKey(capiKey)
                    .setPort(httpPort)
                    .build())
                .build(),
            new io.grpc.stub.StreamObserver<NextDeparturesResponse>() {
                @Override
                public void onNext(NextDeparturesResponse response) {
                    Util.printMessage(response);
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Completed receiving next departures.");
                }
            });
    }
}


