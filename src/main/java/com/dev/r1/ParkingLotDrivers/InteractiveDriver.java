package com.dev.r1.ParkingLotDrivers;

public class InteractiveDriver extends ExecutionDriver {
    @Override
    public String execute(String input_command) {
        try {
            String parkingLot_status = super.parkinglot_events(input_command);
            if(parkingLot_status!=null) {
                System.out.println(parkingLot_status);
            }else{
                System.out.println("Result not found");
                return "Result not found";
            }
            return parkingLot_status;

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
