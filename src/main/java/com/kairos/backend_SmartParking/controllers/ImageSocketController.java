package com.kairos.backend_SmartParking.controllers;

import com.kairos.backend_SmartParking.dto.ImageMessage;
import com.kairos.backend_SmartParking.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ImageSocketController {

    @Autowired
    private ImageService imageService;

    @MessageMapping("/images/upload")   // client → serveur
    @SendTo("/topic/parking")           // serveur → clients
    public ImageMessage receiveImage(ImageMessage msg) {

        // 1. décodage + stockage éventuel
        imageService.processImage(msg);

        // 2. renvoi aux dashboards (temps réel)
        return msg;
    }
}
