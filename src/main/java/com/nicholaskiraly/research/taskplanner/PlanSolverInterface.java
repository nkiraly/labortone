package com.nicholaskiraly.research.taskplanner;

import java.util.Map;

/**
 * Interface for taskplanner solution calculators
 */
public interface PlanSolverInterface {

  public Map solve(Map taskMap, Map resourceMap);

}
