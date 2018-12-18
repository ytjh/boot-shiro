package yt.cn.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yt.cn.test.dao.ModuleMapper;
import yt.cn.test.pojo.Module;

@Service
public class ModuleServiceImpl implements ModuleService{
	
	@Autowired
	private ModuleMapper moduleMapper;

	@Override
	public List<Module> getByUserId(Integer userId) {
		return moduleMapper.getByUserId(userId);
	}
	
	

}
