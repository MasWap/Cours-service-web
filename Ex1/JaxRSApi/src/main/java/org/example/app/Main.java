package org.example.app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.jackson.JacksonFeature;


public class Main {
    public static void main(String[] args) throws Exception {
        // Create jetty server (SE)
        Server server = new Server(8000);

        // Config API JAX-RS
        ResourceConfig config = new ResourceConfig().packages("org.example.res");
        // Register JacksonFeature for JSON serialization/deserialization
        config.register(JacksonFeature.class);

        // Config  ServletContainer with API JAX-RS
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        // Create ServletContextHandler and add du ServletContainer
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(servlet, "/*");

        try {
            // Start jetty server
            server.start();
            server.join();
        } finally {
            // Stop jetty server
            server.destroy();
        }
    }
}