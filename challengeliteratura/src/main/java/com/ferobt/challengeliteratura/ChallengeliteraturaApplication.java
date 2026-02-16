package com.ferobt.challengeliteratura;

import com.ferobt.challengeliteratura.principal.Main;
import com.ferobt.challengeliteratura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeliteraturaApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(ChallengeliteraturaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("\nHola mundo desde Spring Boot\n");

		Main main = new Main(repository);
		main.mostrarMenu();


	}

}
