package com.nicholaskiraly.research.labortone;

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
 * Test that PlanSolver implementations yield the expected plan
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
   * Test all the PlanSolvers
   *
   * @throws IOException
   * @throws FileNotFoundException
   * @throws LaborToneException
   */
  @Test
  @UseDataProvider("providePlanSolverVariants")
  public void testPlanSolvers(String planSolverClass, String taskFileName, String resourceFileName, String expectedPlanFileName) throws IOException, FileNotFoundException, LaborToneException, ClassNotFoundException, InstantiationException, IllegalAccessException {

    File taskFile = new File(getClass().getResource(taskFileName).getFile());
    File resourceFile = new File(getClass().getResource(resourceFileName).getFile());

    LaborTone planner = new LaborTone();

    planner.loadTasksFromFile(taskFile);

    planner.loadResourcesFromFile(resourceFile);

    planner.setSolverClass(planSolverClass);

    File expectedPlanFile = new File(getClass().getResource(expectedPlanFileName).getFile());
    String expectedPlanYamlString = IOUtils.toString(expectedPlanFile.toURI());

    // special case to load expectedPlan yaml PlanSolver is Static baseline implementation
    if (planSolverClass.equals("PlanSolverStatic")) {
      PlanSolverStatic.setSolutionYamlString(expectedPlanYamlString);
    }

    planner.solve();

    String planYamlString = planner.getSolutionYaml();

    assertEquals(expectedPlanYamlString, planYamlString);
  }
  
  /**
   * Solver test file sets
   *
   * for more info, see https://github.com/TNG/junit-dataprovider/wiki/Getting-started#usage
   *
   * @return
   */
  @DataProvider
  public static Object[][] providePlanSolverVariants() {
    return new Object[][]{
      {"PlanSolverStatic",     "/tasks001.yml", "/resources001.yml", "/plan001.yml"},
      {"PlanSolverBruteForce", "/tasks001.yml", "/resources001.yml", "/plan001.yml"},
      {"PlanSolverStatic",     "/tasks003.yml", "/resources003.yml", "/plan003.yml"},
      {"PlanSolverBruteForce", "/tasks003.yml", "/resources003.yml", "/plan003.yml"},
    };
  }

}
