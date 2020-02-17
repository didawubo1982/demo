package com.muke.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class HelloControl {

    @GetMapping("/hello")
//    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod = "GET")
    public String say(HttpServletResponse response){
        Cookie cookie =new Cookie("login","false");
        response.addCookie(cookie);
        return "Hello World!";
    }

    @GetMapping("/getwithcookies")
    @ApiOperation(value ="带Cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest httpServletRequest){
        Cookie[] cookies=httpServletRequest.getCookies();
        if(Objects.isNull(cookies))
        {
            return "你必须携带cookies来";
        }
        else
        {
            for(Cookie cookie:cookies)
            {
                if(cookie.getName().equals("login")&&cookie.getValue().equals("true"))
                    return "恭喜你访问成功";
            }
        }
        return "你没有携带cookies";
    }

    @GetMapping("/getprice")
    @ApiOperation(value = "需要携带参数访问的get方法一",httpMethod = "GET")
    public Map<String,Integer> getProductlist(@RequestParam String name,@RequestParam Integer price)
    {
         Map<String,Integer> map=new HashMap<>();
         map.put("书",200);
         map.put("鞋",300);
         map.put("笔",400);
         return map;
    }

    @GetMapping("/getprice2/{name}/{price}")
    @ApiOperation(value = "需要携带参数访问的get方法二",httpMethod = "GET")
    public Map<String,Integer> getProductlist2(@PathVariable String name,@PathVariable Integer price){
        Map<String,Integer> map=new HashMap<>();
        map.put("书",200);
        map.put("鞋",300);
        map.put("笔",400);
        return map;
    }
}
