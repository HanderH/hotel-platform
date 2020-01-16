package com.hsj.hotel.mapper;

import com.hsj.hotel.request.TdSysUserRequest;
import com.hsj.hotel.model.TdSysUser;
import com.hsj.hotel.vo.TdSysUserPermissionVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TdSysUserMapper {

    /**
     * 通过用户名查询账号
     * @param userName 用户名
     * @return
     */
    @Select("<script>\n" +
            "        select\n" +
            "        t1.USER_ID          as userId,\n" +
            "        t1.USER_TYPE        as userType,\n" +
            "        t1.USER_CODE        as userCode,\n" +
            "        t1.USER_NAME        as userName,\n" +
            "        t1.USER_TEL         as userTel,\n" +
            "        t1.USER_PASSWORD    as userPassword,\n" +
            "        t1.USER_SEX         as userSex,\n" +
            "        t1.USER_STATUS      as userStatus,\n" +
            "        t1.CREATE_TIME      as createTime,\n" +
            "        t1.CREATE_USER      as createUser,\n" +
            "        t1.UPDATE_TIME      as updateTime,\n" +
            "        t1.UPDATE_USER      as updateUser \n" +
            "        from TD_SYS_USER t1\n" +
            "        where USER_NAME = #{userName}" +
            "</script>")
    public TdSysUser queryUserByName(@Param("userName") String userName);

    @Insert("<script>\n" +
            "insert into TD_SYS_USER\n" +
            "            (\n" +
            "             USER_ID,\n" +
            "             USER_TYPE,\n" +
            "             USER_CODE,\n" +
            "             USER_NAME, \n" +
            "             USER_TEL, \n" +
            "             USER_PASSWORD, \n" +
            "             USER_SEX, \n" +
            "             USER_STATUS, \n" +
            "             CREATE_TIME, \n" +
            "             CREATE_USER, \n" +
            "             UPDATE_TIME,\n" +
            "             UPDATE_USER \n" +
            "             )\n" +
            "        VALUES(\n" +
            "               #{user.userId,jdbcType=VARCHAR},\n" +
            "               #{user.userType,jdbcType=VARCHAR},\n" +
            "               #{user.userCode,jdbcType=VARCHAR},\n" +
            "               #{user.userName,jdbcType=VARCHAR},\n" +
            "               #{user.userTel,jdbcType=VARCHAR},\n" +
            "               #{user.userPassword,jdbcType=VARCHAR},\n" +
            "               #{user.userSex,jdbcType=VARCHAR},\n" +
            "               #{user.userStatus,jdbcType=VARCHAR},\n" +
            "               #{user.createTime,jdbcType=DATE},\n" +
            "               #{user.createUser,jdbcType=VARCHAR},\n" +
            "               #{user.updateTime,jdbcType=DATE},\n" +
            "               #{user.updateUser,jdbcType=VARCHAR}\n" +
            "              )" +
            "</script>")
    void register(@Param("user") TdSysUser user);

    @Select("<script>\n" +
            "        select\n" +
            "        t1.USER_ID          as userId,\n" +
            "        t1.USER_TYPE        as userType,\n" +
            "        t1.USER_CODE        as userCode,\n" +
            "        t1.USER_NAME        as userName,\n" +
            "        t1.USER_TEL         as userTel,\n" +
            "        t1.USER_PASSWORD    as userPassword,\n" +
            "        t1.USER_SEX         as userSex,\n" +
            "        t1.USER_STATUS      as userStatus,\n" +
            "        t1.CREATE_TIME      as createTime,\n" +
            "        t1.CREATE_USER      as createUser,\n" +
            "        t1.UPDATE_TIME      as updateTime,\n" +
            "        t1.UPDATE_USER      as updateUser \n" +
            "        from TD_SYS_USER t1\n" +
            "        where USER_CODE = #{userCode}" +
            "</script>")
    TdSysUser queryUserByCode(@Param("userCode") String userCode);

//    TdSysUserPermissionVo queryUserPermissionsById(String userId);

    @Select("<script>\n" +
            "        select\n" +
            "        t1.USER_ID          as userId,\n" +
            "        t1.USER_TYPE        as userType,\n" +
            "        t1.USER_CODE        as userCode,\n" +
            "        t1.USER_NAME        as userName,\n" +
            "        t1.USER_TEL         as userTel,\n" +
            "        t1.USER_PASSWORD    as userPassword,\n" +
            "        t1.USER_SEX         as userSex,\n" +
            "        t1.USER_STATUS      as userStatus,\n" +
            "        t1.CREATE_TIME      as createTime,\n" +
            "        t1.CREATE_USER      as createUser,\n" +
            "        t1.UPDATE_TIME      as updateTime,\n" +
            "        t1.UPDATE_USER      as updateUser \n" +
            "        from TD_SYS_USER t1\n" +
            "        where USER_ID= #{userId}" +
            "</script>")
    TdSysUser queryUserById(@Param("userId") String userId);


    List<TdSysUser> queryAll(@Param("user") TdSysUserRequest tdSysUserRequest);

    void saveUser(@Param("user") TdSysUser tdSysUser);

    TdSysUser selectById(@Param("userId") String userId);

    TdSysUser deleteById(@Param("userId") String userId);

    void updateUser(@Param("user") TdSysUser user);
}
