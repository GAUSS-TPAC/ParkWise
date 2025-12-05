package com.kairos.backend_SmartParking.services;

import com.kairos.backend_SmartParking.dto.ImageMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.time.Instant;
import java.util.Base64;

@Service
public class ImageService {

    @Autowired
    private SimpMessagingTemplate messaging;

    private static final Path STORAGE_FOLDER = Paths.get("images/parking/");

    public void handleIncomingImage(byte[] imgBytes) {

        try {
            // 1. Décode JPEG en BufferedImage
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgBytes));

            if (img == null) {
                System.err.println("Image corrompue !");
                return;
            }

            // 2. Optimisation : réduction x2
            BufferedImage optimized = resize(img, img.getWidth() / 2, img.getHeight() / 2);

            // 3. Convertir en Base64
            String base64 = toBase64(optimized);

            // 4. Envoyer sur WebSocket
            messaging.convertAndSend("/topic/parking", new ImageMessage(base64));

            // 5. Stockage local
            saveToDisk(optimized);

        } catch (Exception e) {
            System.err.println("Erreur traitement image : " + e.getMessage());
        }
    }

    // -----------------------------------------
    // UTILITAIRES
    // -----------------------------------------

    private BufferedImage resize(BufferedImage original, int width, int height) {
        Image temp = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = out.createGraphics();
        g.drawImage(temp, 0, 0, null);
        g.dispose();
        return out;
    }

    private String toBase64(BufferedImage img) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", out);
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    private void saveToDisk(BufferedImage img) throws IOException {
        Files.createDirectories(STORAGE_FOLDER);

        String filename = "parking_" + Instant.now().toEpochMilli() + ".jpg";
        Path file = STORAGE_FOLDER.resolve(filename);

        ImageIO.write(img, "jpg", file.toFile());
    }
}
