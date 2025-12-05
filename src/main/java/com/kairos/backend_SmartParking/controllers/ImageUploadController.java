package com.kairos.backend_SmartParking.controllers;

import com.kairos.backend_SmartParking.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/camera")
public class ImageUploadController {

    @Autowired
    private ImageService imageService;

    private static final String SECRET = "MY-API-KEY-123";

    @PostMapping(value = "/upload", consumes = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<String> uploadImage(
            @RequestBody byte[] imgBytes,
            @RequestParam("apikey") String apiKey) {

        if (!SECRET.equals(apiKey)) {
            return ResponseEntity.status(401).body("Invalid API KEY");
        }

        imageService.handleIncomingImage(imgBytes);
        return ResponseEntity.ok("Image received");
    }
}
