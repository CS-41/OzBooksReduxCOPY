/**
 * @author Elizabeth Allen - eallen12
 * CIS175 - Fall 2021
 * Sep 30, 2021
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class OzBooks {
	
	@Id
	@GeneratedValue
	@Column(name="BOOKID")
	private int bookId;
	@Column(name="DATE")
	private int publishDate;
	@Column(name="TITLE")
	private String bookTitle;
	
	public OzBooks() {
		super();
	}
	
	public OzBooks(String bookTitle, int publishDate) {
		super();
		this.publishDate = publishDate;
		this.bookTitle = bookTitle;
	}
	
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(int publishDate) {
		this.publishDate = publishDate;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	
	public String returnBookDetails() {
		return this.bookTitle + ": " + this.publishDate;
	}
	
	@Override
	public String toString() {
		return "OzBooks [bookId=" + bookId + ", publishDate=" + publishDate + ", bookTitle=" + bookTitle + "]";
	}
	
	
	
	

}
