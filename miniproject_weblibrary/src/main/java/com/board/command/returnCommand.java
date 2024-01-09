package com.board.command;

public class returnCommand {
	
	private int seq;

	public returnCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public returnCommand(int seq) {
		super();
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "returnCommand [seq=" + seq + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
}
