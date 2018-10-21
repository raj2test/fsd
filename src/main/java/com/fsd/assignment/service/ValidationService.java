package com.fsd.assignment.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fsd.assignment.entity.Book;
import com.fsd.assignment.exception.BookMgmtException;
import com.fsd.assignment.repository.BookRepository;

@Service
public class ValidationService {

	@Autowired
	private BookRepository bookRepository;

	public void validateBook(Book book, boolean newFlag) {
		if (null == book) {
			throw new BookMgmtException("Book is mandatory, and cannot be empty.");
		}
		if (null == book.getBookId()) {
			throw new BookMgmtException("bookId is mandatory, and cannot be empty.");
		} else if (!newFlag && !bookRepository.existsById(book.getBookId())) {
			throw new BookMgmtException("bookId not found.", HttpStatus.NOT_FOUND);
		}
		if (StringUtils.isEmpty(book.getTitle())) {
			throw new BookMgmtException("title is mandatory, and cannot be empty.");
		}
	}

	public void validateBookId(Long bookId) {
		if (null == bookId || !bookRepository.existsById(bookId)) {
			throw new BookMgmtException("bookId not found.", HttpStatus.NOT_FOUND);
		}
	}

}
