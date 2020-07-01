package com.bjsxt.backendItem.service.Impl;

import com.bjsxt.backendItem.feign.CommonItemFeignClient;
import com.bjsxt.backendItem.service.ItemParamService;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemParamServiceImpl implements ItemParamService{
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;


    @Override
    public Result selectItemParamByItemCatId(Long itemCatId) {
        TbItemParam tbItemParam =
                commonItemFeignClient.selectItemParamByItemCatId(itemCatId);
        if (tbItemParam!=null){
            return  Result.ok(tbItemParam);
        }
        return Result.error("no msg");
    }

    @Override
    public Result selectItemParamAll(Integer page, Integer rows) {
        PageResult pageResult = commonItemFeignClient.selectItemParamAll(page, rows);
        if (pageResult!=null&&pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }
        return null;
    }

    @Override
    @LcnTransaction
    public Result insertItemParam(Long itemCatId, String paramData) {
        TbItemParam tbItemParam=new TbItemParam();
        tbItemParam.setItemCatId(itemCatId);
        tbItemParam.setParamData(paramData);
        Date date=new Date();
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        Integer itemParam = commonItemFeignClient.insertItemParam(tbItemParam);
        if (itemParam!=null){
            return Result.ok();
        }

        return Result.error("fail");
    }

    @Override
    public Result deleteItemParamById(Long id) {
        Integer itemParamById = commonItemFeignClient.deleteItemParamById(id);
        if (itemParamById!=null&&itemParamById>0){
            return Result.ok();
        }

        return Result.error("fail");
    }
}
