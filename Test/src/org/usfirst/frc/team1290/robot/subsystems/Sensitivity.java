package org.usfirst.frc.team1290.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sensitivity extends Subsystem
{

	private static double coefficient = .01;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public static double calculate(double orig)
	{
		return coefficient * Math.pow(orig, 3) + (1 - coefficient) * orig;
	}
}
