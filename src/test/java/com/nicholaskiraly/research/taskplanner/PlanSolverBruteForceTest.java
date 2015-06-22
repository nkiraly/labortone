package com.nicholaskiraly.research.taskplanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.yaml.snakeyaml.Yaml;

public class PlanSolverBruteForceTest {

  public PlanSolverBruteForceTest() {
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
  public void testCalculateTaskDepends002() throws FileNotFoundException, IOException {
    String taskFileName = "/tasks002_unsorted.yml";
    assertNotNull("Tasks resource " + taskFileName + " not found on class path", getClass().getResource(taskFileName));
    File taskFile = new File(getClass().getResource(taskFileName).getFile());
    assertTrue("Tasks file " + taskFileName + " does not exist", taskFile.exists());

    FileInputStream taskFIS = new FileInputStream(taskFile);
    Yaml yaml = new Yaml();
    String taskContent = IOUtils.toString(taskFIS);
    Map unsortedTaskMap = (LinkedHashMap) yaml.load(taskContent);

    assertNotNull("Unsorted Task Map is null", unsortedTaskMap);
    assertNotEquals("Unsorted Task file " + taskFileName + " load resulted in 0 tasks in list", unsortedTaskMap.size(), 0);

    PlanSolverBruteForce psbf = new PlanSolverBruteForce();

    Map sortedTaskMap = psbf.calculateTaskDepends(unsortedTaskMap);

    // confirm task dependency order
    Set sortedTaskKeySet = sortedTaskMap.keySet();
    assertEquals("Sorted Task Key Set is not size 3", 3, sortedTaskKeySet.size());
    String[] sortedKeyArray = (String[]) sortedTaskKeySet.toArray(new String[sortedTaskKeySet.size()]);
    assertEquals("First task is not task1", "task1", sortedKeyArray[0]);
    assertEquals("Second task is not task2", "task2", sortedKeyArray[1]);
    assertEquals("Third task is not task3", "task3", sortedKeyArray[2]);
  }
  
  @Test
  public void testCalculateTaskDepends003() throws FileNotFoundException, IOException {
    String taskFileName = "/tasks003_unsorted.yml";
    assertNotNull("Tasks resource " + taskFileName + " not found on class path", getClass().getResource(taskFileName));
    File taskFile = new File(getClass().getResource(taskFileName).getFile());
    assertTrue("Tasks file " + taskFileName + " does not exist", taskFile.exists());

    FileInputStream taskFIS = new FileInputStream(taskFile);
    Yaml yaml = new Yaml();
    String taskContent = IOUtils.toString(taskFIS);
    Map unsortedTaskMap = (LinkedHashMap) yaml.load(taskContent);

    assertNotNull("Unsorted Task Map is null", unsortedTaskMap);
    assertNotEquals("Unsorted Task file " + taskFileName + " load resulted in 0 tasks in list", unsortedTaskMap.size(), 0);

    PlanSolverBruteForce psbf = new PlanSolverBruteForce();

    Map sortedTaskMap = psbf.calculateTaskDepends(unsortedTaskMap);

    // confirm task dependency order
    Set sortedTaskKeySet = sortedTaskMap.keySet();
    assertEquals("Sorted Task Key Set is not size 3", 3, sortedTaskKeySet.size());
    String[] sortedKeyArray = (String[]) sortedTaskKeySet.toArray(new String[sortedTaskKeySet.size()]);
    assertEquals("First task is not task1", "task1", sortedKeyArray[0]);
    assertEquals("Second task is not task2", "task2", sortedKeyArray[1]);
    assertEquals("Third task is not task3", "task3", sortedKeyArray[2]);
  }

}