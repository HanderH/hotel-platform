package com.hsj.hotel.service.impl;

import com.hsj.hotel.mapper.TdSysRoleMapper;
import com.hsj.hotel.mapper.TdSysUserRoleMapper;
import com.hsj.hotel.model.TdSysRole;
import com.hsj.hotel.model.TdSysUserRole;
import com.hsj.hotel.service.interfaces.TdSysRoleService;
import com.hsj.hotel.service.interfaces.TdSysUserRoleService;
import com.hsj.hotel.service.util.SessionManager;
import com.hsj.hotel.vo.TdSysUserRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TdSysUserRoleServiceImpl implements TdSysUserRoleService {


    @Resource
    private TdSysUserRoleMapper tdSysUserRoleMapper;

    @Resource
    private TdSysRoleService tdSysRoleService;

    @Resource
    private TdSysRoleMapper tdSysRoleMapper;
    @Override
    public List<TdSysUserRoleVo> queryRoleVoByUserIds(List<String> userIds) {

        List<TdSysUserRole> tdSysUserRoles = tdSysUserRoleMapper.queryRoleVoByUserIds(userIds);
        if (tdSysUserRoles!=null&&tdSysUserRoles.size()>0){

            List<TdSysUserRoleVo> collect = tdSysUserRoles.stream().map(tdSysUserRole -> {

                TdSysUserRoleVo tdSysUserRoleVo = new TdSysUserRoleVo();
                BeanUtils.copyProperties(tdSysUserRole, tdSysUserRoleVo);

                return tdSysUserRoleVo;
            }).collect(Collectors.toList());
            return collect;
        }
        return new ArrayList<>();
    }

    @Override
    public void saveUserRole(List<String> roles, String userId) {

        if (roles!=null&&roles.size()>0){
            // 根据role查询出角色id
           List<String> roleIds =  tdSysRoleMapper.queryRoleIds(roles);

            List<TdSysUserRole> tdSysUserRoleList = roleIds.stream().map(roleId -> {

                return TdSysUserRole.builder()
                        .userId(userId)
                        .roleId(roleId)
                        .dataId(UUID.randomUUID().toString().replace("-", ""))
                        .dataStatus("U")
                        .createTime(new Date())
                        .createUser(SessionManager.getUser().getUserName()).build();

            }).collect(Collectors.toList());
            //想td_sys_user_role插入数据，维户表关系
            tdSysUserRoleMapper.saveList(tdSysUserRoleList);
        }
    }

    @Override
    public void deleteByUserId(String userId) {

        tdSysUserRoleMapper.deleteByUserId(userId);
    }

    @Override
    public List<String> queryRoleByUserId(String userId) {

       List<String> role = tdSysUserRoleMapper.queryRoleByUserId(userId);
        return role;
    }
}
