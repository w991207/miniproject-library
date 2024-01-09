package com.board.command;

public class BookInsertCommad {

	private int book_seq;
	private String title;
	private String writer;
	private String publisher;
	private String content;
	public BookInsertCommad() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookInsertCommad(int book_seq, String title, String writer, String publisher, String content) {
		super();
		this.book_seq = book_seq;
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.content = content;
	}
	@Override
	public String toString() {
		return "BookInsertCommad [book_seq=" + book_seq + ", title=" + title + ", writer=" + writer + ", publisher="
				+ publisher + ", content=" + content + "]";
	}
	public int getBook_seq() {
		return book_seq;
	}
	public void setBook_seq(int book_seq) {
		this.book_seq = book_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
