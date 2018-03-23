package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

interface HouseRepository extends CrudRepository<House , Long> {}


@Entity
class House {
    @Id
    @GeneratedValue
    private long id;
    private String address;

    public House(){}
    public House(long id, String address) {
        this.id = id;
        this.address = address;
    }
    public House(String address){
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
