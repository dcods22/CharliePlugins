

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import charlie.bs.section1.*;
import charlie.bs.section2.*;
import charlie.bs.section3.*;
import charlie.bs.section4.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Brenden
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {Test00_17_4.class, Test01_16_5.class, Test02_17_8.class, Test03_19_11.class,
            Test04_10_3.class, Test05_8_4.class, Test06_9_7.class, Test07_7_7.class,
          Test08_A2_3.class, Test09_A9_5.class, Test10_A4_10.class, Test11_A8_9.class,
        Test12_22_4.class, Test13_99_2.class, Test14_QQ_7.class, Test15_88_10.class
        })
public class NewTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
