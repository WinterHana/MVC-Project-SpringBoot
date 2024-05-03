package com.springboot.project.service.domain.product;

import java.util.List;

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
public class TagDataVO {
	private int prodNo;
	private int tagNo;
	private String tagName;
}
