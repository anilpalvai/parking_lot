package com.dev.r1.ParkingLotDrivers;

import com.dev.r1.Vehicles.Car;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingAllocator {
    //creating the ParkingLot as static to maintain the state
    private static ParkingAllocator parkAllocator;
    private static int numOfSlots;
    private HashMap<Integer, Car> parkingMap = new HashMap<>();
    private Queue<Integer> slotAllocator = new LinkedList<>();
    private HashMap<String, List<Integer>> colorBySlotMap = new HashMap<>();
    private HashMap<String, List<String>> colorByRegMap = new HashMap<>();
    private HashMap<String, Integer> registrationMap = new HashMap<>();
    private List<Integer> slist;
    private List<String> reglist;
    private int slot;

    public ParkingAllocator(int slotlimit) {

        parkingMap = new HashMap<>();
        slotAllocator = new LinkedList<>();
        colorBySlotMap = new HashMap<>();
        colorByRegMap = new HashMap<>();
        registrationMap = new HashMap<>();
        List<Integer> slist;
        List<String> reglist;
        slot = 0;
        this.numOfSlots = slotlimit;

    }

    //Creating Parking slots
    public static ParkingAllocator getOrCreate(String limit) {
        if (parkAllocator == null) {
            parkAllocator = new ParkingAllocator(Integer.parseInt(limit));
        } else {
            System.out.println("Parking lot already created with " + numOfSlots + " slots");
        }
        return parkAllocator;

    }

    //function to return status of number of slots in string formar
    // to driver functions(interactive and file).
    public String getNumOfSlots() {
        return "Created a parking lot with " + Integer.toString(numOfSlots) + " slots";
    }

    //Creating a car and allocating the slot
    public String park(Car car) {
        //checking the parkingSlot is created or not before parking
        if (numOfSlots == 0) {
            System.out.println("Please create parking slots");
            return null;
        }

        String parkingCarColor = car.getColor();
        String parkingCarRegNum = car.getRegNum();

        try {
            if (slot < numOfSlots) {
                String parkingStatus;
                slot = slot + 1;
                int allocatedSlot = slot;
                if (slotAllocator.size() == 0) {
                    parkingMap.put(allocatedSlot, car);
                    parkingStatus = "Allocated slot number: " + allocatedSlot;
                } else {

                    allocatedSlot = slotAllocator.remove();
                    parkingMap.put(allocatedSlot, car);
                    parkingStatus = "Allocated slot number: " + allocatedSlot;
                }
                if (!colorBySlotMap.keySet().contains(parkingCarColor)) {
                    slist = new ArrayList<>();
                    slist.add(allocatedSlot);
                    colorBySlotMap.put(parkingCarColor, slist);
                } else {
                    slist = colorBySlotMap.get(parkingCarColor);
                    slist.add(allocatedSlot);
                    colorBySlotMap.put(parkingCarColor, slist);
                }


                if (!colorByRegMap.keySet().contains(parkingCarColor)) {
                    reglist = new ArrayList<>();
                    reglist.add(parkingCarRegNum);
                    colorByRegMap.put(parkingCarColor, reglist);

                } else {
                    reglist = colorByRegMap.get(parkingCarColor);
                    reglist.add(parkingCarRegNum);
                    colorByRegMap.put(parkingCarColor, reglist);
                }

                registrationMap.put(parkingCarRegNum, allocatedSlot);

                return parkingStatus;
            } else {
                return "Sorry, parking lot is full";
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    //Leave functionality to make available slot for other cars after left
    public String leave(String slotNum) throws Exception {
        try {
            int leavingSlot = Integer.parseInt(slotNum);
            if (parkingMap.keySet().contains(leavingSlot)) {
                Car car = parkingMap.get(leavingSlot);
                String leavingCarRegNum = car.getRegNum();
                String leavingCarColor = car.getColor();

                List<Integer> adjustSlots = colorBySlotMap.get(leavingCarColor);
                adjustSlots.remove(new Integer(leavingSlot));
                colorBySlotMap.put(leavingCarColor, adjustSlots);

                List<String> adjustRegNums = colorByRegMap.get(leavingCarColor);
                adjustRegNums.remove(leavingCarRegNum);
                colorByRegMap.put(leavingCarColor, adjustRegNums);

                parkingMap.remove(leavingSlot);
                slotAllocator.add(leavingSlot);
                slot--;
                return "Slot number " + slotNum + " is free";
            } else {
                System.out.println("Slot is not yet parked or available");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    //display of parking status
    public String status() {

        String[] status_str = new String[parkingMap.size() + 1];
        status_str[0] = "Slot No.  Registration No     Colour\n";
        int i = 1;
        for (Map.Entry<Integer, Car> integerCarEntry : parkingMap.entrySet()) {

            status_str[i] = integerCarEntry.getKey()
                    + "         " +
                    integerCarEntry.getValue().getRegNum() +
                    "                 " +
                    integerCarEntry.getValue().getColor() + "\n";

            i++;

        }
        String str = Arrays.toString(status_str);
        return str.substring(0, str.length() - 1);
    }

    //function to get the registation numbers by color
    public String getregnumsByColor(String color) {
        if (colorByRegMap.keySet().contains(color)) {
            List<String> regByColor = colorByRegMap.get(color);
            return String.join(", ", regByColor);
        } else {
            return null;
        }

    }

    //function to get the slots occupied  by color
    public String getSlotnumsByColor(String color) {
        if (colorBySlotMap.keySet().contains(color)) {
            List<Integer> slotsByColor = colorBySlotMap.get(color);
            String slotsByColorStatus = slotsByColor.stream().map(String::valueOf).collect(Collectors.joining(","));
            return slotsByColorStatus;
        } else {
            return null;
        }

    }

    //function to get the slots occupied  by particular registered car
    public String getSlotByRegNum(String regNum) {
        int slotsByRegNum;
        try {
            if (registrationMap.keySet().contains(regNum)) {
                slotsByRegNum = registrationMap.get(regNum);
                return Integer.toString(slotsByRegNum);
            } else {
                return "Not found";
            }
        } catch (NullPointerException ne) {
            return "Not found";
        }

    }


}
