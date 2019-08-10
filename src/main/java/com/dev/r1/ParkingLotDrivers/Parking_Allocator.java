package com.dev.r1.ParkingLotDrivers;

import com.dev.r1.Vehicles.Car;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Parking_Allocator {
    HashMap<Integer, Car> ParkingMap = new HashMap<>();
    Queue<Integer> Slot_allocator =new LinkedList<>();
    HashMap<String, List<Integer>> Color_SlotMap = new HashMap<>();
    HashMap<String, List<String>> Color_RegMap = new HashMap<>();
    HashMap<String, Integer> RegistationMap = new HashMap<>();
    List<Integer> slist;
    List<String> reglist;
    String car_reg_number;
    String car_color;
    static int no_ofSlots;
    int slot;

    public Parking_Allocator(int slotlimit) {

        ParkingMap = new HashMap<>();
        Slot_allocator =new LinkedList<>();
        Color_SlotMap = new HashMap<>();
        Color_RegMap = new HashMap<>();
        RegistationMap = new HashMap<>();
        List<Integer> slist;
        List<String> reglist;
        slot=0;
        this.no_ofSlots=slotlimit;

    }

    //function to return status of number of slots in string formar
    // to driver functions(interactive and file).
    public String get_noOfSots(){
        return "Created a parking lot with "+Integer.toString(no_ofSlots)+" slots";
    }

    //creating the ParkingLot as static to maintain the state
    public static Parking_Allocator park_allocator;

    //Creating Parking slots
    public  static Parking_Allocator createParkingSlot(String limit){
        if(park_allocator ==null){
            park_allocator =new Parking_Allocator(Integer.parseInt(limit));
            return park_allocator;

        }else{
            System.out.println("Parking lot already created with "+no_ofSlots+" slots");
            return park_allocator;
        }

    }

    public static String park(Car car) {
        return null;
    }

    public static String leave(String s) {
        return null;
    }

    public static String status() { return null;}


    public static String getregnumsByColor(String s) {
        return null;
    }

    public static String getSlotnumsByColor(String s) {
        return null;
    }

    public static String getSlotByRegnum(String s) {
        return null;
    }


}
