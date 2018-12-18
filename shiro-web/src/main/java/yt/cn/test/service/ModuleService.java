package yt.cn.test.service;

import java.util.List;

import yt.cn.test.pojo.Module;

public interface ModuleService {
	
	List<Module> getByUserId(Integer userId);

}
