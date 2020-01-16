package com.hsj.hotel.intecepter;

import com.hsj.hotel.service.interfaces.TdSysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInteceptor implements HandlerInterceptor {

    @Autowired
    private TdSysUserService tdSysUserService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long start = System.currentTimeMillis();
        String accessToken = request.getHeader("Access-Token");

        if (accessToken!=null&&  tdSysUserService.refresgUserInfo(accessToken)){

            tdSysUserService.setUserInfo(accessToken);

            long end = System.currentTimeMillis();

            log.info("拦截器耗时:{}",end-start);
            return true;

        }else{
            accessToken = request.getParameter("token");

            if (accessToken!=null&&  tdSysUserService.refresgUserInfo(accessToken)){
                tdSysUserService.setUserInfo(accessToken);
                long end = System.currentTimeMillis();

                log.info("拦截器耗时:{}",end-start);
                return true;
            }else{
                return false;
            }
        }
    }
}
