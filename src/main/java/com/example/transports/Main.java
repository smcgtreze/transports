package com.example.transports;

import java.awt.BorderLayout;
import java.net.URI;
import java.net.http.HttpClient;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.google.protobuf.util.JsonFormat;

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
                    printMessage(response);
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

    private static <T extends com.google.protobuf.Message> void printMessage( T response) {
        try {
            String rendered = JsonFormat.printer()
                .print(response);

            System.out.println(rendered);
            showInWindow("Route display", rendered);
        } catch (Exception e) {
            System.err.println("Unable to format response: " + e.getMessage());
        }
    }

    private static void showInWindow(String title, String content) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            JTextArea textArea = new JTextArea(content, 24, 80);
            textArea.setEditable(false);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

