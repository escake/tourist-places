package com.touristplaces.touristplaces.testingUtil;

import com.touristplaces.touristplaces.data.User;
import com.touristplaces.touristplaces.repository.PhotoRepository;
import com.touristplaces.touristplaces.repository.PlaceRepository;
import com.touristplaces.touristplaces.repository.UserRepository;
import com.touristplaces.touristplaces.service.ImageStore.ImageStorageService;
import com.touristplaces.touristplaces.service.PhotoService;
import com.touristplaces.touristplaces.service.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
public class IntegrationTestBase {
    @Autowired
    protected PlaceRepository placeRepository;
    @Autowired
    protected PhotoRepository photoRepository;
    @Autowired
    protected ImageStorageService imageStorageService;
    @Autowired
    protected AuthService authService;
    @Autowired
    protected PhotoService photoService;
    @Autowired
    protected TestUtil testUtil;
    @Autowired
    protected UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.save(new User("test-user", "$2a$10$p6nFCs8ZKPrv7VJzfMzSiOCgU5oxeQhim9gQB6QRrPtLjucBtfaxC"));
    }

    public User getTestUser() {
        return userRepository.findByUsername("test-user").get();
    }
}
