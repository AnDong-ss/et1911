package com.etoak.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	//表示这个方法是拦截ParamException异常
	@ExceptionHandler
	public ModelAndView handleParaamException(ParamException e) {
		
		String message = e.getMessage();
		log.error(message,e);
		ModelAndView mv = new ModelAndView();
		mv.addObject("paramError",message);
		mv.setViewName("car/add");
		return mv;
	}
}
