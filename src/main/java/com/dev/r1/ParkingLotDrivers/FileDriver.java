package com.dev.r1.ParkingLotDrivers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
FileDriver class to handle inputs from inputfile path
1) if input command is valid,it writes result on console
2) if not it writes error message on console
 */

public class FileDriver extends ExecutionDriver {
    String inputFilePath;

    public FileDriver(String input_path) {
        this.inputFilePath = input_path;

    }

    @Override
    public void execute() {
        try {
            File f = new File(inputFilePath);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                String output_status = super.parkinglot_events(readLine);
                if(output_status!=null) {
                    System.out.println(output_status);
                }else{
                    System.out.println("Result not found");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
