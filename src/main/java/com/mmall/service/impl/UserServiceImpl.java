package com.mmall.service.impl;

import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount < 1)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "用户名不存在");
        // 密码登陆MD5
        password = MD5Util.MD5EncodeUtf8(password);

        User user = userMapper.selectLogin(username, password);
        if (user == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "密码错误");

        // 此处需要经密码清空，防止被恶意获取
        user.setPassword(StringUtils.EMPTY);

        return ServerResponse.createBySuccessMessage(user);
    }
}
