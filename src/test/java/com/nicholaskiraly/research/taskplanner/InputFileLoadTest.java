package com.nicholaskiraly.research.taskplanner;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test that input files can be loaded by the planner API
 */
public class InputFileLoadTest {

  public InputFileLoadTest() {
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
   * Test that a tasks definition yaml file can be loaded by the planner API
   *
   * @throws IOException
   * @throws TaskPlannerException
   */
  @Test
  public void testLoadTasksFromFile() throws IOException, TaskPlannerException {
    String taskFileName = "/tasks001.yml";
    assertNotNull("Tasks resource " + taskFileName + " not found on class path", getClass().getResource(taskFileName));
    File taskFile = new File(getClass().getResource(taskFileName).getFile());
    assertTrue("Tasks file " + taskFileName + " does not exist", taskFile.exists());

    Planner planner = new Planner();

    planner.loadTasksFromFile(taskFile);

    Map taskMap = planner.getTaskMap();

    assertNotNull("Task Map is null", taskMap);

    assertNotEquals("Task file " + taskFileName + " load resulted in 0 tasks in list", taskMap.size(), 0);
  }

  /**
   * Test that a resources definition yaml file can be loaded by the planner API
   *
   * @throws IOException
   * @throws TaskPlannerException
   */
  @Test
  public void testLoadResourcesFromFile() throws IOException, TaskPlannerException {
    String resourceFileName = "/resources001.yml";
    assertNotNull("Resources resource " + resourceFileName + " not found on class path", getClass().getResource(resourceFileName));
    File resourceFile = new File(getClass().getResource(resourceFileName).getFile());
    assertTrue("Resources file " + resourceFileName + " does not exist", resourceFile.exists());

    Planner planner = new Planner();

    planner.loadResourcesFromFile(resourceFile);

    Map resourceMap = planner.getResourceMap();

    assertNotNull("Resource Map is null", resourceMap);

    assertNotEquals("Resource file " + resourceFileName + " load resulted in 0 resources in list", resourceMap.size(), 0);
  }

}
