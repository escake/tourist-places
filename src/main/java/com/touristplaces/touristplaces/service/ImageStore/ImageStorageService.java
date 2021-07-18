package com.touristplaces.touristplaces.service.ImageStore;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

    String storeAndReturnPath(MultipartFile image);
}
