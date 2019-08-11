package com.dev.r1.ParkingLotDrivers;
/*
InteractiveDriver class to handle inputs from system input
1) if input command is valid,it writes result on console
2) if not it writes error message on console
 */
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
