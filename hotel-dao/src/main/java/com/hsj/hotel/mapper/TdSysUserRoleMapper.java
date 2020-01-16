package com.hsj.hotel.mapper;

import com.hsj.hotel.model.TdSysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TdSysUserRoleMapper {


    public List<TdSysUserRole> queryRoleVoByUserIds(@Param("userIds") List<String> userIds);

    void saveList(@Param("userRoleList") List<TdSysUserRole> tdSysUserRoleList);

    void deleteByUserId(@Param("userId") String userId);

    List<String> queryRoleByUserId(@Param("userId") String userId);
}
