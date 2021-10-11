package com.example.bookstore2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore2.domain.Book;
import com.example.bookstore2.domain.BookRepository;
import com.example.bookstore2.domain.Category;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookrepository;

	@Test
	public void findByTitle() {
		List<Book> books = bookrepository.findByTitle("A farewell to Arms");

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Ernest Hemingway");
	}

	@Test
	public void createNewBook() {
		Book book = new Book("The Great Gatsby", "Fitzgerald", "7283943-378", 1925, new Category("classics"));
		bookrepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteNewBook() {
		List <Book> books = bookrepository.findByTitle("A farewell to Arms");
		Book book = books.get(0);
		long id = book.getId();
		bookrepository.delete(book);
		
		List <Book> newBooks = bookrepository.findByTitle("A farewell to Arms");
		
		assertThat(newBooks).hasSize(0);
	}
}
