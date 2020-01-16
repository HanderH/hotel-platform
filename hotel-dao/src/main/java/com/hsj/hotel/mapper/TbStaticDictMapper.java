package com.hsj.hotel.mapper;

import com.hsj.hotel.model.TbStaticDict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbStaticDictMapper {

    @Select("<script>" +
            "   select " +
            "       code_type," +
            "       code_value," +
            "       code_name," +
            "       code_desc" +
            "   from tb_static_dict" +
            "   where code_type = #{type}" +
            "</script>")
    List<TbStaticDict> queryByType(@Param("type") String type);
}
