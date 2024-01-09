package com.board.dtos;

import org.apache.ibatis.type.Alias;

@Alias(value = "fileBoardDto")
public class FileBoardDto {

	private int seq;
	private int book_seq; 
	private String origin_filename;
	private String stored_filename;
	
	public FileBoardDto() {
		super();
	}

	public FileBoardDto(int seq, int book_seq, String origin_filename, String stored_filename) {
		super();
		this.seq = seq;
		this.book_seq = book_seq;
		this.origin_filename = origin_filename;
		this.stored_filename = stored_filename;
	}

	@Override
	public String toString() {
		return "FileBoardDto [seq=" + seq + ", book_seq=" + book_seq + ", origin_filename=" + origin_filename
				+ ", stored_filename=" + stored_filename + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getBook_seq() {
		return book_seq;
	}

	public void setBook_seq(int book_seq) {
		this.book_seq = book_seq;
	}

	public String getOrigin_filename() {
		return origin_filename;
	}

	public void setOrigin_filename(String origin_filename) {
		this.origin_filename = origin_filename;
	}

	public String getStored_filename() {
		return stored_filename;
	}

	public void setStored_filename(String stored_filename) {
		this.stored_filename = stored_filename;
	}

	
	
	
	
}