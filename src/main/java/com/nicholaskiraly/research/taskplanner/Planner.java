package com.nicholaskiraly.research.taskplanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;

public class Planner {

  /**
   * Tasks to be performed
   */
  protected LinkedHashMap taskMap;

  /**
   * Computing resources available
   */
  protected LinkedHashMap resourceMap;

  /**
   * Solution Plan Steps
   */
  protected LinkedHashMap solutionMap;

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

  public LinkedHashMap getTaskMap() {
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
  
  public LinkedHashMap getResourceMap() {
    return this.resourceMap;
  }

  public void calculateSolution() {
  }
  
  public LinkedHashMap getSolutionMap() {
    return this.solutionMap;
  }

  public void outputSolution() {
    Yaml yaml = new Yaml();
    String output = yaml.dump(this.solutionMap);
    System.out.println(output);
  }

}
