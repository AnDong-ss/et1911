package com.etoak.bean;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter			
public class CarVo extends Car{
	private String levelName;
	private String gearboxName;
	private String outputVolumeName;
	@JsonIgnore //spring mvc 再返回结果不封装被@JsonIgnore注解属性
	private List<Map<String,Integer>> priceMapList;
	@JsonIgnore
	private String vehicleAge;
	@JsonIgnore
	private Integer startYear;
	@JsonIgnore
	private Integer endYear;
}
