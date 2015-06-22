package com.nicholaskiraly.research.taskplanner;

import java.util.LinkedHashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

/**
 * Baseline static plan solver to compare various planner implementations against
 */
public class PlanSolverStatic implements PlanSolverInterface {
  
  public static String solutionYamlString = null;
  public static Map solutionMap = null;
  
  public static void setSolutionYamlString(String solutionYamlString) {
    Yaml yaml = new Yaml();
    PlanSolverStatic.solutionYamlString = solutionYamlString;
    PlanSolverStatic.solutionMap = (LinkedHashMap)yaml.load(PlanSolverStatic.solutionYamlString);
  }

  @Override
  public Map solve(Map taskMap, Map resourceMap) {
    // return the static map built by setSolutionYamlString before this solve() call
    return PlanSolverStatic.solutionMap;
  }
}
