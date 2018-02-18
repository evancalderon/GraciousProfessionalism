/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1290.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static final int leftMotor = 1;
	// public static final int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static final int rangefinderPort = 1;
	// public static final int rangefinderModule = 1;
	public static final int	CTL_DRIVETRAIN_L_DRIVER	= 1;	//Talon ID# that Left Front Motor is hooked up to.
	public static final int	CTL_DRIVETRAIN_L_FOLLOW	= 2;	//Talon ID# that Left Rear Motor is hooked up to.
	public static final int	CTL_DRIVETRAIN_R_DRIVER	= 4;	//Talon ID# that Right Front Motor is hooked up to.
	public static final int	CTL_DRIVETRAIN_R_FOLLOW	= 3;	//Talon ID# that Right Rear Motor is hooked up to.

	public static final int	CTL_PINCER_L_DRIVER		= 5;
	public static final int	CTL_PINCER_R_FOLLOW		= 6;
	
	public static final int	CTL_ELEVATOR			= 7;
  
	public static final int	CTL_PINCER_DEPLOY		= 8;
  
	//TODO: Find out how the motors for these legs need to turn
	public static final int	CTL_LEG_LF_DRIVER		= 13;
	public static final int	CTL_LEG_LB_FOLLOW		= 10;
	public static final int	CTL_LEG_RF_FOLLOW		= 11;
	public static final int	CTL_LEG_RB_FOLLOW		= 12;

	public static final int	joystick				= 0;	//ID# of the Master Joystick.

	public static final int	left_axis				= 1;
	public static final int	right_axis				= 3;

}
