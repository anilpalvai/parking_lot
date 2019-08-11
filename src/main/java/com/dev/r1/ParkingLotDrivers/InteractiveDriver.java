package com.dev.r1.ParkingLotDrivers;
/*
InteractiveDriver class to handle inputs from system input
1) if input command is valid,it writes result on console
2) if not it writes error message on console
 */
public class InteractiveDriver extends ExecutionDriver {
    @Override
    public String execute(String inputCommand) {
        try {
            String parkingLotStatus = super.parkinglotEvents(inputCommand);
            if(parkingLotStatus!=null) {
                System.out.println(parkingLotStatus);
            }else{
                System.out.println("Result not found");
                return "Result not found";
            }
            return parkingLotStatus;

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
