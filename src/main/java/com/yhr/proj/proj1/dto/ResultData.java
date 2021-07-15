package com.yhr.proj.proj1.dto;

import java.util.Map;

import com.yhr.proj.proj1.util.Ut;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ResultData {
	@Getter
	private String msg;
	@Getter
	private String resultCode;
	@Getter
	private Map<String, Object> body;
	
	private ResultData() {
		
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return !isSuccess();
	}
	                             //   성공여부        이유          객체
	public static ResultData from(String resultCode, String msg, Object... bodyArgs) {
		
		ResultData rd = new ResultData();
		
		rd.msg = msg;
		rd.resultCode = resultCode;
		rd.body = Ut.mapOf(bodyArgs);
		
		return rd;
	}

}
