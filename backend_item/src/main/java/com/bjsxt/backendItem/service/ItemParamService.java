package com.bjsxt.backendItem.service;

import com.bjsxt.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemParamService {

    Result selectItemParamByItemCatId(Long itemCatId);

    Result selectItemParamAll(Integer page, Integer rows);

    Result insertItemParam(Long itemCatId, String paramData);

    Result deleteItemParamById(Long id);
}
