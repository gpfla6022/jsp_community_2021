package com.yhr.proj.proj1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Article {
	private int id; // 게시물 번호
	private String regDate;
	private String updateDate;
	private int boardId;
	private int memberId; // 사용자 번호
	private String title;
	private String body;
	
	private String extra__writerName;
	private boolean extra__userCanModify;
	private boolean extra__userCanDelete;
	
	public String getTitleForPrint() {
		return title;
	}
	
	public String getBodySummaryForPrint() {
		return body;
	}

}