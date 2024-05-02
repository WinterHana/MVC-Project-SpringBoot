package com.mvc.common.util;

public enum WeatherCode {
	NOT("0", 1044, "맑음"), RAIN("1", 1045, "비"), RAIN_AND_SNOW("2", 1046, "비/눈"), SNOW("3", 1047, "눈"), SHOWER("4", 1048, "소나기"), DEFAULT("-1", -1, "에러");
	
	private String code;
	private int tagNo;
	private String content;
	
	private WeatherCode(String code, int tagNo, String content) {
		this.code = code;
		this.tagNo = tagNo;
		this.content = content;
	}

	public String getCode() {
		return code;
	}
	
	public int getTagNo() {
		return tagNo;
	}
	
	public String getContent() {
		return content;
	}
}
