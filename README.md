LaborTone

LaborTone is a java-based compute task to heterogeneous resource execution planner.

# Overview
To ensure that the best planner algorithm can be developed, the input (tasks and resources) and output (task execution plan) are defined as data maps and are processed by the specified PlanSolverInterface implementation class. This approach allows for any and all algorithms and their variants to be explored, developed, and tested for speed and accuracy in a consistent way.

# Building
Use maven to build the LaborTone application

```bash
$ mvn clean verify
```

# Running
To run the LaborTone application, run the jar with java -jar

```bash
$ java -jar target/labortone-0.1.0-SNAPSHOT-withdepends.jar \
  --taskfile src/test/resources/tasks001.yml \
  --resourcefile src/test/resources/resources001.yml
```

# Testing LaborTone in the targeted test environment
For testing consistency, wind up the vagrant and virtualbox managed CentOS VM with

```bash
$ vagrant up
```

To test code changes or run the solution again, you only need to run vagrant provisioning steps again:

```bash
$ vagrant provision
```

If you want to get in the VM and poke things, you can get into the box with:

```bash
$ vagrant ssh
```

# Developing
To get started developing a new PlanSolver algorithm or tuning one that already exists, start with the SolverComparisonTest.
