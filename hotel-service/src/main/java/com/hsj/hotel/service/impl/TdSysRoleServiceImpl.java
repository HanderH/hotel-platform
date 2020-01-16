package com.hsj.hotel.service.impl;

import com.hsj.hotel.mapper.TdSysRoleMapper;
import com.hsj.hotel.model.TdSysRole;
import com.hsj.hotel.service.interfaces.TdSysRoleService;
import com.hsj.hotel.service.interfaces.TdSysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TdSysRoleServiceImpl implements TdSysRoleService {

    @Autowired
    private TdSysRoleMapper tdSysRoleMapper;

    @Override
    public List<TdSysRole> queryAll() {

        return tdSysRoleMapper.queryAll();
    }
}
