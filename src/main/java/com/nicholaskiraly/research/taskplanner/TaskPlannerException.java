package com.nicholaskiraly.research.taskplanner;

public class TaskPlannerException extends Exception {

  public TaskPlannerException() {
    super();
  }

  public TaskPlannerException(String message) {
    super(message);
  }

  public TaskPlannerException(String message, Throwable cause) {
    super(message, cause);
  }

  public TaskPlannerException(Throwable cause) {
    super(cause);
  }
}
