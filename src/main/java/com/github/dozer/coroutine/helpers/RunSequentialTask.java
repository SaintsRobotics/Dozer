package com.github.dozer.coroutine.helpers;

import com.github.dozer.coroutine.Task;

		
public class RunSequentialTask extends Task {
	private Task[] tasks;
	private TaskRunner sequentialRunner;


	public RunSequentialTask(Task...tasks){
		this.tasks = tasks;
		sequentialRunner = new TaskRunner(this.tasks);
		
}
  @Override
  protected void runTask() {
    if (sequentialRunner != null) {
      sequentialRunner.run();
    }
    this.sequentialRunner.disable();
    /**
	  for(Task t : this.tasks) {
		  t.iterator =  t.iterator();
		  while(t.iterator.hasNext()) {
			  yield(t.iterator.next());
		  }
	  }**/
  }
	public void free(){
		for(Task task: tasks){
			task.free();
		}
		super.free();
	}
}
