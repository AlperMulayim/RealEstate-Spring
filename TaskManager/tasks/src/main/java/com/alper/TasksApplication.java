package com.alper;

import com.alper.domain.Car;
import com.alper.domain.Task;
import com.alper.service.CarService;
import com.alper.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TaskService taskService, CarService carService){
		return args->{
			taskService.saveTask(new Task("Work For Spring", LocalDate.now(),false));
			taskService.saveTask(new Task("Work For Math", LocalDate.now().plus(10, ChronoUnit.DAYS),false));
			taskService.saveTask(new Task("Go to Supermarket", LocalDate.now().plus(5,ChronoUnit.DAYS),false));
			carService.save(new Car("Opel Corsa","12KL12","Oliver",2010));
			carService.save(new Car("Mercedes GLA","XCVR312","Teressa",2017));
			carService.save(new Car("Renault Megan","123UV1234","Namık",2005));
			carService.save(new Car("Skoda Kodiak","14mn34","Demi",2017));

		};
	}
}
