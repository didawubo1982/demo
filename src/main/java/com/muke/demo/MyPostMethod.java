package com.muke.demo;

import com.muke.demo.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post方法")
@RequestMapping("/v1")
public class MyPostMethod {
    private Cookie cookie;

    @PostMapping("/login")
    @ApiOperation(value = "登陆接口，成功后获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse httpResponse, @RequestParam(value = "name",required = true) String name,
                        @RequestParam(value = "password",required = true) String password){
          if (name.equals("zhangsan")&&password.equals("123456")) {
              cookie = new Cookie("login", "true");
              httpResponse.addCookie(cookie);
              return "恭喜你登陆成功了";
          }
          return "用户名或者密码错误";
    }

    @PostMapping("/getuserlist")
    @ApiOperation(value = "检查cookie信息，成功后获取用户列表")
    public String getUserList(HttpServletRequest httpServletRequest, @RequestBody User user){
        Cookie[] cookies=httpServletRequest.getCookies();
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")
               && user.getUserName().equals("zhangsan")&&user.getPassWord().equals("123456"))
            {
                User user1=new User();
                user1.setName("lisi");
                user1.setSex("nan");
                user1.setAge("18");
                return user1.toString();
            }
        }
        return "cookies信息不合法";
    }
}
