package com.hsj.hotel.mapper;

import com.hsj.hotel.model.TdSysMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TdSysMenuMapper {

    @Select("<script>" +
            "select m.menu_id," +
            "       m.menu_name," +
            "       m.menu_code," +
            "       m.menu_type," +
            "       m.menu_level," +
            "       m.menu_type," +
            "       m.menu_status," +
            "       m.menu_component," +
            "       m.parent_menu_id," +
            "       m.menu_url," +
            "       m.menu_order," +
            "       m.exist_sub," +
            "       m.create_user," +
            "       m.create_time," +
            "       m.update_user," +
            "       m.update_time " +
            "from td_sys_menu m left join td_sys_role_menu rm on m.menu_id = rm.menu_id  " +
            "where m .menu_status = 'U' and rm.status = 'U' and " +
            "        <foreach collection=\"roleIds\" item=\"roleId\" open=\"(\" close=\")\" separator=\"OR\">\n" +
            "            rm.ROLE_ID = #{roleId}\n" +
            "        </foreach>"+
            "</script>")
    public List<TdSysMenu> queryPermissions(@Param("roleIds") List<String> roleIds);
}
