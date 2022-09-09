package com.springauth.utils;

import com.google.gson.Gson;
import com.springauth.payload.response.UnsplashResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Data
public class Unsplash {
    private String clientId = "NwZfngFJ6Lcw5p2yHkzY2vmzFvarjC6xm9ph3jRQE_s";
    private int page = 1;

    public UnsplashResponse search(String query) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.unsplash.com/search/photos?page=" + page + "&query=" + query + "&client_id=" + clientId))
                    .build();
            var response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), UnsplashResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
