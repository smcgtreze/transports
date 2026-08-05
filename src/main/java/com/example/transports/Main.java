package com.example.transports;

import java.net.URI;
import java.net.http.HttpClient;

import com.example.transports.Root;

public class Main {
    private static int httpPort = 8443;
    private static String capiKey = "825858535684745631cd5fef5c1626ee";
    private static String capiHost = "busmaps.com";

    public static void executeSimpleGET() {
        HttpClient httpClient = HttpClient.newHttpClient();
        httpClient.sendAsync(
            java.net.http.HttpRequest.newBuilder()
                .uri(URI.create("https://capi." + capiHost + ":" + httpPort + "/routes?origin=51.537511,-0.152208&destination=51.531921,-0.082569"))
                .header("capi-key", "Bearer " + capiKey)
                .header("capi-host", capiHost)
                .GET()
                .build(),
            java.net.http.HttpResponse.BodyHandlers.ofString()
        ).thenAccept(response -> {
            System.out.println("HTTP Response: " + response.body());
        }).join();
    }

    // public static void main(String[] args) throws Exception {
    //     RoutesServiceImpl routesService = new RoutesServiceImpl();
    //     routesService.getRoutes(
    //         Path.newBuilder()
    //             .setOrigin("41.2054279594662,-8.549259243833832")
    //             .setDestination("41.158195900186485,-8.643438221307983")
    //             .setCapiHost(capiHost)
    //             .setApiKey(capiKey)
    //             .build(),
    //         new io.grpc.stub.StreamObserver<Root>() {
    //             @Override
    //             public void onNext(Root root) {
    //                 printRoot(root);
    //             }

    //             @Override
    //             public void onError(Throwable t) {
    //                 System.err.println("Error: " + t.getMessage());
    //             }

    //             @Override
    //             public void onCompleted() {
    //                 System.out.println("Completed receiving routes.");
    //             }
    //         });
    // }

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
                    printNextDeparturesRoot(response);
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

    private static void printRoot(Root root) {
        System.out.println("Region: " + root.getRegionName());
        System.out.println("Imperial: " + root.getImperial());
        System.out.println("Processing Time: " + root.getProcessingTimeMs());

        for (Route route : root.getRoutesList()) {
            System.out.println("Route ID: " + route.getId());
            System.out.println("Duration: " + route.getDuration());
            System.out.println("Transfers: " + route.getTransfers());
            System.out.println("Walking Distance: " + route.getWalkingDistance());
            System.out.println("Walking Calories: " + route.getWalkingCalories());
            System.out.println("Region Name: " + route.getRegionName());

            for (Section s : route.getSectionsList()) {
                System.out.println("  Section ID: " + s.getId());
                System.out.println("  Type: " + s.getType());
                System.out.println("  Departure: " + s.getDeparture().getTime());
                System.out.println("  Arrival: " + s.getArrival().getTime());
                System.out.println("  Transport Mode: " + s.getTransport().getMode());
            }
        }

        for (Alert alert : root.getAlertsList()) {
            System.out.println("ALERT: " + alert.getHeader());

            for (InformedEntity ie : alert.getInformedEntityList()) {
                System.out.println("  Informed Route ID: " + ie.getRouteId());
            }
        }
    }

    private static void printNextDeparturesRoot(NextDeparturesResponse response) {
        System.out.println("Region: " + response.getRegionName());
        System.out.println("Imperial: " + response.getImperial());
        System.out.println("Processing Time: " + response.getProcessingTimeMs());
        System.out.println("Local Time: " + response.getLocalTime());

        for (StopDeparture stop : response.getStopDeparturesList()) {
            System.out.println("Stop: " + stop.getStopName() + " (" + stop.getStopId() + ")");

            for (DepartureItem departure : stop.getDepartureListList()) {
                System.out.println("  Departure: " + departure.getDepartureTime() + " -> " + departure.getTripHeadsign());
                System.out.println("  Route: " + departure.getRouteShortName());
            }
        }

        for (Alert alert : response.getAlertsList()) {
            System.out.println("ALERT: " + alert.getHeader());

            for (InformedEntity informedEntity : alert.getInformedEntityList()) {
                System.out.println("  Informed Route ID: " + informedEntity.getRouteId());
            }
        }
    }
}

