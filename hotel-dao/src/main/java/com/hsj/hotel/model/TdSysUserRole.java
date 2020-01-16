package com.hsj.hotel.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;
@Data
@Builder
public class TdSysUserRole {

    @Tolerate
    public TdSysUserRole() {
    }

    /**
     * id
     */
    private String dataId;

    /**
     * 员工id
     */
    private String userId;

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

    private String roleName;

    /**
     * 状态
     */
    private String dataStatus;
}
