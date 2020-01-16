package com.hsj.hotel.service.interfaces;

import com.hsj.hotel.common.vo.ResponsePageBean;
import com.hsj.hotel.model.TdSysUser;
import com.hsj.hotel.vo.TdSysUserPermissionVo;
import com.hsj.hotel.request.TdSysUserRequest;
import com.hsj.hotel.vo.TdSysUserVo;

public interface TdSysUserService {

    public String getUser(String userName,String password);

    void register(TdSysUserRequest tdSysUserRequest);

    TdSysUserPermissionVo getInfo(String token);

    void logout(String token);

    ResponsePageBean queryAllUserByPage(TdSysUserRequest userRequest);

    boolean refresgUserInfo(String accessToken);

    void setUserInfo(String accessToken);

    void saveUser(TdSysUserRequest sysUserRequest);

    void saveUser(TdSysUserVo tdSysUser);

    TdSysUser selectById( String userId);

    TdSysUser deleteById( String userId);

    void updateUser(TdSysUser user);

    TdSysUser queryUserByCode(String userCode);

    TdSysUserVo queryById(String userId);

    void edit(TdSysUserVo tdSysUserVo);
}
