package com.course.controller;

import com.course.model.User2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@Api(value = "v1",description = "这是我第一个demo")
@RequestMapping("/v1")
public class Demo {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @GetMapping("/getusercount")
    @ApiOperation(value ="可以获取到用户数",httpMethod = "GET")
    public int getUserCount(){
        return  sqlSessionTemplate.selectOne("getUserCount");
    }

    @PostMapping("/adduser")
    @ApiOperation(value = "添加一个人",httpMethod = "POST")
    public int addUser(@RequestBody User2 user2){
        return sqlSessionTemplate.insert("addUser",user2);
    }

    @PostMapping("/updateuser")
    public int updateUser(@RequestBody User2 user2){
        return  sqlSessionTemplate.update("updateUser",user2);
    }

    @PostMapping("/deleteuser")
    public int deleteUser(@RequestParam int id){
        return sqlSessionTemplate.delete("deleteUser",id);
    }

    @PostMapping("/getuserinfo")
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public List<User2> getUserInfo(@RequestBody User2 user2){
        List<User2> users=sqlSessionTemplate.selectList("getUserInfo",user2);
        log.info("获取到的用户数量是"+users.size());
        return users;
    }

}
