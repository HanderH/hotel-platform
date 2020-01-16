package com.hsj.hotel.controller.user;

import com.hsj.hotel.common.vo.ResponseBean;
import com.hsj.hotel.model.TdSysRole;
import com.hsj.hotel.service.interfaces.TdSysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private TdSysRoleService tdSysRoleService;

    @GetMapping("/queryAll")
    public ResponseBean queryAll(){

      List<TdSysRole> list = tdSysRoleService.queryAll();

        ResponseBean responseBean = new ResponseBean();

        responseBean.addSuccess(list);
        return responseBean;
    }

}
