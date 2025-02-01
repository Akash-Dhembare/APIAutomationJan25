package com.akash.dhembare2000.ex_31012025.TESTNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG_008 {
    @Test
    public void test1(){
        Assert.assertTrue(false);
    }

    @Test(enabled = false)
    public void test2(){
        Assert.assertTrue(false);
    }

    @Test(alwaysRun = true)
    public void test3(){
        Assert.assertTrue(true);
    }
}
