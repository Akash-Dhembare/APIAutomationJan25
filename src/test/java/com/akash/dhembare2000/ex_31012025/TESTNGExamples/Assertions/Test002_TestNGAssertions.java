package com.akash.dhembare2000.ex_31012025.TESTNGExamples.Assertions;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Test002_TestNGAssertions {

    @Test
    public void hardAssertExample(){
        Assert.assertTrue(false); // This will throw an AssertionError and stop execution.
        System.out.println("This line will not be executed");
    }

    @Test
    public void softAssertExample(){
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(false, true);
        System.out.println("This line will execute even if testcase fails");
        softAssert.assertAll(); // This will report all collected reports
    }
}
