package com.saintsrobotics.framework.coroutine.helpers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import com.saintsrobotics.framework.coroutine.Task;

public abstract class RunParallelTask extends Task {
	
	private List<Task> tasks;
	private int priority = 4; 

	public RunParallelTask(Task... tasks) {
		   this.tasks = new LinkedList<>();
		   this.tasks.addAll(Arrays.asList(tasks));
		
	}
	
	public RunParallelTask(int priority, Task... tasks) {
		this(tasks);
		setTaskPriority(priority);
	}
	
	public void setTaskPriority(int priority) {
		this.priority = priority; 
		changePriorities();
	}
	
	private void changePriorities() {
		for(Object t : this.tasks) {
			if(t instanceof RunParallelTask) {
				((RunParallelTask) t).setTaskPriority(priority-1);
			}
		}
	}
  @Override
  protected void runTask() {
	  for(Task t : this.tasks) {
		  Thread aa = new Thread((Runnable) t);
		  aa.setPriority(priority); 
		  aa.start();
	  }
	  
	  }
       
	 
}
