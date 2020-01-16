package com.hsj.hotel.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class TdSysMenu {

    @Tolerate
    public TdSysMenu() {
    }

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型
     */
    private String menuType;
    /**
     * 菜单代码
     */
    private String menuCode;

    /**
     * 菜单等级
     */
    private Integer menuLevel;
    /**
     * 菜单状态
     */
    private String menuStatus;

    /**
     * 菜单组件
     */
    private String menuComponent;

    /**
     * 父级菜单id
     */
    private String parentMenuId;

    /**
     * 菜单url
     */
    private String menuUrl;


    /**
     * 菜单排序
     */
    private String menuOrder;


    /**
     * 是否存在子菜单
     */
    private String existSub;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
