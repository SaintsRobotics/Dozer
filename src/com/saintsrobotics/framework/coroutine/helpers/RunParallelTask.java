package com.saintsrobotics.framework.coroutine.helpers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import com.saintsrobotics.framework.coroutine.Task;

public abstract class RunParallelTask extends Task {
	
	private List<Task> tasks;

	public RunParallelTask(Task... tasks) {
		   this.tasks = new LinkedList<>();
		   this.tasks.addAll(Arrays.asList(tasks));
		
	}
  @Override
  protected void runTask() {

	    for (Iterator<Task> taskIterator = tasks.iterator(); taskIterator.hasNext();) {
	      Task task = taskIterator.next();
	      if (task.iterator == null) {
	        task.iterator = task.iterator();
	      }

	      if (task.waiter != null && task.waiter.getAsBoolean()) {
	        if (task.iterator.hasNext()) {
	          task.waiter = task.iterator.next();
	        } else {
	          taskIterator.remove();
	        }
	      }
	    }
	  }
       
	 
 
  /**
   * Runs this method in a while loop. Must wait inside.
   */
  protected abstract void runContinuously();
}
