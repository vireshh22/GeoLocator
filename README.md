# GeoLocator
GeoLocator is an interactive web application that allows users to display and extract geographic coordinates from a given map URL. It dynamically visualizes the location on a map using Leaflet.js and retrieves reverse geocoded address details via the OpenStreetMap API.

# Features
## Coordinate Extraction:
- Extracts latitude and longitude from map URLs using regex patterns.
## Interactive Map:
- Displays the extracted coordinates on a dynamic, interactive map.
- Users can drag the map marker to update location details.
## Reverse Geocoding:
- Fetches and displays human-readable addresses for the selected location using Nominatim API.
## Validation:
- Ensures that extracted coordinates fall within valid latitude and longitude ranges.
## User-Friendly Design:
- Clean and intuitive map-based interface for better usability.
# Technologies Used
## Backend
- Spring Boot: Handles the backend logic for URL parsing and coordinate validation.
## Frontend
- Thymeleaf: Template engine for rendering dynamic web pages.
- Leaflet.js: JavaScript library for interactive map visualization and marker controls.
## APIs
- OpenStreetMap: Provides map tiles for visualization.
- Nominatim API: Retrieves reverse geocoded addresses for coordinates.
## Build Tool
- Maven: Manages dependencies and project build.
