package com.mmall.controller;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    IUserService iUserService;


    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody   // 将返回值自动转为json格式
    public ServerResponse<User> login(String username, String password, HttpSession session){
        // service -> mybatis -> dao
        ServerResponse<User> response = iUserService.login(username, password);

        // 如果登陆成功就将data数据存入session中，供后面使用
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }
}
