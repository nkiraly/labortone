package com.nicholaskiraly.research.taskplanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test that PlanSolver implementations yield the same result as the
 * PlanSolverStatic baseline implementation
 */
public class SolverComparisonTest {

  public SolverComparisonTest() {
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
   * Test PlanSolverBruteForce against known static solution
   *
   * @throws IOException
   * @throws FileNotFoundException
   * @throws TaskPlannerException
   */
  @Test
  public void testPlanSolverBruteForce() throws IOException, FileNotFoundException, TaskPlannerException {

    String taskFileName = "/tasks001.yml";
    File taskFile = new File(getClass().getResource(taskFileName).getFile());

    String resourceFileName = "/resources001.yml";
    File resourceFile = new File(getClass().getResource(resourceFileName).getFile());

    Planner planner = new Planner();

    planner.loadTasksFromFile(taskFile);

    planner.loadResourcesFromFile(resourceFile);

    planner.setSolverClass("PlanSolverBruteForce");

    planner.solve();

    String planYamlString = planner.getSolutionYaml();

    String planFileName = "/plan001.yml";
    File planFile = new File(getClass().getResource(planFileName).getFile());
    String expectedPlanYamlString = IOUtils.toString(planFile.toURI());

    assertEquals(expectedPlanYamlString, planYamlString);
  }

}
