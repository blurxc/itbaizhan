package com.bjsxt.backendItem.controller;


import com.bjsxt.backendItem.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/backend_item/backend/item")
public class ItemController {

    @Autowired
    private  ItemService itemService;

    @RequestMapping("/selectTbItemAllByPage")
    public Result selectTbItemAllByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer rows
    ){

        try {
            return itemService.selectTbItemAllByPage(page,rows);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");


    }


    @RequestMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem,String desc,String itemParams){
        try {
            return itemService.insertTbItem(tbItem,desc,itemParams);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/deleteItemById")
    public Result deleteItemById(Long itemId){
        try {
            return itemService.deleteItemById(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/preUpdateItem")
    public Result preUpdateItem(Long itemId){
        try {
            return itemService.preUpdateItem(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/updateTbItem")
    public  Result updateTbItem(TbItem tbItem,String desc,String itemParams){
        try {
            return itemService.updateTbItem(tbItem,desc,itemParams);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
