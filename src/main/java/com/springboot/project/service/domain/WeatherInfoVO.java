package com.springboot.project.service.domain;

import java.sql.Date;

import com.mvc.common.util.WeatherCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WeatherInfoVO {
	private WeatherCode weatherCode;
}
