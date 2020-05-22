package com.etoak.mapper;

import java.util.List;

import com.etoak.bean.Dict;

public interface DictMapper {
	List<Dict> queryList(String groupId);
}
