package com.example.administrator.paysdk.javabean;

/**
 * Created by Administrator on 2017/8/27/027.
 * 附近加油站实体类对象，用来存储获取回来的数据
 */

public class Gatstation {
    private String name;
    private String areaname;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "GastStation{" +
                "name='" + name + '\'' +
                ", areaName='" + areaname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
