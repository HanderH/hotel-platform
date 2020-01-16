package com.hsj.hotel.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hsj.hotel.model.TdSysRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TdSysUserResponse {

    /**
     *用户id
     */
    private String userId;

    /**
     * 用户类别
     */
    private String userType;

    /**
     * 用户身份证号
     */
    private String userCode;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户电话号码
     */
    private String userTel;

    /**
     * 用户性别
     */
    private String userSex;

    /**
     * 用户状态
     */
    private String userStatus;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建人员
     */
    private String createUser;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 更新
     */
    private String updateUser;

    /**
     * 角色
     */
    private List<String> roles;
}
