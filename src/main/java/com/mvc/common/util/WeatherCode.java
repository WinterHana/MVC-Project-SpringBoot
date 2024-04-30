package com.mvc.common.util;

public enum WeatherCode {
	NOT("0"), RAIN("1"), RAIN_AND_SNOW("2"), SNOW("3"), SHOWER("4");
	
	private String code;

	private WeatherCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
