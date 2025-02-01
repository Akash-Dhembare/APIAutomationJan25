package com.akash.dhembare2000.ex_31012025.TESTNGExamples;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG_003 {
    @BeforeTest
    public void getToken(){
        System.out.println("1");
    }

    @BeforeTest
    public void getBookingID(){
        System.out.println("2");
    }

    @Test
    public void test_PUT(){
        System.out.println("3");
    }
}
