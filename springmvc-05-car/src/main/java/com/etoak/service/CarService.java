package com.etoak.service;

import org.springframework.stereotype.Service;

import com.etoak.bean.Car;

@Service
public interface CarService {
	int addCar(Car car);
}
