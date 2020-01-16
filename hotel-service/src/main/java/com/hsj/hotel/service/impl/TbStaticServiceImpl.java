package com.hsj.hotel.service.impl;

import com.hsj.hotel.mapper.TbStaticDictMapper;
import com.hsj.hotel.model.TbStaticDict;
import com.hsj.hotel.service.interfaces.TbStaticDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TbStaticServiceImpl implements TbStaticDictService {

    @Autowired
    private TbStaticDictMapper tbStaticDictMapper;

    @Override
    public List<TbStaticDict> queryByType(String type) {
        return tbStaticDictMapper.queryByType(type);
    }
}
