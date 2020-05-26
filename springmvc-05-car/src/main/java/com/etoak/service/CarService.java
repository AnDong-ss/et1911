package com.etoak.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.etoak.bean.Car;
import com.etoak.bean.CarVo;
import com.etoak.bean.PageVo;

@Service
public interface CarService {
	int addCar(Car car);
	PageVo<CarVo> queryList(int pageNumber,int pageSize,CarVo carVo, String[] priceList); 
	
	List<String> getAllBrand();

	List<String> getSeriesByBrand(String brand);
}
