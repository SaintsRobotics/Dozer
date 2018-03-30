package com.github.dozer.output;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Timer;

public class MotorRamping implements Motor{

  private SpeedController speedController;
  private double motorRamping;
  private double lastTime;

  public MotorRamping(SpeedController speedController, boolean inverted) {
    this(speedController, inverted, 5);
  }

  public MotorRamping(SpeedController speedController, boolean inverted, double rampPerSecond) {
    this.speedController = speedController;
    this.speedController.setInverted(inverted);
    this.motorRamping = rampPerSecond;
    this.lastTime = System.currentTimeMillis();
  }

  private double setpoint = 0;
  private double current = 0;

  public double get(){
    return speedController.get();
  }


  public void set(double speed) {
    setpoint = speed;
  }

  public void stop() {
    speedController.stopMotor();
    setpoint = 0;
    current = 0;
  }

  public void update() {
    double moveAmount = motorRamping * (System.currentTimeMillis() - this.lastTime);
    if (Math.abs(setpoint - current) < moveAmount) {
      current = setpoint;
    } else if (setpoint > moveAmount) {
      current += motorRamping;
    } else if (setpoint < moveAmount) {
      current -= motorRamping;
    }
    speedController.set(current);
    this.lastTime = System.currentTimeMillis();
  }
}
