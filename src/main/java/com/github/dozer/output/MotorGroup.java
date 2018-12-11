package com.github.dozer.output;

import java.util.ArrayList;
import java.util.List;

public class MotorGroup implements Motor{

  private Motor[] motors;

  public MotorGroup(Motor... motors) {
    this.motors = motors;
  }

  public MotorGroup(MotorGroup... motorGroups) {
    List<Motor> motorList = new ArrayList<>();
    for (MotorGroup motorGroup : motorGroups) {
      for (Motor motor : motorGroup.motors) {
        if (!motorList.contains(motor)) {
          motorList.add(motor);
        }
      }
    }
    motors = motorList.toArray(new Motor[0]);
  }

  @Override
  public void set(double speed) {
    for (Motor motor : motors) {
      motor.set(speed);
    }
  }

  @Override
  public double get(){
    return motors[0].get();
  }
  
  @Override
  public void stop() {
    for (Motor motor : motors) {
      motor.stop();
    }
  }

}
