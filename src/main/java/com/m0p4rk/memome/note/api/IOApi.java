package com.m0p4rk.memome.note.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.m0p4rk.memome.common.model.ApiResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class IOApi {

	private static final String FILE_DIRECTORY = "F:/MemoMeServer";
	private Map<String, String> fileMap = new HashMap<>();
	
	private void saveFile(MultipartFile file, String uniqueFilename) throws IOException {
        Path directoryPath = Paths.get(FILE_DIRECTORY);

        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        Path filePath = directoryPath.resolve(uniqueFilename);
        Files.write(filePath, file.getBytes());
    }

	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, "File is empty"));
		}

		try {
			String originalFilename = file.getOriginalFilename();
			String uniqueFilename = UUID.randomUUID().toString();
			fileMap.put(uniqueFilename, originalFilename);

			saveFile(file, uniqueFilename);
			return ResponseEntity.ok(new ApiResponse(true, "File successfully uploaded: " + originalFilename));
		} catch (IOException e) {
			return ResponseEntity.internalServerError()
					.body(new ApiResponse(false, "Failed uploading: " + e.getMessage()));
		}
	}

	@GetMapping("/download")
	public void handleFileDownload(@RequestParam("fileName") String originalFileName, HttpServletResponse response) {
		String uniqueFilename = fileMap.entrySet().stream().filter(entry -> originalFileName.equals(entry.getValue()))
				.map(Map.Entry::getKey).findFirst().orElse(null);

		if (uniqueFilename == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return;
		}

		Path filePath = Paths.get(FILE_DIRECTORY, uniqueFilename);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\"");

		try (ServletOutputStream outputStream = response.getOutputStream()) {
			Files.copy(filePath, outputStream);
			outputStream.flush();
		} catch (IOException e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

}
