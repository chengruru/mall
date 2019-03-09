package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * Created by geely
 */
public interface ICategoryService {
    /**
     * 添加新的分类
     * @param categoryName 分类名
     * @param parentId  父级分类名
     * @return
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 修改分类名称
     * @param categoryId
     * @param categoryName
     * @return
     */
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

}
