package com.m0p4rk.memome.note.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.m0p4rk.memome.common.model.ApiResponse;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class IOApi {

    private static final String FILE_DIRECTORY = System.getProperty("user.home") + "/Documents/MemoMeServer";

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "File is empty"));
        }

        try {
            Path filePath = saveFile(file);
            return ResponseEntity.ok(new ApiResponse(true, "File successfully uploaded: " + filePath.getFileName()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(new ApiResponse(false, "Failed uploading: " + e.getMessage()));
        }
    }

    @PostMapping("/download")
    public ResponseEntity<?> handleFileDownload(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        Path filePath = Paths.get(FILE_DIRECTORY, sanitizeFilename(fileName));

        if (!Files.exists(filePath)) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "File does not exist"));
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            Files.copy(filePath, outputStream);
            outputStream.flush();
            return ResponseEntity.ok(new ApiResponse(true, "File download successful"));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(new ApiResponse(false, "Error during file download: " + e.getMessage()));
        }
    }

    private Path saveFile(MultipartFile file) throws IOException {
        String filename = sanitizeFilename(file.getOriginalFilename());
        Path directoryPath = Paths.get(FILE_DIRECTORY);

        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        Path filePath = directoryPath.resolve(filename);
        Files.write(filePath, file.getBytes());
        return filePath;
    }

    private String sanitizeFilename(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9._]", "");
    }
}
