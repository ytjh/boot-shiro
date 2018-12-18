package yt.cn.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yt.cn.test.dao.UserMapper;
import yt.cn.test.pojo.User;
import yt.cn.test.pojo.UserExample;
import yt.cn.test.pojo.UserExample.Criteria;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getByName(String name) {
		UserExample userExample=new UserExample();
		Criteria criteria=userExample.createCriteria();
		criteria.andNameEqualTo(name);
		List<User> users=userMapper.selectByExample(userExample);
		if(users.size()>0){
			return users.get(0);
		}
		return null;
	}

}
