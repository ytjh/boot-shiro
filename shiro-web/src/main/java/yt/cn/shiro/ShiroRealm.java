package yt.cn.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import groovy.util.logging.Slf4j;
import yt.cn.test.pojo.Module;
import yt.cn.test.pojo.User;
import yt.cn.test.service.ModuleService;
import yt.cn.test.service.UserService;

@Slf4j
public class ShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModuleService moduleService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		
		User user=(User) collection.getPrimaryPrincipal();
		if(user==null){
			return null;
		}
		
		List<Module> modules=null;
		try {
			modules=moduleService.getByUserId(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//单独定一个集合对象 
		List<String> moduleList = new ArrayList<String>();
		if(modules.size()>0){
			for(int i=0; i<modules.size();i++){
				//将数据库中的权限标签 符放入集合
				moduleList.add(modules.get(i).getCode());
			}
		}
		//查到权限数据，返回授权信息(要包括 上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(moduleList);
		
		
			System.out.println(1111);
			return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String name=token.getUsername();
		char[] password=token.getPassword();
		System.out.println(password);
		User user=userService.getByName(name);
		if(user!=null){
			 SimpleAuthenticationInfo simpleAuthenticationInfo = 
					 new SimpleAuthenticationInfo(user, user.getPassword().toString(), this.getName());
			 return simpleAuthenticationInfo;
		}
		return null;
	}

}
