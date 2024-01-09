package com.board.command;

public class ReserveBookCommand {

	private int book_seq;
	private String reserver;
	private String resflag;
	public ReserveBookCommand() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReserveBookCommand(int book_seq, String reserver, String resflag) {
		super();
		this.book_seq = book_seq;
		this.reserver = reserver;
		this.resflag = resflag;
	}
	@Override
	public String toString() {
		return "ReserveBookCommand [book_seq=" + book_seq + ", reserver=" + reserver + ", resflag=" + resflag + "]";
	}
	public int getBook_seq() {
		return book_seq;
	}
	public void setBook_seq(int book_seq) {
		this.book_seq = book_seq;
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
	
	
}
