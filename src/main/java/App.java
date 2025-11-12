package com.example;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
  public static void main(String[] args) throws IOException {
    int port = 8080; // container port
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", exchange -> {
      String resp = "Hello from Jenkins this is InduVas here! If you see this, CI/CD works.";
      exchange.sendResponseHeaders(200, resp.getBytes().length);
      try (OutputStream os = exchange.getResponseBody()) { os.write(resp.getBytes()); }
    });
    server.start();
    System.out.println("HTTP server started on port " + port);
    // keep process alive
    try { Thread.currentThread().join(); } catch (InterruptedException ignored) {}
  }
}
