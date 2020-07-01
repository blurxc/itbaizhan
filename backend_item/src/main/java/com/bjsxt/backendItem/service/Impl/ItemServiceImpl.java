package com.bjsxt.backendItem.service.Impl;

import com.bjsxt.backendItem.feign.CommonItemFeignClient;
import com.bjsxt.backendItem.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Override
    public Result selectTbItemAllByPage(Integer page, Integer rows) {
        PageResult pageResult = commonItemFeignClient.selectTbItemAllByPage(page, rows);
        if (pageResult!=null && pageResult.getResult()!=null && pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }

        return Result.error("no result");

    }

    @Override
    @LcnTransaction
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams) {
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        tbItem.setStatus((byte) 1);
        Date date=new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);


        Integer insertTbItem = commonItemFeignClient.insertTbItem(tbItem);


        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setCreated(date);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(date);


        Integer insertItemDesc = commonItemFeignClient.insertItemDesc(tbItemDesc);

        TbItemParamItem tbItemParamItem=new TbItemParamItem();
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParams);

        Integer insertTbItemParamItem = commonItemFeignClient.insertTbItemParamItem(tbItemParamItem);


        return Result.ok();
    }

    @Override
    @LcnTransaction
    public Result deleteItemById(Long itemId) {
        TbItem item= new TbItem();
        item.setId(itemId);
        item.setStatus((byte) 3);

        Integer integer = commonItemFeignClient.deleteItemById(item);
        if (integer!=null)
        {
            return  Result.ok();
        }

        return Result.error("fail");
    }

    @Override
    public Result preUpdateItem(Long itemId) {
        Map<String,Object> map=commonItemFeignClient.preUpdateItem(itemId);
        if (map!=null){
            return Result.ok(map);
        }
        return Result.error("fail");
    }

    @Override
    @LcnTransaction
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams) {
        Integer integer = commonItemFeignClient.updateTbItem(tbItem);

        TbItemDesc tbItemDesc= new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);

        Integer integer1 = commonItemFeignClient.updateTbItemDesc(tbItemDesc);

        TbItemParamItem tbItemParamItem= new TbItemParamItem();
        tbItemParamItem.setItemId(tbItem.getId());
        tbItemParamItem.setParamData(itemParams);
        Integer integer2 = commonItemFeignClient.updateItemParamItem(tbItemParamItem);

       /* if (integer!=0&&integer1!=0&&integer2!=0){
            return  Result.ok();
        }*/


        return Result.ok();
    }
}
