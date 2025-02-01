package com.akash.dhembare2000.ex_31012025.TESTNGExamples;

import org.apache.log.Priority;
import org.testng.annotations.Test;

public class TestNG_004 {
    @Test(priority=1)
    public void test1(){
        System.out.println("1");
    }

    @Test(priority = 0)
    public void test2(){
        System.out.println("0");
    }

    @Test(priority = 2)
    public void test3(){
        System.out.println("2");
    }
}
