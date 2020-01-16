package com.hsj.hotel.service.interfaces;

import com.hsj.hotel.model.TdSysRole;
import com.hsj.hotel.vo.TdSysUserRoleVo;

import java.util.List;

public interface TdSysUserRoleService {

    List<TdSysUserRoleVo> queryRoleVoByUserIds(List<String> userIds);

    void saveUserRole(List<String> roles, String userId);

    void deleteByUserId(String userId);

    List<String> queryRoleByUserId(String userId);
}
