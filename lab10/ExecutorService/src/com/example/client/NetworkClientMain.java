package com.example.client;

import java.util.*;
import java.util.concurrent.*;

public class NetworkClientMain {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        Map<RequestResponse, Future<RequestResponse>> callables = new HashMap<>();

        
        for (int port = 10000; port < 10010; port++) {
            RequestResponse request = new RequestResponse("localhost", port);
            NetworkClientCallable task = new NetworkClientCallable(request);
            Future<RequestResponse> future = executor.submit(task);
            callables.put(request, future);
        }

        executor.shutdown(); 
        executor.awaitTermination(5, TimeUnit.SECONDS); 

    
        for (Map.Entry<RequestResponse, Future<RequestResponse>> entry : callables.entrySet()) {
            RequestResponse request = entry.getKey();
            try {
                RequestResponse response = entry.getValue().get();
                System.out.println("Server [" + request.getPort() + "]: " + response.getResponse());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error from server [" + request.getPort() + "]: " + e.getMessage());
            }
        }
    }
}
