package com.saintsrobotics.framework.coroutine.helpers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import com.saintsrobotics.framework.coroutine.Task;

public class RunParallelTask extends Task {
	
	private List<Task> tasks;

	public RunParallelTask(Task... tasks) {
			System.out.println("Parallel task constructor");
		   this.tasks = new LinkedList<>();
		   this.tasks.addAll(Arrays.asList(tasks));
	}		

  @Override
  protected void runTask() {
	  System.out.println("runTask called");
	  for (Iterator<Task> taskIterator = tasks.iterator(); taskIterator.hasNext();) {
		
	      Task task = taskIterator.next();
	      System.out.println("Running through task:" + task.toString());
	      if (task.iterator == null) {
	    	  System.out.println(task.toString() + " null. Initalizing");
	        task.iterator = task.iterator();
	      }

	      if (task.waiter != null && task.waiter.getAsBoolean()) {
	        if (task.iterator.hasNext()) {
		    	  System.out.println(task.toString() + " has next");
	          task.waiter = task.iterator.next();
	        } else {
		    	  System.out.println(task.toString() + " removed");

	          taskIterator.remove();
	        }
	      }
	    }
      wait.forFrame();

	  boolean test = true; 
	  for(Task t : tasks) {
		  System.out.println("At test " + t.toString());
		  test = test && !t.waiter.getAsBoolean();
		  System.out.println("waiter is " + t.waiter.getAsBoolean() + " test is " + test);
	  }
	  System.out.println("TEST VALUE");
	  System.out.println(test);
	  if(test) {
		  runTask();
	  }
}
}
