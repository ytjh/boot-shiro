package yt.cn.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cs")
public class CsCOntroller {

	@GetMapping("cs")
	public String cs(HttpServletRequest request){
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		  Subject subject = SecurityUtils.getSubject();
	        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
	        		name,
	                password);
	        //进行验证，这里可以捕获异常，然后返回对应信息
	        try {
				subject.login(usernamePasswordToken);
			} catch (AuthenticationException e) {
				
				e.printStackTrace();
				
			}
		return "cs";
	}
}
