package com.touristplaces.touristplaces.testingUtil;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
public class TestUtil extends IntegrationTestBase {
    @Autowired
    ResourceLoader resourceLoader;

    @SneakyThrows
    public MultipartFile getFile(String fileName) {
        final InputStream inputStream = resourceLoader.getResource("classpath:test-files/" + fileName).getInputStream();
        return new MockMultipartFile(fileName, inputStream);
    }
}
