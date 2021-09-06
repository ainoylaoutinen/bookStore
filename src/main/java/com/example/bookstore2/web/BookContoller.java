package com.example.bookstore2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookContoller {

	@RequestMapping("/bookIndex")
	    public String showIndex() {
	    return "books";
}
}
