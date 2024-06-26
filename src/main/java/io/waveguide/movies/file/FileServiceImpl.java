package io.waveguide.movies.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        // Getting file name
        String fileName = file.getOriginalFilename();

        // Getting file path
        String filePath = path + File.separator + fileName;

        // File object creation
        File createdFile = new File(path);
        if(!createdFile.exists()){
            createdFile.mkdir();
        }

        // Copying and uploading file to the path
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }
}
