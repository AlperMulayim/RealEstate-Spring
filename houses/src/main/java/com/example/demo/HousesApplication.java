package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.SynchronousQueue;
import java.util.stream.Stream;

@SpringBootApplication
public class HousesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousesApplication.class, args);
	}

	@Bean
	public CommandLineRunner houses(HouseRepository houseRepository){
		return args->{
			Stream.of(new House("Turdu Orhaniye ,MUGLA"),
					new House("OgretmenEvi , MUGLA"),
					new House("875 Howard St , NYC"))
					.forEach(houseRepository::save);

            houseRepository.findAll().forEach(
                    house -> System.out.println("New House : " + house.getAddress()));
		};
	}
}
