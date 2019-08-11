package com.dev.r1.ParkingLotDrivers;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParkingAllocatorTest {
    static ExecutionDriver ed;

    @BeforeClass
    public static void setUp() throws Exception {
        ed = new InteractiveDriver();
        String status = ed.execute("create_parking_lot 6");
        assertEquals("Created a parking lot with 6 slots", status);
        String status1 = ed.execute("park KA-01-HH-1234 White");
        assertEquals("Allocated slot number: 1", status1);
        String status2 = ed.execute("park KA-01-HH-9999 White");
        assertEquals("Allocated slot number: 2", status2);
        String status3 = ed.execute("park KA-01-BB-0001 Black");
        assertEquals("Allocated slot number: 3", status3);
        String status4 = ed.execute("park KA-01-HH-7777 Red");
        assertEquals("Allocated slot number: 4", status4);
        String status5 = ed.execute("park KA-01-HH-2701 Blue");
        assertEquals("Allocated slot number: 5", status5);
        String status6 = ed.execute("park KA-01-HH-3141 Black");
        assertEquals("Allocated slot number: 6", status6);
        String status7 = ed.execute("park DL-12-AA-9999 White");
        assertEquals("Sorry, parking lot is full", status7);
    }
    @Test
    public void testLeaveSlot() throws Exception {
        String leaveStatus = ed.execute("leave 4");
        assertEquals("Slot number 4 is free", leaveStatus);
    }
    @Test
    public void testRegnumByColor() throws Exception {
        String regByColorStatus = ed.execute("registration_numbers_for_cars_with_colour White");
        assertEquals("KA-01-HH-1234, KA-01-HH-9999", regByColorStatus);
    }

    @Test
    public void testSlotnumsByColor() throws Exception {
        String regByColor_Status = ed.execute("slot_numbers_for_cars_with_colour White");
        assertEquals("1,2", regByColor_Status);
    }
    @Test
    public void testSlotByRegnum() throws Exception {
        String regByColorStatus = ed.execute("slot_number_for_registration_number KA-01-HH-3141");
        assertEquals("6", regByColorStatus);
        String regByColot_Notfound = ed.execute("slot_number_for_registration_number MH-04-AY-1111");
        assertEquals("Not found", regByColot_Notfound);
    }

}