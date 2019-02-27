package com.mmall.dao;

import com.mmall.pojo.Cart;

public interface CartMapper {
    /**
     * 根据主键进行删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 将Cart对象完全插入表中
     * @param record
     * @return
     */
    int insert(Cart record);

    /**
     * 根据传入对象的某些字段是否为空进行判断,若字段不为空,则将字段放入sql语句中
     * @param record
     * @return
     */
    int insertSelective(Cart record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Cart selectByPrimaryKey(Integer id);

    /**
     * 根据主键进行更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}