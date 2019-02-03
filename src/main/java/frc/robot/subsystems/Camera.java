/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Camera system to allow toggling between a front-facing camera and a back-facing camera
 */
public class Camera extends Subsystem {
  UsbCamera frontCamera;
  UsbCamera backCamera;
  VideoSink cameraServer;

  boolean showFrontCamera = true;

  public Camera() {
    frontCamera = CameraServer.getInstance().startAutomaticCapture(0);
    backCamera = CameraServer.getInstance().startAutomaticCapture(1);

    frontCamera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 176, 144, 15);
    backCamera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 176, 144, 15);

    frontCamera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
    backCamera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);

    cameraServer = CameraServer.getInstance().getServer();//addServer("Switched camera");
  }

  public void toggle() {
    showFrontCamera = !showFrontCamera;
    if(showFrontCamera) {
      cameraServer.setSource(frontCamera);
    }
    else {
      cameraServer.setSource(backCamera);
    }
  }
  
  @Override
  public void initDefaultCommand() {
    // no default command
  }
}
