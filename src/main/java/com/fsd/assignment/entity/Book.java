package com.fsd.assignment.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fsd.assignment.util.LocalDateConverter;

@Entity
@Table(name="book")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(updatable = false, nullable = false, name="bookId")
	private Long bookId;
	private String title;
	private double price;
	private int volume;

	@Convert(converter = LocalDateConverter.class)
	@Column(name="publishDate")
	private LocalDate publishDate;
	
	@ManyToOne
	@JoinColumn(name="subjectId")
	@JsonBackReference
	private Subject subject;
	
	public Book() {
		super();
	}

	public Book(Long bookId, String title, double price, int volume, LocalDate publishDate) {
		this.bookId = bookId;
		this.title = title;
		this.volume = volume;
		this.price = price;
		this.publishDate = publishDate;
	}

	/**
	 * @return the bookId
	 */
	public Long getBookId() {
		return bookId;
	}

	/**
	 * @param bookId
	 *            the bookId to set
	 */
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the volume
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

	/**
	 * @return the publishDate
	 */
	public LocalDate getPublishDate() {
		return publishDate;
	}

	/**
	 * @param publishDate
	 *            the publishDate to set
	 */
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if (obj instanceof Book) {
			Book b = (Book) obj;
			if (this.bookId == b.bookId && this.price == b.price && this.title.equals(b.title)
					&& this.volume == b.volume) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, title, price, volume);
	}

	@Override
	public String toString() {
		return new StringBuffer("Book: bookId[").append(bookId).append("], title[").append(title)
				.append("], price[").append(price).append("], volume[").append(volume)
				.append("], publishDate[").append(publishDate).append("]").toString();
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	

}
