package com.nicholaskiraly.research.taskplanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class TaskPlannerCLI {

  public static final String CLI_APP_NAME = "taskplanner";

  public static void main(String[] args) {
    CommandLineParser parser = new DefaultParser();

    Options options = new Options();
    Option help = new Option("help", "print this message");
    options.addOption(help);
    Option taskfile = OptionBuilder.withLongOpt("taskfile")
            .withDescription("task list yaml file")
            .hasArg()
            .withArgName("tasks.yml")
            .create("t");
    options.addOption(taskfile);
    Option resourcefile = OptionBuilder.withLongOpt("resourcefile")
            .withDescription("resource list yaml file")
            .hasArg()
            .withArgName("resources.yml")
            .create("r");
    options.addOption(resourcefile);
    Option solverClass = OptionBuilder.withLongOpt("solverclass")
            .withDescription("plan solver class to use")
            .hasArg()
            .withArgName("className")
            .create("c");
    options.addOption(solverClass);

    CommandLine cl = null;
    try {
      cl = parser.parse(options, args);
    } catch (ParseException ex) {
      Logger.getLogger(TaskPlannerCLI.class.getName()).log(Level.SEVERE, null, ex);
      System.err.println("CommandLine ParseException: " + ex.getMessage());
      System.exit(2);
    }

    // validate command line arguments
    if (!cl.hasOption("taskfile")) {
      System.err.println("taskfile not specified");
      TaskPlannerCLI.showHelp(options);
      System.exit(5);
    }
    File taskFile = new File(cl.getOptionValue("taskfile"));

    if (!cl.hasOption("resourcefile")) {
      System.err.println("resourcefile not specified");
      TaskPlannerCLI.showHelp(options);
      System.exit(5);
    }
    File resourceFile = new File(cl.getOptionValue("resourcefile"));

    TaskPlanner planner = new TaskPlanner();

    if (cl.hasOption("solverclass")) {
      planner.setSolverClass(cl.getOptionValue("solverclass"));
    }

    try {
      planner.loadTasksFromFile(taskFile);
    } catch (IOException ex) {
      Logger.getLogger(TaskPlannerCLI.class.getName()).log(Level.SEVERE, null, ex);
      System.err.println("loadTasksFromFile IOException: " + ex.getMessage());
      System.exit(2);
    } catch (TaskPlannerException ex) {
      Logger.getLogger(TaskPlannerCLI.class.getName()).log(Level.SEVERE, null, ex);
      System.err.println("loadTasksFromFile TaskPlannerException: " + ex.getMessage());
      System.exit(2);
    }

    try {
      planner.loadResourcesFromFile(resourceFile);
    } catch (IOException ex) {
      Logger.getLogger(TaskPlannerCLI.class.getName()).log(Level.SEVERE, null, ex);
      System.err.println("loadResourcesFromFile IOException: " + ex.getMessage());
      System.exit(2);
    } catch (TaskPlannerException ex) {
      Logger.getLogger(TaskPlannerCLI.class.getName()).log(Level.SEVERE, null, ex);
      System.err.println("loadResourcesFromFile TaskPlannerException: " + ex.getMessage());
      System.exit(2);
    }

    try {
      planner.solve();
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(TaskPlannerCLI.class.getName()).log(Level.SEVERE, null, ex);
      System.err.println("solve ClassNotFoundException: " + ex.getMessage());
      System.exit(2);
    } catch (InstantiationException ex) {
      Logger.getLogger(TaskPlannerCLI.class.getName()).log(Level.SEVERE, null, ex);
      System.err.println("solve InstantiationException: " + ex.getMessage());
      System.exit(2);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(TaskPlannerCLI.class.getName()).log(Level.SEVERE, null, ex);
      System.err.println("solve IllegalAccessException: " + ex.getMessage());
      System.exit(2);
    }

    String solutionYaml = planner.getSolutionYaml();

    System.out.println(solutionYaml);
  }

  public static void showHelp(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp(CLI_APP_NAME, options);
  }

}
