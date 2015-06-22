package com.nicholaskiraly.research.taskplanner;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 * Test that PlanSolver implementations yield the same result as the
 * PlanSolverStatic baseline implementation
 */
@RunWith(DataProviderRunner.class)
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
  @UseDataProvider("providePlanSolverVariants")
  public void testPlanSolverBruteForce(String taskFileName, String resourceFileName, String planSolverClass, String expectedPlanFileName) throws IOException, FileNotFoundException, TaskPlannerException, ClassNotFoundException, InstantiationException, IllegalAccessException {

    File taskFile = new File(getClass().getResource(taskFileName).getFile());
    File resourceFile = new File(getClass().getResource(resourceFileName).getFile());

    TaskPlanner planner = new TaskPlanner();

    planner.loadTasksFromFile(taskFile);

    planner.loadResourcesFromFile(resourceFile);

    planner.setSolverClass(planSolverClass);

    planner.solve();

    String planYamlString = planner.getSolutionYaml();

    File expectedPlanFile = new File(getClass().getResource(expectedPlanFileName).getFile());
    String expectedPlanYamlString = IOUtils.toString(expectedPlanFile.toURI());

    assertEquals(expectedPlanYamlString, planYamlString);
  }
  
  /**
   * Tasks YAML file resource name Provider
   *
   * for more info, see https://github.com/TNG/junit-dataprovider/wiki/Getting-started#usage
   *
   * @return
   */
  @DataProvider
  public static Object[][] providePlanSolverVariants() {
    return new Object[][]{
      {"/tasks001.yml", "/resources001.yml", "PlanSolverBruteForce", "/plan001.yml"},
    };
  }

}
