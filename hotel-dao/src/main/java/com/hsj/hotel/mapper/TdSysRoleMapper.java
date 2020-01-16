package com.hsj.hotel.mapper;

import com.hsj.hotel.model.TdSysRole;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TdSysRoleMapper {

    /**
     * 根据用户id查询用户角色
     */
    @Select("<script>" +
            "select r.role_id," +
            "       r.role_name," +
            "       r.role_type," +
            "       r.role_code," +
            "       r.role_status," +
            "       r.create_time," +
            "       r.create_user," +
            "       r.update_time," +
            "       r.update_user " +
            "from td_sys_role r left join td_sys_user_role ur on r.role_id = ur.role_id " +
            "where " +
            "ur.user_id = #{userId} " +
            "and " +
            "r.role_status = 'U' " +
            "and " +
            "ur.data_status = 'U'" +
            "</script>")
    public List<TdSysRole> queryRolesByUserId(@Param("userId") String userId);

    List<TdSysRole> queryAll();

    List<String> queryRoleIds(@Param("roles") List<String> roles);
}
