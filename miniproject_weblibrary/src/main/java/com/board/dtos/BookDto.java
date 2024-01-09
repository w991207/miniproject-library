package com.board.dtos;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "bookDto")
public class BookDto {

	private int book_seq;
	private String title;
	private String writer;
	private String publisher;
	private String content;
	private String reserver;
	private String resflag;
	private Date resdate;
	
	public BookDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookDto(int book_seq, String title, String writer, String publisher, String content, String reserver,
			String resflag, Date resdate) {
		super();
		this.book_seq = book_seq;
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.content = content;
		this.reserver = reserver;
		this.resflag = resflag;
		this.resdate = resdate;
	}

	@Override
	public String toString() {
		return "BookDto [book_seq=" + book_seq + ", title=" + title + ", writer=" + writer + ", publisher=" + publisher
				+ ", content=" + content + ", reserver=" + reserver + ", resflag=" + resflag + ", resdate=" + resdate
				+ "]";
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

	public String getReserver() {
		return reserver;
	}

	public void setReserver(String reserver) {
		this.reserver = reserver;
	}

	public String getResflag() {
		return resflag;
	}

	public void setResflag(String resflag) {
		this.resflag = resflag;
	}

	public Date getResdate() {
		return resdate;
	}

	public void setResdate(Date resdate) {
		this.resdate = resdate;
	}
	
	
}
