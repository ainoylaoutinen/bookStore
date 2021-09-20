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

@SpringBootApplication
public class Bookstore2Application {
	
	private static final Logger log = LoggerFactory.getLogger(Bookstore2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Bookstore2Application.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository drepository) {
		return (args) -> {
			
			log.info("save books");
			drepository.save(new Category("classics"));
			drepository.save(new Category("horror"));
			drepository.save(new Category("comics"));
			
			repository.save(new Book("Ernest Hemingway", "A farewell to Arms", "1232323-21", 1929, drepository.findByName("classics").get(0)));
			repository.save(new Book("George Orwell", "Animal Farm", "22122343-5", 1945, drepository.findByName("horror").get(0)));
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
