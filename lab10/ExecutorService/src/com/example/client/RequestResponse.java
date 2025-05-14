package com.example.client;

public class RequestResponse {
    private final String host;
    private final int port;
    private String response;

    public RequestResponse(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }
}
