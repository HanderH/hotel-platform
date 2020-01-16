package com.hsj.hotel.service.interfaces;

import com.hsj.hotel.model.TbStaticDict;

import java.util.List;

public interface TbStaticDictService {

    public List<TbStaticDict> queryByType(String type);
}
