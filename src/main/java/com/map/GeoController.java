package com.map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class GeoController {

    @GetMapping("/show-map")
    public String showMap(@RequestParam String mapUrl, Model model) throws Exception {
        URL url = new URL(mapUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setInstanceFollowRedirects(true);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        
        int responseCode = connection.getResponseCode();
        String finalUrl = (responseCode == HttpURLConnection.HTTP_OK) ? connection.getURL().toString() : mapUrl;

        String[] patterns = {
            "@(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)",             
            "ll=(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)",        
            "q=(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)",        
            "center=(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)",        
            "3d(-?\\d+\\.\\d+)!4d(-?\\d+\\.\\d+)",      
            "destination=(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)"         
        };

        for (String patternStr : patterns) {
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(finalUrl);
            
            if (matcher.find()) {
                try {
                    double lat = Double.parseDouble(matcher.group(1));
                    double lng = Double.parseDouble(matcher.group(2));
                    
                    if (isValidCoordinate(lat, lng)) {
                        String latitude = String.format("%.6f", lat);
                        String longitude = String.format("%.6f", lng);
                        
                        System.out.println("Found coordinates: " + latitude + ", " + longitude);
                        System.out.println("Using pattern: " + patternStr);
                        
                        model.addAttribute("latitude", latitude);
                        model.addAttribute("longitude", longitude);
                        return "map";
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        
        return "error";
    }
    
    private boolean isValidCoordinate(double lat, double lng) {
        return lat >= -90 && lat <= 90 && lng >= -180 && lng <= 180;
    }
}