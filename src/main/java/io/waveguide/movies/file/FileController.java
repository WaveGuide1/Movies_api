package io.waveguide.movies.file;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {

    private final FileServiceImpl fileService;

    @Value("${project.poster}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<String> fileUpload(@RequestPart MultipartFile file) throws IOException{

        String uploadedFilename = fileService.uploadFile(path, file);
        return ResponseEntity.ok(uploadedFilename + " " + "Uploaded successfully");
    }

    @GetMapping("/{filename}")
    public void serveFile(@PathVariable String filename, HttpServletResponse response) throws IOException {

        InputStream inputStream = fileService.getResourceFile(path, filename);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(inputStream, response.getOutputStream());
    }
}
