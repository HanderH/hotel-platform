package com.hsj.hotel.common.handler;

import com.github.pagehelper.Page;
import com.hsj.hotel.common.annotation.VoField;
import com.hsj.hotel.common.annotation.VoObject;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Slf4j
public class VoHandler {

    /**
     * 处理含有 {@link VoField} 的对象
     *
     * @param obj 对象
     */
    public static void deal(Object obj) {
        if (obj != null) {
            if (obj instanceof Page) {
                dealPage((Page) obj);
                return;
            }
            if (obj instanceof List) {
                deal((List) obj);
            }
            Class<?> cls = obj.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    //判断是否存在Vo注解
                    if (field.isAnnotationPresent(VoField.class)) {
                        //获取注解
                        VoField voFieldAnno = field.getAnnotation(VoField.class);
                        Class enumCls = voFieldAnno.enumClass();
                        //处理对应枚举名称
                        if (value != null && enumCls != Object.class) {
                            Object[] enumConstants = enumCls.getEnumConstants();
                            Method getName = enumCls.getDeclaredMethod("getName");
                            Method getCode = enumCls.getDeclaredMethod("getCode");
                            for (Object enumConstant : enumConstants) {
                                if (getCode.invoke(enumConstant).equals(value)) {
                                    field.set(obj, getName.invoke(enumConstant));
                                    log.info("转换 {}->{}", value, getName.invoke(enumConstant));
                                    break;
                                }
                            }
                        }
                        //判断是否存在VoObject注解
                    } else if (field.isAnnotationPresent(VoObject.class)) {
                        if (value instanceof List) {
                            deal((List<Object>) value);
                        } else {
                            deal(value);
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            log.info("转换完成...");


        }

    }

    /**
     * 处理含有 {@link VoField} 的对象
     *
     * @param objs
     */
    public static void deal(List<Object> objs) {
        if (objs != null && objs.size() > 0) {
            for (Object obj : objs) {
                deal(obj);
            }
        }
    }

    /**
     * 处理分页对象
     *
     * @param page
     */
    private static void dealPage(Page page) {
        for (int i = 0; i < page.size(); i++) {
            deal(page.get(i));
        }
    }


}