package com.bjsxt.frontend.portal.service.impl;


import com.bjsxt.frontend.portal.feign.CommonContentFeignClient;
import com.bjsxt.frontend.portal.feign.CommonRedisFeignClient;
import com.bjsxt.frontend.portal.service.ContentService;
import com.bjsxt.utils.CatResult;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;


    @Override
    public Result selectFrontendContentByAD() {

        try {
            List<Map> list = commonRedisFeignClient.selectContentAD();

            if (list != null && list.size() > 0 ) {
                return Result.ok(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




        List<Map> list = commonContentFeignClient.selectFrontendContentByAD();

        try {
            if (list != null && list.size() > 0 ) {
                commonRedisFeignClient.insertContentAD(list);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (list!=null&&list.size()>0){
            return Result.ok(list);
        }
        return Result.error("fail");
    }
}
