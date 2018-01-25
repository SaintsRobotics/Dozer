package com.saintsrobotics.framework.coroutine.helpers;

import com.saintsrobotics.framework.coroutine.Task;

public abstract class RunContinuousTask extends Task {

  @Override
  protected void runTask() {
    while (true) {
      if(runContinuously()) break;
    }
  }
  /*
   * 
   */
  protected boolean runContinuously() {
	  runForever();
	  return false;
  }
  /**
   * Runs this method in a while loop. Must wait inside.
   */
  protected void runForever() {
	  
  }
}
