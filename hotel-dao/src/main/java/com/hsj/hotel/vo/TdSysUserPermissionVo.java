package com.hsj.hotel.vo;

import com.hsj.hotel.model.TdSysMenu;
import com.hsj.hotel.model.TdSysRole;
import com.hsj.hotel.model.TdSysUser;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@Data
@Builder
public class TdSysUserPermissionVo {

    @Tolerate
    public TdSysUserPermissionVo() {
    }

    /**
     * 用户
     */
    private TdSysUser user;

    /**
     * 角色
     */
    private List<TdSysRole> roles;
    /**
     * 权限
     */
    private List<TdSysMenu> permissions;

    /**
     * 权限名称集合
     */
    private List<String> permissionList;
}