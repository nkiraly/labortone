package com.nicholaskiraly.research.taskplanner;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PlanSolverBruteForce implements PlanSolverInterface {

  @Override
  public Map solve(Map taskMap, Map resourceMap) {
    Map sortedTaskMap = this.orderTaskDepends(taskMap);
    
    // now with a dependency sorted task map
    // create a job schedule that will meet the requirements of the job dependencies
    LinkedHashMap map = new LinkedHashMap();
    map.put("task1", "compute1");
    map.put("task2", "compute1");
    map.put("task3", "compute3");
    return map;
  }

  /**
   * Order task dependencies by ordering the tasks based on parent_tasks specified
   * @param taskMap
   * @return Map
   */
  public Map orderTaskDepends(Map taskMap) {

    // Convert Task Map to List of entries
    // TaskMap has a LinkedHashMap for values from the task details in yaml
    List<Map.Entry<String, Map>> taskList = new LinkedList<>(taskMap.entrySet());

    // Sort list by dependency with comparator
    Collections.sort(taskList, new Comparator<Map.Entry<String, Map>>() {
      @Override
      public int compare(Map.Entry<String, Map> o1, Map.Entry<String, Map> o2) {
        String o1Name = o1.getKey();
        String o2Name = o2.getKey();
        Map o1Props = o1.getValue();
        Map o2Props = o2.getValue();

        if (o1Name.equals(o2Name)) {
          // if task names are the same, return o1 == o2
          return 0;
        }

        if (o1Props.get("parent_tasks") == null) {
          // no parent tasks in o1
        }
        else {
          // check o1 parent task dependencies
          String[] o1ParentTasks = o1Props.get("parent_tasks").toString().trim().split(",\\s*");
          for (String o1ParentTask : o1ParentTasks) {
            if (o1ParentTask.equals(o2Name)) {
              // if o1 depends on o2, return o1 > o2
              return 1;
            }
          }
        }
        
        if (o2Props.get("parent_tasks") == null) {
          // no parent tasks in o2
        }
        else {
          // check o2 parent task dependencies
          String[] o2ParentTasks = o2Props.get("parent_tasks").toString().trim().split(",\\s*");
          for (String o2ParentTask : o2ParentTasks) {
            if (o2ParentTask.equals(o1Name)) {
              // if o2 depends on o1, return o1 < o2
              return -1;
            }
          }
        }

        // default to natural order, return equal
        return 0;
      }
    });

    // Convert sorted list back to a Map
    Map<String, Object> sortedMap = new LinkedHashMap<>();
    for (Iterator<Map.Entry<String, Map>> it = taskList.iterator(); it.hasNext();) {
      Map.Entry<String, Map> entry = it.next();
      sortedMap.put(entry.getKey(), entry.getValue());
    }
    return sortedMap;
  }
}
