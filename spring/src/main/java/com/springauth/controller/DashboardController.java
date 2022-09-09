package com.springauth.controller;

import com.springauth.payload.response.UnsplashResponse;
import com.springauth.utils.Unsplash;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/dashboard")
@AllArgsConstructor
public class DashboardController {

    private final Unsplash unsplash;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/searchunsplash")
    public UnsplashResponse searchUnsplash(@RequestParam String query) {
        return unsplash.search(query);
    }
}
