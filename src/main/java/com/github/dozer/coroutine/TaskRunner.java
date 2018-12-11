package com.github.dozer.coroutine;


import com.github.dozer.coroutine.helpers.RunParallelTask;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class TaskRunner {

  private RunParallelTask mainTask;
  private boolean disabled = false;

  public TaskRunner(Task... tasks) {
    this.mainTask = new RunParallelTask(tasks);
  }

  public void run() {
    if (disabled) {
      return;
    }else{
      this.mainTask.run();
    }
  }

  public void disable() {
    this.disabled = true;
    this.free();
  }
  public void free(){
    this.mainTask.free();
  }
}
