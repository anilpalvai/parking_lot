package com.dev.r1;

import com.dev.r1.ParkingLotDrivers.ExecutionDriver;
import com.dev.r1.ParkingLotDrivers.FileDriver;
import com.dev.r1.ParkingLotDrivers.InteractiveDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Invoking the driver class based upon number of arguments passed
 *
 */
public class Parking_lot
{
    public static void main( String[] args ) throws Exception
    {
        ExecutionDriver ed;
        try {
            if (args.length > 1) {
                ed = new FileDriver(args[0]);
                ed.execute();
            } else {
                while (true) {
                    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                    String input = bufferRead.readLine();
                    ed = new InteractiveDriver();
                    ed.execute("input");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

    }
}
