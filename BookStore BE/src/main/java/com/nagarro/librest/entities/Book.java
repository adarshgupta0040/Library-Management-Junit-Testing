package com.nagarro.librest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder

@Entity
@Table(name = "book")
public class Book {
	@Id
	int bookCode;
	@Column(name = "bookName")
	String bookName;
	@Column(name = "author")
	String author;
	@Column(name = " addedOn")
	String addedOn;

	public Book() {
		super();
	}

	public Book(int bookCode, String bookName, String author, String addedOn) {
		super();
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.author = author;
		this.addedOn = addedOn;
	}

	public int getBookCode() {
		return bookCode;
	}

	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}

//	@Override
//	public String toString() {
//		return "Book [bookCode=" + bookCode + ", bookName=" + bookName + ", author=" + author + ", addedOn=" + addedOn
//				+ "]";
//	}

}
