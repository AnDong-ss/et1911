package com.etoak.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etoak.bean.Dict;
import com.etoak.service.DictService;

@RestController
@RequestMapping("/dict")
public class DictController {
	
	private static final Logger log = LoggerFactory.getLogger(DictController.class);
	
	@Autowired
	DictService dictService;
	
	@GetMapping("/{groupId}")
	public List<Dict> queryList(@PathVariable String groupId){
		log.info("param groupId - {}",groupId);
		return dictService.queryList(groupId);
	}
}
