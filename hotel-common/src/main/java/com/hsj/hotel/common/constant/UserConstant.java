package com.hsj.hotel.common.constant;

import javax.jws.soap.SOAPBinding;

public enum  UserConstant {
        ;

    public enum GENDER{
        WOMEN("0","女"),
        MEN("1","男");

        private String code;
        private String name;

        GENDER(String code,String name){

            this.code = code;
            this.name = name;
        }

        public String getCode(){
            return code;
        }

        public String getName(){
            return name;
        }

        public String getNameByCode(String code){

            for (UserConstant.GENDER gender:UserConstant.GENDER.values()){
                if (gender.getCode().equals(code)){
                    return gender.getName();
                }
            }
            return null;
        }
    }

    public enum STATUS{
        STOP("O","停用"),
        USERING("U","启用");

        private String code;
        private String name;

        STATUS(String code,String name){

            this.code = code;
            this.name = name;
        }

        public String getCode(){
            return code;
        }

        public String getName(){
            return name;
        }

        public String getNameByCode(String code){

            for (UserConstant.STATUS status:UserConstant.STATUS.values()){
                if (status.getCode().equals(code)){
                    return status.getName();
                }
            }
            return null;
        }
    }

}
