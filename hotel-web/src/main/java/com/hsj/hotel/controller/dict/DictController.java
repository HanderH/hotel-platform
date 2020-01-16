package com.hsj.hotel.controller.dict;

import com.hsj.hotel.common.vo.ResponseBean;
import com.hsj.hotel.model.TbStaticDict;
import com.hsj.hotel.service.interfaces.TbStaticDictService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class DictController {

    @Autowired
    private TbStaticDictService tbStaticDictService;

    @GetMapping("/dict/queryByType")
    public ResponseBean getDict(@Param("type") String type){

        List<TbStaticDict> tbStaticDicts = tbStaticDictService.queryByType(type);

        ResponseBean responseBean = new ResponseBean();

        responseBean.addSuccess(tbStaticDicts);
        return responseBean;
    }
}
