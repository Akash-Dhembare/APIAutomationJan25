package com.akash.dhembare2000.ex_31012025.TESTNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG_006 {
    @Test
    public void serverStartedOK(){
        System.out.println("Server Started");
      //  Assert.assertEquals(true,false);
    }

    @Test(dependsOnMethods = "serverStartedOK")
    public void method1(){
        System.out.println("method1");
    }
}
