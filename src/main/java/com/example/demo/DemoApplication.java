package com.example.demo;

import com.example.demo.domain.Card;
import com.example.demo.service.CardService;
import com.sun.tools.javac.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ApplicationRunner init(CardService service){
		return args -> service.createAll(List.of(Card.builder()
					.id(1L)
					.cost("UU")
					.color("Blue")
					.title("Conterspell")
					.type("instant")
					.subType(null)
					.build(),
				Card.builder()
					.id(2L)
					.cost("WW")
					.color("White")
					.title("White knight")
					.type("creature")
					.subType("Knight")
					.build(),
				Card.builder()
						.id(3L)
						.cost("B")
						.color("Black")
						.title("Though Seize")
						.type("sorcery")
						.subType(null)
						.build(),
				Card.builder()
						.id(4L)
						.cost("R")
						.color("Red")
						.title("Choke")
						.type("instant")
						.subType(null)
						.build(),
				Card.builder()
						.id(5L)
						.cost("G")
						.color("Green")
						.title("Llanowar Elves")
						.type("creature")
						.subType("elf")
						.build()
		));
	}

}
