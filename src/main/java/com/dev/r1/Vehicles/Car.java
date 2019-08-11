package com.dev.r1.Vehicles;

public class Car extends Vehicle {
    private String color;
    private String regNum;

    public Car(String regNum, String color) {
        this.color = color;
        this.regNum = regNum;
    }

    public String getColor() {
        return color;
    }

    public String getRegNum() {
        return regNum;
    }
}
