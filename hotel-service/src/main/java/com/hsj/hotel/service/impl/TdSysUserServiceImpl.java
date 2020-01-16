package com.hsj.hotel.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hsj.hotel.common.constant.UserConstant;
import com.hsj.hotel.common.util.TimeUtil;
import com.hsj.hotel.common.vo.ResponsePageBean;
import com.hsj.hotel.common.exception.BaseException;
import com.hsj.hotel.common.handler.VoHandler;
import com.hsj.hotel.mapper.TdSysRoleMapper;
import com.hsj.hotel.response.TdSysUserResponse;
import com.hsj.hotel.service.interfaces.TdSysRoleService;
import com.hsj.hotel.service.interfaces.TdSysUserRoleService;
import com.hsj.hotel.service.util.SessionManager;
import com.hsj.hotel.mapper.TdSysUserMapper;
import com.hsj.hotel.model.TdSysUser;
import com.hsj.hotel.vo.TdSysUserListVo;
import com.hsj.hotel.vo.TdSysUserPermissionVo;
import com.hsj.hotel.service.interfaces.TdSysUserPermissionService;
import com.hsj.hotel.service.interfaces.TdSysUserService;
import com.hsj.hotel.request.TdSysUserRequest;
import com.hsj.hotel.vo.TdSysUserRoleVo;
import com.hsj.hotel.vo.TdSysUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class TdSysUserServiceImpl implements TdSysUserService {

    @Resource
    private TdSysUserMapper tdSysUserMapper;

    @Resource
    private TdSysUserPermissionService tdSysUserPermission;

    @Resource
    private TdSysUserRoleService tdSysUserRoleService;

    @Resource
    private TdSysRoleService tdSysRoleService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final String USER_TOKEN = "USER_TOKEN";

    private final String TOKEN_LIST = "TOKEN_LIST";

    @Value("${token.expire}")
    private long expireTime;

    @Override
    public String getUser(String userName,String password) {

        TdSysUser user = tdSysUserMapper.queryUserByName(userName);

        if (user==null){
            throw new BaseException(10007,"用户不存在");
        }
        if (!user.getUserPassword().equals(password)) {
            throw new BaseException(10008, "账号或密码错误");
        }
        String token = null;
        // 登陆时先判断redis中是否存在token
        // 从redis中获取token列表
        String tokenList = stringRedisTemplate.opsForValue().get(USER_TOKEN + ":" + TOKEN_LIST);
        Map<String,String>tokenMap = null;
        if (tokenList!=null){
             tokenMap  = JSON.parseObject(tokenList, Map.class);
            // 根据userid获取token
            token = (String) tokenMap.get(user.getUserId());
            // token 不为空，重新设置过期时间
            if (token !=null){

                String oldPermissions = stringRedisTemplate.opsForValue().get(USER_TOKEN+":"+token);
                if (oldPermissions==null){
                    tokenMap.remove(user.getUserId());
                }else{
                    stringRedisTemplate.expire(USER_TOKEN+":"+token , expireTime, TimeUnit.SECONDS);
                    return token;
                }
            }
        }
        // 生成token
        token = UUID.randomUUID().toString();
        //查询用户的权限
        TdSysUserPermissionVo userPermissions =  tdSysUserPermission.queryUserPermissionsById(user.getUserId());

        String permissionStr = JSON.toJSONString(userPermissions);

        stringRedisTemplate.opsForValue().set(USER_TOKEN+":"+token, permissionStr);
        stringRedisTemplate.expire(USER_TOKEN+":"+token,expireTime, TimeUnit.SECONDS);

        // 在redis中维护一个token列表，用来保存所有的token
        if (tokenMap==null){
            tokenMap = new HashMap<String,String>();
        }
         tokenMap.put(user.getUserId(),token);
        stringRedisTemplate.opsForValue().set(USER_TOKEN+":"+TOKEN_LIST,JSON.toJSONString(tokenMap));
        return token;
    }

    @Override
    public void register(TdSysUserRequest tdSysUserRequest) {

        TdSysUser tdSysUser = dealBeforeRegister(tdSysUserRequest);

        if (tdSysUserMapper.queryUserByCode(tdSysUserRequest.getUserCode())!=null){
            throw new BaseException(500,"用户已注册");
        }
        if (tdSysUserMapper.queryUserByName(tdSysUserRequest.getUserName())!=null){
            throw new BaseException(500,"用户名已存在");
        }
        tdSysUserMapper.register(tdSysUser);
    }

    // 查询用户信息
    @Override
    public TdSysUserPermissionVo getInfo(String token) {

        String userPermission = stringRedisTemplate.opsForValue().get(USER_TOKEN + ":" + token);
        if (userPermission == null){
            throw new BaseException(10010, "不存在用户登陆信息，请重新登陆");
        }
        return JSON.parseObject(userPermission,TdSysUserPermissionVo.class);
    }

    @Override
    public void logout(String token) {

        stringRedisTemplate.delete(USER_TOKEN + ":" + token);

    }


    @Override
    public ResponsePageBean queryAllUserByPage(TdSysUserRequest sysUserRequest) {

        PageHelper.startPage(sysUserRequest.getPage(),sysUserRequest.getRows());

        List<TdSysUser> tdSysUsers = tdSysUserMapper.queryAll(sysUserRequest);

        if (tdSysUsers == null||tdSysUsers.size()==0){
            PageInfo<Object> of = PageInfo.of(new ArrayList<>());
            return new ResponsePageBean(of.getTotal(),of.getList());
        }else{
            ResponsePageBean pageBean = new ResponsePageBean<>();
            VoHandler.deal(tdSysUsers);

            List<TdSysUserListVo> tdSysUserList = tdSysUsers.stream().map(tdSysUser -> {

                TdSysUserListVo tdSysUserListVo = new TdSysUserListVo();
                BeanUtils.copyProperties(tdSysUser, tdSysUserListVo);

                return tdSysUserListVo;
            }).collect(Collectors.toList());
            List<String> userIds = tdSysUsers.stream()
                                             .map(tdSysUser -> tdSysUser.getUserId())
                                             .collect(Collectors.toList());
            List<TdSysUserRoleVo> roleVos = tdSysUserRoleService.queryRoleVoByUserIds(userIds);

            if (roleVos!=null&&roleVos.size()>0){
                Map<String, List<TdSysUserRoleVo>> collect = roleVos.stream().collect(Collectors.groupingBy(roleVo -> roleVo.getUserId()));

                tdSysUserList.forEach(tdSysUserListVo -> {
                    tdSysUserListVo.setRoles(collect.get(tdSysUserListVo.getUserId()));
                });
            }
            return new ResponsePageBean(PageInfo.of(tdSysUsers).getTotal(),tdSysUserList);
        }
    }

    @Override
    public boolean refresgUserInfo(String accessToken) {

        String userInfo = stringRedisTemplate.opsForValue().get(USER_TOKEN + ":" + accessToken);

        stringRedisTemplate.expire(USER_TOKEN + "" + accessToken,expireTime,TimeUnit.SECONDS);

        return userInfo!=null;

    }

    @Override
    public void setUserInfo(String accessToken) {

        String userInfo = stringRedisTemplate.opsForValue().get(USER_TOKEN + "" + accessToken);

        SessionManager.serUserInfo(userInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUser(TdSysUserRequest sysUserRequest) {

        TdSysUser tdSysUser = new TdSysUser();

        BeanUtils.copyProperties(sysUserRequest,tdSysUser);

        tdSysUser.setUserId(UUID.randomUUID().toString().replace("-",""));
        tdSysUserMapper.saveUser(tdSysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUser(TdSysUserVo tdSysUserVo) {

        TdSysUser tdSysUser = new TdSysUser();
        BeanUtils.copyProperties(tdSysUserVo,tdSysUser);
        tdSysUser.builder().userId(UUID.randomUUID().toString().replace("-",""))
                            .userStatus(tdSysUserVo.getUserStatus()? UserConstant.STATUS.USERING.getCode():UserConstant.STATUS.STOP.getCode())
                            .createTime(new Date())
                           .createUser(SessionManager.getUser().getUserName())
                           .updateTime(new Date())
                           .updateUser(SessionManager.getUser().getUserName())
                           .build();
        tdSysUserMapper.saveUser(tdSysUser);
        List<String> roles = tdSysUserVo.getRoles();
        tdSysUserRoleService.saveUserRole(roles,tdSysUserVo.getUserId());
    }

    @Override
    public TdSysUser selectById(String userId) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TdSysUser deleteById(String userId) {

        tdSysUserMapper.deleteById(userId);

        tdSysUserRoleService.deleteByUserId(userId);

        return null;
    }

    @Override
    public void updateUser(TdSysUser user) {

    }

    @Override
    public TdSysUser queryUserByCode(String userCode) {
        return tdSysUserMapper.queryUserByCode(userCode);
    }

    @Override
    public TdSysUserVo queryById(String userId) {

        TdSysUserVo tdSysUserVo = new TdSysUserVo();
        TdSysUser tdSysUser = tdSysUserMapper.queryUserById(userId);

        List<String> roles =  tdSysUserRoleService.queryRoleByUserId(userId);

        BeanUtils.copyProperties(tdSysUser,tdSysUserVo);
        tdSysUserVo.setRoles(roles);
        return tdSysUserVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(TdSysUserVo tdSysUserVo) {

        TdSysUser tdSysUser = tdSysUserMapper.queryUserById(tdSysUserVo.getUserId());

        if (tdSysUser==null){
            throw new BaseException(500,"该用户不存在无法修改");
        }
    }


    public TdSysUser dealBeforeRegister(TdSysUserRequest tdSysUserRequest){

        return TdSysUser.builder()
                .userId(UUID.randomUUID().toString().replace("-",""))
                .userName(tdSysUserRequest.getUserName())
                .userCode(tdSysUserRequest.getUserCode())
                .userPassword(tdSysUserRequest.getUserPassword())
                .createTime(new Date())
                .userSex(tdSysUserRequest.getUserSex()).build();

    }
}
