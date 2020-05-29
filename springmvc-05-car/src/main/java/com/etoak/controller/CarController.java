package com.etoak.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.etoak.bean.Car;
import com.etoak.bean.CarVo;
import com.etoak.bean.PageVo;
import com.etoak.exception.ParamException;
import com.etoak.service.CarService;

import lombok.extern.slf4j.Slf4j;



@Controller
@RequestMapping("/car")
@Slf4j
public class CarController {

	@Autowired
	CarService carSevice;
	
	@RequestMapping("/toAdd")
	public String toAdd() { 
		return "car/add";
	}
	
	@RequestMapping("/toList")
	public String toList() {
		return "car/list";
	}
	
	
	@RequestMapping("/add")
	public String add(MultipartFile file,@Valid Car car,BindingResult bindingResult,HttpServletRequest request) throws IllegalStateException, IOException {
		String originalFilename = file.getOriginalFilename();
		log.info("文件名称 - {}" ,originalFilename);
		log.info("param ca - {}",car);
		
		//先校验
		//获取所有的校验结果
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		if(!CollectionUtils.isEmpty(allErrors)) {
			StringBuffer errorBuf = new StringBuffer();
			for(ObjectError error:allErrors) {
				System.out.println(error);
				String errorMsg = error.getDefaultMessage();
				System.out.println(errorMsg);
				errorBuf.append(errorMsg).append(";");
			}
			/*
			 * request.setAttribute("paramError", errorBuf.toString()); return
			 * "forward:/car/toAdd";
			 */
			throw new ParamException(errorBuf.toString());
		}
		
	
		//新文件名称 
		String uuid = UUID.randomUUID().toString().replace("-","");
		String newFileName = uuid + "_"+originalFilename;
		
		//目标文件
		File destFile = new File("d:/upload",newFileName);
		file.transferTo(destFile);
		//设置图片地址
		car.setPic("/pic/"+newFileName);
		//调用服务层添加车辆信息
		carSevice.addCar(car);
		return "redirect:/car/toAdd";
	}
	
	
	@GetMapping("/list")
	@ResponseBody
	public PageVo<CarVo> queryList(
			@RequestParam(required = false,defaultValue = "1")int pageNumber,
			@RequestParam(required = false,defaultValue = "8")int pageSize,
			CarVo carVo,
			String[] priceList){
		return carSevice.queryList(pageNumber, pageSize, carVo,priceList);
		
	}
	
	@GetMapping("/getBrand")
	@ResponseBody
	public List<String> getBrand(){
		return carSevice.getAllBrand();
	}
	
	@GetMapping("/getSeries")
	@ResponseBody
	public List<String> getService(String brand){
		return carSevice.getSeriesByBrand(brand);
	}

}
