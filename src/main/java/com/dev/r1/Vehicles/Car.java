package com.dev.r1.Vehicles;

public class Car extends Vehicle {
    String car_color;
    String car_regnum;
    public Car(String car_regnum,String car_color){
        this.car_color=car_color;
        this.car_regnum=car_regnum;
    }
    public String getColor() {
        return car_color;
    }
    public String getReg_num() {
        return car_regnum;
    }
}
