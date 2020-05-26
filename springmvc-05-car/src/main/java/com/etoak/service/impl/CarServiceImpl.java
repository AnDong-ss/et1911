package com.etoak.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;

import com.alibaba.druid.util.StringUtils;
import com.etoak.bean.Car;
import com.etoak.bean.CarVo;
import com.etoak.bean.PageVo;
import com.etoak.mapper.CarMapper;
import com.etoak.service.CarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarMapper carMapper;
	
	@Override
	public int addCar(Car car) {
		return carMapper.addCar(car);
	}

	@Override
	public PageVo<CarVo> queryList(int pageNumber, int pageSize, CarVo carVo, String[] priceList) {
		//处理价格区间
		List<Map<String,Integer>> priceMapList = this.handlePrice(priceList);
		carVo.setPriceMapList(priceMapList);
		
		
		this.handleVehicleAge(carVo);
		
		
		//设置分页条件
		PageHelper.startPage(pageNumber,pageSize);
		//查询列表
		List<CarVo> carList = carMapper.queryList(carVo);
		
		//创建PageInfo对象 用于获取分页数据
		PageInfo<CarVo> pageInfo = new PageInfo<CarVo>(carList);
		return new PageVo<CarVo>(pageInfo.getPageNum(),pageInfo.getPageSize(),
				carList,pageInfo.getTotal(),
				pageInfo.getPages(),
				pageInfo.isIsFirstPage(),
				pageInfo.isIsLastPage());
	}
	
	public void handleVehicleAge(CarVo carVo) {
		//前端传来的值
		String vehicleAge = carVo.getVehicleAge();
		if(!StringUtils.isEmpty(vehicleAge)) {
			String[] vehicleAgeArray = vehicleAge.split("-");
			if(!"0".equals(vehicleAgeArray[0])) {
				carVo.setEndYear(Integer.parseInt(vehicleAgeArray[0]));
			}
			if(!"0".equals(vehicleAgeArray[1])) {
				carVo.setStartYear(Integer.parseInt(vehicleAgeArray[1]));
			}
		}
	}

	private List<Map<String, Integer>> handlePrice(String[] priceList) {
		List<Map<String,Integer>> priceMapList = new ArrayList<Map<String,Integer>>();
		if(!ArrayUtils.isEmpty(priceList)) {
			for(String priceStr:priceList) {
				String[] prices = priceStr.split("-");
				Map<String,Integer> priceMap = new HashMap<String, Integer>();
				priceMap.put("start",Integer.parseInt(prices[0]));
				priceMap.put("end",Integer.parseInt(prices[1]));
				priceMapList.add(priceMap);
			}
		}
		return priceMapList;
	}

	@Override
	public List<String> getAllBrand() {
		return carMapper.getAllBrand();
	}

	@Override
	public List<String> getSeriesByBrand(String brand) {
		return carMapper.getSeriesByBrand(brand);
	}
	
	
}
