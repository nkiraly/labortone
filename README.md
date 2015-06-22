TaskPlanner

TaskPlanner is a java-based compute task to heterogeneous resource execution planner.

# Overview
To ensure that the best planner algorithm can be developed, the input (tasks and resources) and output (task execution plan) are defined in data model objects. This way any and all algorithms and their variants and experimentation can be tested for speed and accuracy in the same way.

# Running
To run the TaskPlanner application, build the maven project and run the jar with

```bash
$ mvn clean verify

$ java -jar target/TaskPlanner.jar \
  --taskfile src/test/resources/tasks001.yml \
  --resourcefile src/test/resources/resources001.yml
```

Or wind up the vagrant and virtualbox CentOS VM with

```bash
$ vagrant up
```

To test changes or run the solution again, you only need to run vagrant provisioning steps again:

```bash
$ vagrant provision
```

# Developing
To get started developing a new PlanSolver algorithm or tuning one that is already present, start with the SolverComparisonTest.
