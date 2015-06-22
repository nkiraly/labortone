package com.nicholaskiraly.research.taskplanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;
// import the PlanSolver classes so they can be referenced by short name in solve()
import com.nicholaskiraly.research.taskplanner.PlanSolverStatic;
import com.nicholaskiraly.research.taskplanner.PlanSolverBruteForce;
import org.yaml.snakeyaml.DumperOptions;

public class TaskPlanner {

  /**
   * Tasks to be performed
   */
  protected Map taskMap;

  /**
   * Computing resources available
   */
  protected Map resourceMap;

  /**
   * Solution Plan Steps
   */
  protected Map solutionMap;

  public void loadTasksFromFile(File taskFile) throws FileNotFoundException, IOException, TaskPlannerException {
    FileInputStream taskFIS = new FileInputStream(taskFile);
    Yaml yaml = new Yaml();
    String taskContent = IOUtils.toString(taskFIS);
    LinkedHashMap map = (LinkedHashMap)yaml.load(taskContent);
    if ( map.size() == 0 ) {
      throw new TaskPlannerException("No tasks found in taskFile " + taskFile.getPath());
    }
    //System.out.println(map);
    this.taskMap = map;
  }

  public Map getTaskMap() {
    return this.taskMap;
  }

  public void loadResourcesFromFile(File resourceFile) throws FileNotFoundException, IOException, TaskPlannerException {
    FileInputStream resourceFIS = new FileInputStream(resourceFile);
    Yaml yaml = new Yaml();
    String resourceContent = IOUtils.toString(resourceFIS);
    LinkedHashMap map = (LinkedHashMap)yaml.load(resourceContent);
    if ( map.size() == 0 ) {
      throw new TaskPlannerException("No resources found in resourceFile " + resourceFile.getPath());
    }
    //System.out.println(map);
    this.resourceMap = map;
  }
  
  public Map getResourceMap() {
    return this.resourceMap;
  }
  
  /**
   * Solver class to use when solving the task plan
   * Use PlanSolverBruteForce by default
   */
  protected String solverClass = "com.nicholaskiraly.research.taskplanner.PlanSolverBruteForce";
  
  public void setSolverClass(String className) {
    // if there are no namespace qualifiers on the class
    // prefix it with  com.nicholaskiraly.research.taskplanner.
    if (className.indexOf(".") == -1) {
      className = "com.nicholaskiraly.research.taskplanner." + className;
    }
    this.solverClass = className;
  }
  
  public String getSolverClass() {
    return this.solverClass;
  }

  public void solve() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    Class clazz = Class.forName(this.solverClass);
    PlanSolverInterface ps = (PlanSolverInterface) clazz.newInstance();
    this.solutionMap = ps.solve(this.taskMap, this.resourceMap);
  }
  
  public Map getSolutionMap() {
    return this.solutionMap;
  }
  
  public String getSolutionYaml() {
    // use dumper flowstyle BLOCK to put newlines for each entry instead of a comma
    DumperOptions options = new DumperOptions();
    options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    Yaml yaml = new Yaml(options);
    String solutionYaml = yaml.dump(this.solutionMap);
    return solutionYaml;
  }

}
