package com.board.dtos;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "reserveDto")
public class ReserveDto {

	private int seq;
	private String booktitle;
	private String bookauthor;
	private String bookpublisher;
	private String reserver;
	private Date resdate;
	private Date returndate;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public String getBookpublisher() {
		return bookpublisher;
	}
	public void setBookpublisher(String bookpublisher) {
		this.bookpublisher = bookpublisher;
	}
	public String getReserver() {
		return reserver;
	}
	public void setReserver(String reserver) {
		this.reserver = reserver;
	}
	public Date getResdate() {
		return resdate;
	}
	public void setResdate(Date resdate) {
		this.resdate = resdate;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	@Override
	public String toString() {
		return "ReserveDto [seq=" + seq + ", booktitle=" + booktitle + ", bookauthor=" + bookauthor + ", bookpublisher="
				+ bookpublisher + ", reserver=" + reserver + ", resdate=" + resdate + ", returndate=" + returndate
				+ "]";
	}
	public ReserveDto(int seq, String booktitle, String bookauthor, String bookpublisher, String reserver, Date resdate,
			Date returndate) {
		super();
		this.seq = seq;
		this.booktitle = booktitle;
		this.bookauthor = bookauthor;
		this.bookpublisher = bookpublisher;
		this.reserver = reserver;
		this.resdate = resdate;
		this.returndate = returndate;
	}
	public ReserveDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
