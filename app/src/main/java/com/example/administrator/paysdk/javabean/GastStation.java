package com.example.administrator.paysdk.javabean;

/**
 * Created by Administrator on 2017/5/26.
 */

public class GastStation {
    private String name;
    private String areaName;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
                ", areaName='" + areaName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
