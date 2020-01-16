package com.hsj.hotel.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class TdSysRoleMenu {

    @Tolerate
    public TdSysRoleMenu() {
    }

    /**
     * id
     */
    private String dataId;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 状态
     */
    private String status;

    /**
     * 类型
     */
    private String dataType;
}
