package com.hsj.hotel.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hsj.hotel.common.vo.PageRequest;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class TdSysUserRequest extends PageRequest {

    @Tolerate
    public TdSysUserRequest() {
    }

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

}
