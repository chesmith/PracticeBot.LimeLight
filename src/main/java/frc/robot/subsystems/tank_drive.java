/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.arcade_drive;

/**
 * Add your docs here.
 */
public class tank_drive extends Subsystem {
  WPI_TalonSRX flDrive = new WPI_TalonSRX(RobotMap.flDrive);
  WPI_TalonSRX frDrive = new WPI_TalonSRX(RobotMap.frDrive);
  WPI_TalonSRX blDrive = new WPI_TalonSRX(RobotMap.blDrive);
  WPI_TalonSRX brDrive = new WPI_TalonSRX(RobotMap.brDrive);

  DifferentialDrive kopdrive = new DifferentialDrive(flDrive,frDrive);

  public void configDrive(){

    blDrive.follow(flDrive);
    brDrive.follow(frDrive);

    flDrive.setInverted(false);
    frDrive.setInverted(false);
    blDrive.setInverted(false);
    brDrive.setInverted(false);

}

public void teleopDrive(Joystick driveControl){
  kopdrive.arcadeDrive(driveControl.getRawAxis(1), driveControl.getRawAxis(4));
}

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new arcade_drive());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
