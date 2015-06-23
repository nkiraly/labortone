package com.nicholaskiraly.research.labortone;

import java.util.Map;

/**
 * Interface for task execution plan solvers
 */
public interface PlanSolverInterface {

  public Map solve(Map taskMap, Map resourceMap);

}
