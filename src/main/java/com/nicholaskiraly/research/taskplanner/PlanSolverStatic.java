package com.nicholaskiraly.research.taskplanner;

import java.util.LinkedHashMap;

public class PlanSolverStatic implements PlanSolverInterface {

  @Override
  public LinkedHashMap solve(LinkedHashMap taskMap, LinkedHashMap resourceMap) {
    LinkedHashMap map = new LinkedHashMap();
    map.put("task1", "compute1");
    map.put("task2", "compute2");
    map.put("task3", "compute3");
    return map;
  }
}
