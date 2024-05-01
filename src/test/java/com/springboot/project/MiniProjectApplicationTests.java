package com.springboot.project;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mvc.common.util.WeatherUtill;

@SpringBootTest
class MiniProjectApplicationTests {
	
	@Test
	public void getWeatherTest() {
		try {
			System.out.println("getWeatherTest - getTagNo : " + new WeatherUtill().getTodayWeather().getTagNo());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
