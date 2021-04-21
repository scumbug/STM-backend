package com.wongc.stm;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@EnableAutoConfiguration
@ComponentScan
@SpringBootConfiguration
@EnableScheduling
public class StmApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StmApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/users/").allowedOrigins("http://localhost:4200");
			}
		};
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);

		return modelMapper;
	}

	@Override
	public void run(String... args) throws Exception {
		Path root = Paths.get("uploads");
		try {
			FileSystemUtils.deleteRecursively(root.toFile());
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Cant init upload folder!");
		}
	}
}
