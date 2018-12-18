package yt.cn.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {
	
	@GetMapping("cs")
	public String cs(HttpServletRequest request){
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		  Subject subject = SecurityUtils.getSubject();
	        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
	        		name,
	                password);
	        //进行验证，这里可以捕获异常，然后返回对应信息
	        subject.login(usernamePasswordToken);
		return "";
	}
}
