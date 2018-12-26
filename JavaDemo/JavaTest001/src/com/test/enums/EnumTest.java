package com.test.enums;

public enum  EnumTest {
    FIRST{
        @Override
        public String getInfo() {
            return "FIRST ENUM";
        }
    },

    SCEOND{
        @Override
        public String getInfo() {
            return "SCEOND ENUM";
        }
    };


    /**
     * 定义抽象方法
     * @return
     */
    public abstract String getInfo();

}
