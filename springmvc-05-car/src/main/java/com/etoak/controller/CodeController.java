package com.etoak.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.etoak.commons.VerifyCode;

@Controller
public class CodeController {
	@GetMapping("/code")
	public void code(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//创建VerifyCide对象，获取表达式计算结果 放入到session
		VerifyCode code = new VerifyCode();
		BufferedImage image = code.createImage();
		request.getSession().setAttribute("code", code.getResult()+"");
		//向前端写图片
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires",0);
		response.setContentType("image/jpeg");
		ImageIO.write(image, "JPEG",response.getOutputStream());
		
		  
	}
}
