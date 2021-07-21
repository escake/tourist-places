package com.touristplaces.touristplaces;

import com.touristplaces.touristplaces.service.ImageStore.ImageStorageProperty;
import com.touristplaces.touristplaces.service.auth.JwtTokenProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({ImageStorageProperty.class, JwtTokenProperties.class})
@SpringBootApplication
public class TouristPlacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TouristPlacesApplication.class, args);
	}

}
