package com.bjsxt.backendItem.service.Impl;

import com.bjsxt.backendItem.feign.CommonItemFeignClient;
import com.bjsxt.backendItem.service.ItemCategoryService;
import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;



    @Override
    public Result selectItemCategoryByParentId(Long id) {
        List<TbItemCat> list = commonItemFeignClient.selectItemCategoryByParentId(id);

        if (list!=null&&list.size()>0){
            return Result.ok(list);
        }
        return Result.error("no msg");
    }
}
