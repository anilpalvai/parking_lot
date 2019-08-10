package com.dev.r1.ParkingLotDrivers;

import com.dev.r1.Vehicles.Car;
import java.util.*;
import java.util.stream.Collectors;

public class Parking_Allocator {
    HashMap<Integer, Car> ParkingMap = new HashMap<>();
    Queue<Integer> Slot_allocator =new LinkedList<>();
    HashMap<String, List<Integer>> Color_SlotMap = new HashMap<>();
    HashMap<String, List<String>> Color_RegMap = new HashMap<>();
    HashMap<String, Integer> RegistationMap = new HashMap<>();
    List<Integer> slist;
    List<String> reglist;
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

    //Creating a car and allocating the slot
    public  String park(Car car) throws NullPointerException{
        //checking the parkingSlot is created or not before parking
        if(no_ofSlots==0){
            System.out.println("Please create parking slots");
            return null;
        }
        String Parking_Car_Color=car.getColor();
        String Parking_Car_RegNum=car.getReg_num();

        try {
            if (slot < no_ofSlots) {
                String Parking_status;
                slot = slot + 1;
                int allocated_slot = slot;
                if (Slot_allocator.size() == 0) {
                    ParkingMap.put(allocated_slot, car);
                    Parking_status = "Allocated slot number: " + allocated_slot;
                } else {

                    allocated_slot = Slot_allocator.remove();
                    ParkingMap.put(allocated_slot, car);
                    Parking_status = "Allocated slot number: " + allocated_slot;
                }
                if (!Color_SlotMap.keySet().contains(Parking_Car_Color)) {
                    slist = new ArrayList<>();
                    slist.add(allocated_slot);
                    Color_SlotMap.put(Parking_Car_Color, slist);
                } else {
                    slist = Color_SlotMap.get(Parking_Car_Color);
                    slist.add(allocated_slot);
                    Color_SlotMap.put(Parking_Car_Color, slist);
                }


                if (!Color_RegMap.keySet().contains(Parking_Car_Color)) {
                    reglist = new ArrayList<>();
                    reglist.add(Parking_Car_RegNum);
                    Color_RegMap.put(Parking_Car_Color, reglist);

                } else {
                    reglist = Color_RegMap.get(Parking_Car_Color);
                    reglist.add(Parking_Car_RegNum);
                    Color_RegMap.put(Parking_Car_Color, reglist);
                }

                RegistationMap.put(Parking_Car_RegNum, allocated_slot);

                return Parking_status;
            } else {
                return "Sorry, parking lot is full";
            }

        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    //Leave functionality to make available slot for other cars after left
    public  String leave(String slotnum) throws Exception{
        try {
            int leavingSlot=Integer.parseInt(slotnum);
            if(ParkingMap.keySet().contains(leavingSlot)) {
                Car car = ParkingMap.get(leavingSlot);
                String leaving_car_reg_num = car.getReg_num();
                String leaving_car_color = car.getColor();

                List<Integer> adjustSlots = Color_SlotMap.get(leaving_car_color);
                adjustSlots.remove(new Integer(leavingSlot));
                Color_SlotMap.put(leaving_car_color, adjustSlots);

                List<String> adjustRegNums = Color_RegMap.get(leaving_car_color);
                adjustRegNums.remove(leaving_car_reg_num);
                Color_RegMap.put(leaving_car_color, adjustRegNums);

                ParkingMap.remove(leavingSlot);
                Slot_allocator.add(leavingSlot);
                slot--;
                return "Slot number " + slotnum + " is free";
            }else{
                System.out.println("Slot is not yet parked or available");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    //display of parking status
    public  String status(){

        String[] status_str = new String[ParkingMap.size()+1];
        status_str[0] = "Slot No.  Registration No     Colour\n";
        int i=1;
        for (Map.Entry<Integer, Car> integerCarEntry : ParkingMap.entrySet()) {

            status_str[i]= integerCarEntry.getKey()
                    +"         "+
                    integerCarEntry.getValue().getReg_num()+
                    "                 "+
                    integerCarEntry.getValue().getColor()+"\n";

            i++;

        }
        String str = Arrays.toString(status_str);
        return str.substring(0,str.length()-1);
    }

    //function to get the registation numbers by color
    public  String getregnumsByColor(String color){
        if(Color_RegMap.keySet().contains(color)) {
            List<String> regByColor = Color_RegMap.get(color);
            return String.join(", ", regByColor);
        }else{
            return null;
        }

    }

    //function to get the slots occupied  by color
    public  String getSlotnumsByColor(String color){
        if(Color_SlotMap.keySet().contains(color)) {
            List<Integer> slotsByColor = Color_SlotMap.get(color);
            String slotsByColorStatus = slotsByColor.stream().map(String::valueOf).collect(Collectors.joining(","));
            return slotsByColorStatus;
        }
        else{
            return null;
        }

    }
    //function to get the slots occupied  by particular registered car
    public  String getSlotByRegnum(String regnum){
        int slotsByRegnum;
        try {
            if(RegistationMap.keySet().contains(regnum)) {
                slotsByRegnum = RegistationMap.get(regnum);
                return Integer.toString(slotsByRegnum);
            }else{
                return "Not found";
            }
        }catch (NullPointerException ne){
            return "Not found";
        }

    }


}
