package com.etoak.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	private Integer id;
	private String brand;
	private String series;
	private Integer price;
	private String licensingTime;
	private String level;
	private String gearbox;
	private String outputVolume;
	private Integer mileage;
	private String location;
	private String pic;
	private String summary;
	private String createTime;
}
