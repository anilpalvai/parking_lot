package com.dev.r1;

import com.dev.r1.ParkingLotDrivers.ExecutionDriver;
import com.dev.r1.ParkingLotDrivers.FileDriver;
import com.dev.r1.ParkingLotDrivers.InteractiveDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Invoking the driver class based upon number of arguments passed
 * without arguments invokes interactiveDriver class
 * with one argument invokes FileDriver class
 * else throws an exception
 */
public class Parkinglotmain {
    public static void main(String[] args) throws Exception {
        ExecutionDriver execute_driver;
        try {
            if (args.length > 1) {
                throw new IllegalArgumentException("arguments must be one or none");
            }
            if (args.length == 1) {
                execute_driver = new FileDriver(args[0]);
                execute_driver.execute();
            } else {
                while (true) {
                    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                    String input = bufferRead.readLine();
                    execute_driver = new InteractiveDriver();
                    execute_driver.execute(input);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
