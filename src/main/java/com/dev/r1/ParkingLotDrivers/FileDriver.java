package com.dev.r1.ParkingLotDrivers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileDriver extends ExecutionDriver {
    String inputfilepath;
    public FileDriver(String input_path){
        this.inputfilepath=input_path;

    }

    @Override
    public void execute()  {
        try {
            File f = new File(inputfilepath);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                String output_status=super.parkinglot_events(readLine);
                System.out.println(output_status);

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
