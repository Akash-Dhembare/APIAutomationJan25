package com.akash.dhembare2000.ex_31012025.TESTNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG_005 {

    @Test(groups = {"sanity","preprod"})
    public void sanityRun(){
        System.out.println("Sanity");
        System.out.println("QA");
    }

    @Test(groups = {"qa", "preprod"})
    public void regRun(){
        System.out.println("Reg");
        System.out.println("QA");
    }

    @Test(groups = {"dev", "qa"})
    public void smokeRun(){
        System.out.println("Smoke");
        System.out.println("Dev");
        Assert.assertTrue(true);
    }
}
