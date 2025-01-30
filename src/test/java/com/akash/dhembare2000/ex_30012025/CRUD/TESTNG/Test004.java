package com.akash.dhembare2000.ex_30012025.CRUD.TESTNG;

import org.testng.annotations.*;

public class Test004 {
    @BeforeSuite
    public void demo1(){
        System.out.println("Before Suite");
    }

    @BeforeTest
    public void demo2(){
        System.out.println("Before Test");
    }

    @BeforeClass
    public void demo3(){
        System.out.println("Before Class");
    }


    @BeforeMethod
    public void demo4(){
        System.out.println("Before Method");
    }

    @Test
    public void demo5(){
        System.out.println("Test");
    }


    @AfterMethod
    public void demo6(){
        System.out.println("After Method");
    }
    @AfterClass
    public void demo7(){
        System.out.println("After Class");
    }

    @AfterTest
    public void demo8(){
        System.out.println("After Test");
    }

    @AfterSuite
    public void demo9(){
        System.out.println("After Suite");
    }
}
