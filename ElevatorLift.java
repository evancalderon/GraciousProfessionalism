package org.usfirst.frc.team1290.robot.commands;

import org.usfirst.frc.team1290.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorLift extends Command
{

	protected void initialize()
	{
		/* choose the sensor and sensor direction */

	}

	protected void execute()
	{
		Robot.getInstance().getElevator().moveTop();
	}

	protected boolean isFinished()
	{
		// TODO: Make this return true when this Command no longer needs to run execute() 
		return true;
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
		super.interrupted();
	}
}
