package org.usfirst.frc.team1290.robot.commands;

import org.usfirst.frc.team1290.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorDrop extends Command
{

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.getInstance().getElevator().moveBottom();
	}

	protected boolean isFinished()
	{
		// TODO: Make this return true when this Command no longer needs to run execute() 
		if(Robot.getInstance().getElevator().isAtBottom())
		{
			Robot.getInstance().getElevator().stopMoving();
		}
		return Robot.getInstance().getElevator().isAtBottom();
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
		super.interrupted();
	}
}
