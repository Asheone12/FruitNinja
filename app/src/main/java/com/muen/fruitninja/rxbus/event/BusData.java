package com.muen.fruitninja.rxbus.event;


public class BusData {

    private String type;

    private Object data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public <T extends Object> T getData() {
        return (T) data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BusData() {
    }

    public BusData(String type) {
        this.type = type;
    }

    public BusData(String type, Object data) {
        this.type = type;
        this.data = data;
    }
}
