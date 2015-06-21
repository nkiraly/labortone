package com.nicholaskiraly.research.taskplanner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputFileTest {

  public InputFileTest() {
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

  /**
   * Test Planner.loadTasksFromFile
   */
  @Test
  public void testTasksFromFile() {
    String taskFileName = "/tasks001.yml";
    assertNotNull("Tasks resource " + taskFileName + " not found on class path", getClass().getResource(taskFileName));
    File templateFile = new File(getClass().getResource(taskFileName).getFile());
    assertTrue("Tasks file " + taskFileName + " does not exist", templateFile.exists());

    Planner planner = new Planner();

    planner.loadTasksFromFile(templateFile);

    ArrayList<Task> tasks = planner.getTasks();
    
    assertNotNull("Task list is null", tasks);

    org.junit.Assert.assertNotEquals("Task file " + taskFileName + " load resulted in 0 tasks in list", tasks.size(), 0);
  }
  
  /**
   * Test Planner.loadTasksFromFile
   */
  @Test
  public void loadResourcesFromFile() {
    String resourceFileName = "/resources001.yml";
    assertNotNull("Resources resource " + resourceFileName + " not found on class path", getClass().getResource(resourceFileName));
    File templateFile = new File(getClass().getResource(resourceFileName).getFile());
    assertTrue("Resources file " + resourceFileName + " does not exist", templateFile.exists());

    Planner planner = new Planner();

    planner.loadResourcesFromFile(templateFile);

    ArrayList<ComputeResource> resources = planner.getResources();
    
    assertNotNull("Resources list is null", resources);

    org.junit.Assert.assertNotEquals("Resource file " + resourceFileName + " load resulted in 0 resources in list", resources.size(), 0);
  }

}
