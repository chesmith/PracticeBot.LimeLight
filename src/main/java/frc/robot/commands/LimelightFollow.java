/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.text.DecimalFormat;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class LimelightFollow extends Command {
  public LimelightFollow() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.kopchassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

    // NetworkTableEntry camtran = table.getEntry("camtran");
    // double[] t = camtran.getDoubleArray(new double[6]);
    // DecimalFormat df = new DecimalFormat("###.##");
    // SmartDashboard.putString("LL-X", df.format(t[0]));
    // SmartDashboard.putString("LL-Y", df.format(t[1]));
    // SmartDashboard.putString("LL-Z", df.format(t[2]));
    // SmartDashboard.putString("LL-Yaw", df.format(t[3]));
    // SmartDashboard.putString("LL-Pitch", df.format(t[4]));
    // SmartDashboard.putString("LL-Roll", df.format(t[5]));

    double kp = 0.075;
    double output = kp * tx.getDouble(0);
    Robot.kopchassis.limelightDrive(Robot.m_oi.returnJoystickDrive(), output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
