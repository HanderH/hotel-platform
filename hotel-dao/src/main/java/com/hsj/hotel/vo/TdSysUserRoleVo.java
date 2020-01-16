package com.hsj.hotel.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
public class TdSysUserRoleVo {

    @Tolerate
    public TdSysUserRoleVo(){

    }

    private String userId;

    private String roleName;
}
