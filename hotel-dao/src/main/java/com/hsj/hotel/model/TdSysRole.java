package com.hsj.hotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hsj.hotel.common.annotation.VoField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TdSysRole {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型
     */
//    @VoField(codeType = "ROLE_TYPE")
    private String roleType;

    /**
     * 角色代码
     */
    private String roleCode;


    @VoField
    private String roleStatus;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUser;
}
