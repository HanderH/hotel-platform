package com.hsj.hotel.service.impl;


import com.hsj.hotel.common.exception.BaseException;
import com.hsj.hotel.mapper.TdSysMenuMapper;
import com.hsj.hotel.mapper.TdSysRoleMapper;
import com.hsj.hotel.mapper.TdSysUserMapper;
import com.hsj.hotel.model.TdSysMenu;
import com.hsj.hotel.model.TdSysRole;
import com.hsj.hotel.model.TdSysUser;
import com.hsj.hotel.vo.TdSysUserPermissionVo;
import com.hsj.hotel.service.interfaces.TdSysUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TdSysUserPermissionImpl implements TdSysUserPermissionService {

    @Autowired
    private TdSysUserMapper tdSysUserMapper;
    @Autowired
    private TdSysRoleMapper tdSysRoleMapper;
    @Autowired
    private TdSysMenuMapper tdSysMenuMapper;

    /**
     * 查询用户权限
     *
     * @param userId
     * @return
     */
    @Override
    public TdSysUserPermissionVo queryUserPermissionsById(String userId) {

        TdSysUser user = tdSysUserMapper.queryUserById(userId);
        if (user == null) {
            throw new BaseException(401, "该用户不存在，无法查询权限");
        }
        List<TdSysRole> roles = tdSysRoleMapper.queryRolesByUserId(userId);
        List<String> roleIds = roles.stream().map((tdSysRole) -> tdSysRole.getRoleId()).collect(Collectors.toList());

        List<TdSysMenu> menus = null;
        if (roleIds!=null && roleIds.size()>0){
            menus =  tdSysMenuMapper.queryPermissions(roleIds);
        }
        List<String> permissionList = null;
        if (menus!=null) {
            permissionList = menus.stream().map((menu) -> menu.getMenuCode()).collect(Collectors.toList());

        }
        return TdSysUserPermissionVo.builder().
                                user(user).
                                permissions(menus).
                                roles(roles).
                                permissionList(permissionList).
                                build();
    }
}
