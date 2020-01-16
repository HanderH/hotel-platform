package com.hsj.hotel.controller.user;

import com.hsj.hotel.model.TdSysUser;
import com.hsj.hotel.request.TdSysUserRequest;
import com.hsj.hotel.common.vo.ResponseBean;
import com.hsj.hotel.common.vo.ResponsePageBean;
import com.hsj.hotel.service.interfaces.TdSysUserService;
import com.hsj.hotel.vo.TdSysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private TdSysUserService tdSysUserService;

    @GetMapping("/queryAllUserByPage")
    public ResponseBean queryAllUserByPage(TdSysUserRequest sysUserRequest){


      ResponsePageBean responsePageBean =  tdSysUserService.queryAllUserByPage(sysUserRequest);

      ResponseBean responseBean = new ResponseBean();

      responseBean.addSuccess(responsePageBean);
      return responseBean;
    }

    @PostMapping("/user/add")
    public ResponseBean saveUser(@RequestBody TdSysUserVo tdSysUserVo){

        tdSysUserService.saveUser(tdSysUserVo);

        return new ResponseBean();
    }
    @GetMapping("/hasUserCodeRepeat")
    public ResponseBean hasUserCodeRepeat(@RequestParam("userCode") String userCode){

       TdSysUser tdSysUser =  tdSysUserService.queryUserByCode(userCode);
        ResponseBean responseBean = new ResponseBean();
       if(tdSysUser!=null){
           responseBean.addSuccess(1);
       }else{
           responseBean.addSuccess(0);
       }
        return responseBean;
    }

    @GetMapping("/queryById")
    public ResponseBean queryById(@RequestParam("userId") String userId)  {

        TdSysUserVo tdSysUserVo = tdSysUserService.queryById(userId);

        ResponseBean responseBean = new ResponseBean();
        responseBean.addSuccess(tdSysUserVo);
        return responseBean;
    }

    @PostMapping("/delete")
    public ResponseBean delete(@RequestParam("userId") String userId){

        tdSysUserService.deleteById(userId);

        return new ResponseBean();
    }

    @PostMapping("/user/edit")
    public ResponseBean edit(@RequestBody TdSysUserVo tdSysUserVo){

        tdSysUserService.edit(tdSysUserVo);
        return new ResponseBean();
    }

}
