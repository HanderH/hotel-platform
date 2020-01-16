package com.hsj.hotel.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VoField {
    /**
     * 枚举类
     */
    public Class enumClass() default Object.class;

    /**
     * 类型
     *
     * @return
     */
    public String codeType() default "";

    /**
     * 类型的前缀
     * @return
     */
    public String prefix() default "DICT:";

    /**
     * value属性的名称
     *
     * @return
     */
    public String fieldValue() default "codeValue";

    /**
     * name属性的名称
     *
     * @return
     */
    public String fieldName() default "codeName";
}
