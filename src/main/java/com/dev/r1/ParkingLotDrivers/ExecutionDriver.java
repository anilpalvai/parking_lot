package com.dev.r1.ParkingLotDrivers;
import com.dev.r1.Vehicles.Car;
/*
Creating abstract Execution driver to handle
both file and interactive modes of execution
 */



public abstract class ExecutionDriver {

    public String execute_parkinglot(String inputcommand){
        return null;
    }

    //Implementing method overloading to handle file and interactive modes
    public  void execute(){

    }

    public  String execute(String inputcommand){
        return null;
    }

    //method to execute parkingLot event based upon input command
    Parking_Allocator Park_event =null;
    //Implementing Common menthod Implement_parkinglot menthod on
    //Both File and Interactive modes

    protected String parkinglot_events(String input_command) {
        Car car;
        String[] input_split=input_command.split(" ");

        //Validating the input command passed
        if ((input_split[0].equals("status")||input_split[0].equals("exit"))&& !(input_split.length == 1)) {
            System.out.println("invalid number of args passed");
        } else {
            if (input_split[0].equals("park") && !(input_split.length == 3)) {
                System.out.println("invalid number of args passed" + input_split[0]);
            } else {
                if (!(input_split[0].equals("status")||input_split[0].equals("exit") ||input_split[0].equals("park"))&&
                    input_split.length != 2) {
                    System.out.println("invalid number of args passed   >>> " + input_split[0]);
                }
            }
        }


        try {
            switch (input_split[0]) {

                case "create_parking_lot":
                    Park_event = Parking_Allocator.createParkingSlot(input_split[1]);
                    return Park_event.get_noOfSots();

                case "park":
                    car = new Car(input_split[1], input_split[2]);

                    String ParkingCarStatus= Parking_Allocator.park(car);
                    return ParkingCarStatus;

                case "leave":
                    String leave_Status= Parking_Allocator.leave(input_split[1]);
                    return leave_Status;

                case "status":
                    String parking_status= Parking_Allocator.status();
                    String display_Status = parking_status.substring(1, parking_status.length() - 1);
                    return (display_Status.replaceAll(",",""));


                case "registration_numbers_for_cars_with_colour":
                    String regByColor= Parking_Allocator.getregnumsByColor(input_split[1]);
                    return regByColor;

                case "slot_numbers_for_cars_with_colour":
                    String SlotnumsByColor= Parking_Allocator.getSlotnumsByColor(input_split[1]);
                    return SlotnumsByColor;

                case "slot_number_for_registration_number":
                    String SlotByRegnum= Parking_Allocator.getSlotByRegnum(input_split[1]);
                    return SlotByRegnum;

                case "exit":
                    System.exit(0);
                case "default":
                    System.out.println("Please pass the commands correctly");

            }
        }catch (   Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;

    }

}
