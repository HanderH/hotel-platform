package com.hsj.hotel.service.util;

import com.alibaba.fastjson.JSON;
import com.hsj.hotel.vo.TdSysUserPermissionVo;
import com.hsj.hotel.model.TdSysUser;

public class SessionManager {

    static ThreadLocal<String> USER_INFO = new ThreadLocal<>();

    public static void serUserInfo(String userInfo){

        USER_INFO.set(userInfo);
    }

    public static String getUserInfo(){

        return USER_INFO.get();
    }

    public static void removeUserInfo(){

        USER_INFO.remove();
    }

    public static TdSysUser getUser(){

        String userInfo = USER_INFO.get();

        TdSysUserPermissionVo tdSysUserPermissionVo = JSON.parseObject(userInfo, TdSysUserPermissionVo.class);

        return tdSysUserPermissionVo.getUser();
    }
}
