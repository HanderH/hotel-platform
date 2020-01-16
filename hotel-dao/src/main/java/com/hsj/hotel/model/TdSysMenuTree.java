package com.hsj.hotel.model;

import lombok.experimental.Tolerate;

import java.util.List;

public class TdSysMenuTree {

    @Tolerate
    public TdSysMenuTree() {
    }

    /**
     * 节点唯一标志
     */
    private String key;

    /**
     * 节点名称
     */
    private String title;

    /**
     * 节点类型
     */
    private String type;

    /**
     * 子节点
     */
    private List<TdSysMenuTree> children;

}
