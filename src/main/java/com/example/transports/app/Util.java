package com.example.transports.app;

import java.awt.BorderLayout;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public final class Util {

    private Util() {
        // utility class
    }

    public static void executeSimpleGET(int httpPort, String capiHost, String capiKey) {
        HttpClient httpClient = HttpClient.newHttpClient();
        httpClient.sendAsync(
            HttpRequest.newBuilder()
                .uri(URI.create("https://capi." + capiHost + ":" + httpPort + "/routes?origin=51.537511,-0.152208&destination=51.531921,-0.082569"))
                .header("capi-key", "Bearer " + capiKey)
                .header("capi-host", capiHost)
                .GET()
                .build(),
            HttpResponse.BodyHandlers.ofString()
        ).thenAccept(response -> {
            System.out.println("HTTP Response: " + response.body());
        }).join();
    }

    public static <T extends Message> void printMessage(T response) {
        try {
            String rendered = JsonFormat.printer().print(response);
            System.out.println(rendered);
            showInWindow("Route display", rendered);
        } catch (Exception e) {
            System.err.println("Unable to format response: " + e.getMessage());
        }
    }

    public static void showInWindow(String title, String content) {
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
