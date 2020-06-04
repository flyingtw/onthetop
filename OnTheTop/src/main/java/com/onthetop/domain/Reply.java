package com.onthetop.domain;

import java.sql.Timestamp;

public class Reply {
	
	private Integer reNum;	//댓글 번호
	private Integer boardNum;	//게시물 번호
	private String name;	//작성자
	private String detail;	//댓글 내용
	private String ip;	//작성 ip
	private Timestamp regDate;	//작성 날짜
	private String passwd;	//비밀번호
	
	public Integer getReNum() {
		return reNum;
	}
	public void setReNum(Integer reNum) {
		this.reNum = reNum;
	}
	public Integer getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(Integer boardNum) {
		this.boardNum = boardNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
