package com.nicholaskiraly.research.taskplanner;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommandLineTest {
  
  public CommandLineTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  @Test
  public void testScenario001() throws IOException, TaskPlannerException {
    String[] args = new String[]{"-t", "tasks001.yml", "-r", "resources001.yml"};

    TaskPlannerCLI.main(args);
  }

}
