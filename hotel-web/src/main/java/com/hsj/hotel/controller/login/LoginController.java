package com.hsj.hotel.controller.login;

import com.hsj.hotel.common.vo.ResponseBean;
import com.hsj.hotel.vo.TdSysUserPermissionVo;
import com.hsj.hotel.request.TdSysUserRequest;
import com.hsj.hotel.service.interfaces.TdSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private TdSysUserService tdSysUserService;

    @PostMapping("/login")
    public ResponseBean login(@RequestParam(value = "userName",required = true) String userName, @RequestParam(value = "userPassword",required = true) String userPassword){

        String token = tdSysUserService.getUser(userName, userPassword);

        ResponseBean responseBean = new ResponseBean();

        responseBean.addSuccess(token);

        return  responseBean;
    }

    @PostMapping("/register")
    public ResponseBean register(@RequestBody TdSysUserRequest tdSysUserRequest){

        tdSysUserService.register(tdSysUserRequest);
        return new ResponseBean();
    }

    @PostMapping("/user/info")
    public ResponseBean getInfo(@RequestParam("token") String token){
       TdSysUserPermissionVo tdSysUserPermissionVo =  tdSysUserService.getInfo(token);

        ResponseBean responseBean = new ResponseBean();

        responseBean.addSuccess(tdSysUserPermissionVo);
        return responseBean;
    }

    @PostMapping("/logout")
    public ResponseBean logout(@RequestParam("token") String token){

        tdSysUserService.logout(token);
        return new ResponseBean();
    }

}
