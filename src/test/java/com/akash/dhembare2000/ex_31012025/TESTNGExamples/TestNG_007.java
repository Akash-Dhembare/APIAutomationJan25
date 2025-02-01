package com.akash.dhembare2000.ex_31012025.TESTNGExamples;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_007 {
    @Parameters("browser")
    @Test
    public void browserName(String value){
        System.out.println("Browser name is: "+value);
    }
}
