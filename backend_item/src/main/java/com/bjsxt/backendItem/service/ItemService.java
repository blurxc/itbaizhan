package com.bjsxt.backendItem.service;


import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.Result;

import java.util.Map;

public interface ItemService {

    Result selectTbItemAllByPage(Integer page, Integer rows);

    Result insertTbItem(TbItem tbItem, String desc, String itemParams);
    Result deleteItemById(Long itemId);

    Result preUpdateItem(Long itemId);

    Result updateTbItem(TbItem tbItem, String desc, String itemParams);
}
