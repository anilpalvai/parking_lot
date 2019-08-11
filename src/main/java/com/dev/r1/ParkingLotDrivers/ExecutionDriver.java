package com.dev.r1.ParkingLotDrivers;

import com.dev.r1.Vehicles.Car;
/*
Creating abstract Execution driver to handle
both file and interactive modes of execution
 */


public abstract class ExecutionDriver {

    //method to execute parkingLot event based upon input command
    static ParkingAllocator parkingAllocator = null;

    //Implementing method overloading to handle file and interactive modes
    public void execute() {

    }

    public String execute(String inputcommand) {
        return null;
    }

    //Implementing Common menthod Implement_parkinglot menthod on
    //Both File and Interactive modes
    protected String parkinglot_events(String input_command) {
        Car car;
        String[] input_split = input_command.split(" ");

        //Validating the input command passed
        if ((input_split[0].equals("status") || input_split[0].equals("exit")) && !(input_split.length == 1)) {
            System.out.println("invalid number of args passed");
        } else {
            if (input_split[0].equals("park") && !(input_split.length == 3)) {
                System.out.println("invalid number of args passed" + input_split[0]);
            } else {
                if (!(input_split[0].equals("status") || input_split[0].equals("exit") || input_split[0].equals("park")) &&
                        input_split.length != 2) {
                    System.out.println("invalid number of args passed   >>> " + input_split[0]);
                }
            }
        }


        try {
            switch (input_split[0]) {


                //case is to handle parking lot creation if not created
                    //if created use the already created Parkinglot
                case "create_parking_lot":
                    parkingAllocator = ParkingAllocator.getOrCreate(input_split[1]);
                    return parkingAllocator.getNumOfSlots();

                    //case is to park a car in the created Parkinglot
                case "park":
                    car = new Car(input_split[1], input_split[2]);
                    String ParkingCarStatus = parkingAllocator.park(car);
                    return ParkingCarStatus;

                    //case is to leave the car from parkingslot and make it available for other cars
                case "leave":
                    String leave_Status = parkingAllocator.leave(input_split[1]);
                    return leave_Status;

                     //case is to dispaly status of parkingLot
                case "status":
                    String parking_status = parkingAllocator.status();
                    String display_Status = parking_status.substring(1, parking_status.length() - 1);
                    return (display_Status.replaceAll(",", ""));


                    //case is to dispaly lookup of cars by a color
                case "registration_numbers_for_cars_with_colour":
                    String regByColor = parkingAllocator.getregnumsByColor(input_split[1]);
                    return regByColor;

                    //case is to dispaly lookup of slots allocation for a color
                case "slot_numbers_for_cars_with_colour":
                    String SlotnumsByColor = parkingAllocator.getSlotnumsByColor(input_split[1]);
                    return SlotnumsByColor;

                    //case is to dispaly lookup of slots allocation for a regisration number
                case "slot_number_for_registration_number":
                    String SlotByRegnum = parkingAllocator.getSlotByRegNum(input_split[1]);
                    return SlotByRegnum;

                case "exit":
                    System.exit(0);

                case "default":
                    System.out.println("Please pass the commands correctly");

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;

    }

}
