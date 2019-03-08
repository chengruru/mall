package com.mmall.controller.portal;


import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService iUserService;
    /**
     * 用户登陆
     * @param name
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String name, String password, HttpSession session){
        // service -> mybatis -> dao

        // 1. 查询数据库是否存在：UserName = name的用户，若不存在则返回错误消息
        // 2. 查询数据库看是否存在UserName， password是否匹配，返回消息

        ServerResponse<User> response = iUserService.login(name, password);

        // 如果登陆成功的化，直接将登陆成功的用户放在session中
        if (response.isSuccess())
            session.setAttribute(Const.CURRENT_USER, response.getData());
        return response;
    }

    /**
     * 用户登出功能
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> logout(HttpSession session){
        // 直接将session中存储的current_user信息清除就可以了

        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 用户注册功能
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }

    /**
     * 实时校验用户注册时输入的信息
     * @param str
     * @param type 用于判断输入的信息是用户名还是邮箱
     * @return
     */
    @RequestMapping(value = "check_valid.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str,String type){
        return iUserService.checkValid(str,type);
    }
}
