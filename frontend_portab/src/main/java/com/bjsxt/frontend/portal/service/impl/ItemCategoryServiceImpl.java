package com.bjsxt.frontend.portal.service.impl;


import com.bjsxt.frontend.portal.feign.CommonItemFeignClient;
import com.bjsxt.frontend.portal.feign.CommonRedisFeignClient;
import com.bjsxt.frontend.portal.service.ItemCategoryService;
import com.bjsxt.utils.CatResult;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{



    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Override
    public Result selectItemCategoryAll() {
        try {
            CatResult catResult = commonRedisFeignClient.selectItemCategory();

            if (catResult != null && catResult.getData().size() > 0 && catResult.getData() != null) {
                return Result.ok(catResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        CatResult catResult =
                commonItemFeignClient.selectItemCategoryAll();

        try {
            if (catResult != null && catResult.getData().size() > 0 && catResult.getData() != null) {
                commonRedisFeignClient.insertItemCategory(catResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (catResult != null && catResult.getData().size() > 0 && catResult.getData() != null) {

            {
                return Result.ok(catResult);
            }
        }
        return Result.error("fail");
    }
}
