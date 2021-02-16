package com.example.classlifecycle;

public class Player {

    String name;
    String code;
    String type;
    private boolean isExpand;

    public Player(String name, String code, String type) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.isExpand = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }
}
