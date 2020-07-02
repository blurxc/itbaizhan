package com.bjsxt.backendItem.controller;


import com.bjsxt.backendItem.service.ItemParamService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend_item/backend/itemParam")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId){
        try {
            return itemParamService.selectItemParamByItemCatId(itemCatId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/selectItemParamAll")
    public Result selectItemParamAll( @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "20") Integer rows){
        try {
            return itemParamService.selectItemParamAll(page,rows);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/insertItemParam")
    public Result insertItemParam( @RequestParam Long itemCatId,
                                      @RequestParam String paramData){
        try {
            return itemParamService.insertItemParam(itemCatId,paramData);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    @RequestMapping("/deleteItemParamById")
    public  Result deleteItemParamById(@RequestParam Long id){
        try {
            return itemParamService.deleteItemParamById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
