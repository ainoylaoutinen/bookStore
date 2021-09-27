package com.example.bookstore2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore2.domain.Book;
import com.example.bookstore2.domain.BookRepository;
import com.example.bookstore2.domain.CategoryRepository;


import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
public class BookContoller {
	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository drepository; 
	
    @GetMapping("/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @RequestMapping("/addBook")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", drepository.findAll());
        return "addBook";
    }
    
    @PostMapping(value = "/save")
    public String saveBook(Book book){
        repository.save(book);
        return "redirect:booklist";
    } 
    
    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
    }
    
    @GetMapping(value= "/edit/{id}")
    public String edit(@PathVariable("id") Long bookId, Model model) {
    	Optional<Book> book = repository.findById(bookId);
    	model.addAttribute("book", book);
    	model.addAttribute("categories", drepository.findAll());
    	return "modifyBook";
    }
    
    @RequestMapping(value="/allBooks", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }
    
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    }   
    
    
	    
	   
}

