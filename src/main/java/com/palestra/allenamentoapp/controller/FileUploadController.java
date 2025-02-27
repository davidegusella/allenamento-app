package com.palestra.allenamentoapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    // Percorso dove salvare i file (puoi cambiare il percorso)
    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // Crea la cartella se non esiste
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Salva il file sul server
        Path path = Paths.get(uploadDir + "/" + file.getOriginalFilename());
        Files.write(path, file.getBytes());

        return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
    }

    // Endpoint per visualizzare un file (immagine o pdf)
    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) throws IOException {
        Path path = Paths.get(uploadDir + "/" + filename);
        byte[] fileBytes = Files.readAllBytes(path);
        return ResponseEntity.ok(fileBytes);
    }
}
