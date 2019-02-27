package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 校验姓名，返回数据库中有多少个姓名=username的数据
     * @param username
     * @return
     */
    int checkUsername(String username);

    User selectLogin(@Param("username") String username, @Param("password") String password);
}