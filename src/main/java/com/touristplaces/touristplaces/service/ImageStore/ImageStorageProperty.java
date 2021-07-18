package com.touristplaces.touristplaces.service.ImageStore;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "images")
@Component
public class ImageStorageProperty {
    private String storageDirectory;
}
