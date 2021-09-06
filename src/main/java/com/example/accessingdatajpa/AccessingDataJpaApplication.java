package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			repository.save(new Customer("Evaldo", "Wolkers"));
			repository.save(new Customer("Joao", "Silva"));
			repository.save(new Customer("Maria", "Souza"));
			repository.save(new Customer("Jose", "Santos"));
			repository.save(new Customer("Bill", "Gates"));
			repository.save(new Customer("Linus", "Torvalds"));

			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer: repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("-------------------------------");
			log.info(customer.toString());
			log.info("");

			log.info("Customer found with findByLastName('Gates'):");
			log.info("-------------------------------");
			repository.findByLastName("Gates").forEach(wolkers -> {
				log.info(wolkers.toString());
			});			
			log.info("");
		};
	}

}
