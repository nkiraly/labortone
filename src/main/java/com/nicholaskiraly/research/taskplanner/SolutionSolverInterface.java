package com.nicholaskiraly.research.taskplanner;

import java.util.LinkedHashMap;

public interface SolutionSolverInterface {

  public LinkedHashMap solve(LinkedHashMap taskMap, LinkedHashMap resourceMap);

}
