package com.fsd.assignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.assignment.entity.Book;
import com.fsd.assignment.exception.BookMgmtException;
import com.fsd.assignment.repository.BookRepository;
import com.fsd.assignment.service.ValidationService;

@RestController
public class BookMgmtController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ValidationService validationService;

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public @ResponseBody Book saveBook(@RequestBody Book book) {

		validationService.validateBook(book, true);
		bookRepository.save(book);
		return book;
	}

	@RequestMapping(value = "/book", method = RequestMethod.PUT)
	public @ResponseBody Book updateBook(@RequestBody Book book) {
		
		validationService.validateBook(book, false);
		bookRepository.save(book);
		return book;
	}

	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable("bookId") Long bookId) {
		validationService.validateBookId(bookId);
		bookRepository.deleteById(bookId);
	}

	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
	public @ResponseBody Book getBook(@PathVariable("bookId") Long bookId) {
		Optional<Book> result = bookRepository.findById(bookId);
		String exceptionMsg = "Book details not found, for the bookId:"+ bookId;
		Book book = result.orElseThrow(() -> new BookMgmtException(exceptionMsg, HttpStatus.NOT_FOUND));
		return book;
	}

	@RequestMapping(value = "/book/all", method = RequestMethod.GET)
	public @ResponseBody List<Book> getBooks() {
		return bookRepository.findAll();
	}

}
