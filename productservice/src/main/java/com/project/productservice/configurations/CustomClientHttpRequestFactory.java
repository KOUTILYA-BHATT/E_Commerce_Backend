package com.project.productservice.configurations;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

public class CustomClientHttpRequestFactory extends SimpleClientHttpRequestFactory {
    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws ProtocolException {
        if ("PATCH".equalsIgnoreCase(httpMethod)) {
            connection.setRequestMethod("POST");
            connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        } else {
            connection.setRequestMethod(httpMethod);
        }
    }
}