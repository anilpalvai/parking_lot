# parking_lot Project for Gojek

This Java Maven project is to handle two modes of Driver files
1)Filemode, where we have to pass the list of input commands
2)Interactive mode to pass commands from console


#How to Run:

Please run below command sequentially from the Parking_lot folder
Filemode:
    1) bin/setup
    2) bin/parking_lot <inputfile_path>
    3) For funtionality test: "bin/run_functional_tests
    
Interactive mode:
    1) bin/setup
    2) bin/parking_lot (submit commands from console)
    
 Assumptions made on ParkingSlot:
 1) Will allow to allocate slots in incremental order for cars in order.
 2) if car leaves, parkinglot will allow the slot to next car which arrives
    Example: If three cars parked in lot and second cars left the parking then Parkinglot will allow the Fourth arrival car to     second slot.
 3) If Parking is full, will not allow car to park in Parkinglot.
 



