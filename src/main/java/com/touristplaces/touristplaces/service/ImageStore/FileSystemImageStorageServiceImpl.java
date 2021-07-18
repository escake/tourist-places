package com.touristplaces.touristplaces.service.ImageStore;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class FileSystemImageStorageServiceImpl implements ImageStorageService {

    private final ImageStorageProperty imageStorageProperty;

    @Override
    @SneakyThrows // TODO handle exceptions
    public String storeAndReturnPath(MultipartFile image) {
        final String STORAGE_DIRECTORY = imageStorageProperty.getStorageDirectory();

        final byte[] imageBytes = image.getBytes();

        Path newFile = Paths.get(STORAGE_DIRECTORY + "/" + LocalDateTime.now() + "-" + image.getOriginalFilename());

        Files.createDirectories(newFile.getParent());

        Files.write(newFile, imageBytes);

        return newFile.toString();
    }
}
