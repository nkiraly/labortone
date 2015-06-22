package com.nicholaskiraly.research.taskplanner;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlanSolverBruteForce implements PlanSolverInterface {

  @Override
  public Map solve(Map taskMap, Map resourceMap) {
    LinkedHashMap map = new LinkedHashMap();
    map.put("task1", "compute1");
    map.put("task2", "compute1");
    map.put("task3", "compute3");
    return map;
  }
}
