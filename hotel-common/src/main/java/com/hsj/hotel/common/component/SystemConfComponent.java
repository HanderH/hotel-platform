package com.hsj.hotel.common.component;


import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Lazy
@Component
public class SystemConfComponent {
    private final static Logger logger = LoggerFactory.getLogger(SystemConfComponent.class);

    private static Map<String,Map<String, SystemConf>> SystemConfRootMap=new HashMap<>();


    public SystemConf getSystemConfByLbbal(String key,String field){
        Map<String,SystemConf> systemConfMap= SystemConfComponent.SystemConfRootMap.get(key);
        if(MapUtils.isNotEmpty(systemConfMap)){
            return systemConfMap.get(field);
        }
        return null;
    }



}
