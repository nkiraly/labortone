package com.nicholaskiraly.research.taskplanner;

import java.util.LinkedHashMap;

public interface PlanSolverInterface {

  public LinkedHashMap solve(LinkedHashMap taskMap, LinkedHashMap resourceMap);

}
