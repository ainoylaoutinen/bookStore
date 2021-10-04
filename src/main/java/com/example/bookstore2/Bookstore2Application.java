package com.example.bookstore2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore2.domain.Book;
import com.example.bookstore2.domain.BookRepository;
import com.example.bookstore2.domain.Category;
import com.example.bookstore2.domain.CategoryRepository;

import com.example.bookstore2.domain.User;
import com.example.bookstore2.domain.UserRepository;


@SpringBootApplication
public class Bookstore2Application {
	
	private static final Logger log = LoggerFactory.getLogger(Bookstore2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Bookstore2Application.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository drepository, UserRepository urepository) {
		return (args) -> {
			
			log.info("save books");
			drepository.save(new Category("classics"));
			drepository.save(new Category("horror"));
			drepository.save(new Category("comics"));
			
			repository.save(new Book("Ernest Hemingway", "A farewell to Arms", "1232323-21", 1929, drepository.findByName("classics").get(0)));
			repository.save(new Book("George Orwell", "Animal Farm", "22122343-5", 1945, drepository.findByName("horror").get(0)));
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user1@gmail.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "user2@hotmail.fi", "ADMIN");
			User user3 = new User("secondUser", "bd56ba4fae10afd4d88777c60bf3ce5df32176ab4936699a7eb9b168d7ef31da", "user3@hotmail.com", "USER");

			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
