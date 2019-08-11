package com.dev.r1;


import com.dev.r1.ParkingLotDrivers.ExecutionDriver;
import com.dev.r1.ParkingLotDrivers.FileDriver;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    static ExecutionDriver ed;

    @Test
    public  void setUp() throws Exception {
        Path inputfilepath = Paths.get(getClass().getClassLoader()
                .getResource("input.txt").toURI());
        ed = new FileDriver(inputfilepath.toString());
        ed.execute();

    }
}
