package com.nicholaskiraly.research.taskplanner;

public class Task {

  /**
   * Cores required to perform task - must be available on the same computing
   * resource
   */
  protected int coresRequired;
  /**
   * Number of global "ticks" that pass before the task will be complete and can
   * release all its computing resources
   */
  protected int executionTime;
  /**
   * Tasks that must be completely finished before this task can start
   */
  protected String parentTasks;
}
