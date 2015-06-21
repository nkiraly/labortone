package com.nicholaskiraly.research.taskplanner;

import java.io.File;
import java.util.ArrayList;

public class Planner {

  /**
   * List of Tasks to be performed
   */
  protected ArrayList<Task> tasks;

  /**
   * List of computing resources available
   */
  protected ArrayList<ComputeResource> resources;

  /**
   * Solution Plan Steps
   */
  protected ArrayList<PlanStep> steps;

  public void loadTasksFromFile(File taskFile) {
  }

  public ArrayList<Task> getTasks() {
    return this.tasks;
  }

  public void loadResourcesFromFile(File resourceFile) {
  }
  
  public ArrayList<ComputeResource> getResources() {
    return this.resources;
  }

  public void calculatePlan() {
  }

  public void outputPlan() {

  }

}
