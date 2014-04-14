/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import charle.sb.section1.*;


/**
 *
 * @author Brenden Bishop & Dan Cody
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {Test_01.class, Test_02.class, Test_03.class, Test_04.class, Test_05.class,
            Test_06.class, Test_07.class, Test_08.class, Test_09.class, Test_10.class})

public class SideBetTestSuite {

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
