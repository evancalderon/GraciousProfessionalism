package org.usfirst.frc.team1290.robot.commands;

import org.usfirst.frc.team1290.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ElevatorLift extends Command
{
//	//should prevent both comands from running
// 	public ElevatorLift(){
//		requires(Robot.getInstance().getElevator());
//		setTimeout(1);
//	}
	//===============
	protected void initialize()
	{
		/* choose the sensor and sensor direction */
		Scheduler.getInstance().removeAll();

	}

	protected void execute()
	{
		Robot.getInstance().getElevator().moveTop();
	}

	protected boolean isFinished()
	{
		// TODO: Make this return true when this Command no longer needs to run execute()
		if(Robot.getInstance().getElevator().isAtTop()){
			Robot.getInstance().getElevator().stopMoving();
		}
		return Robot.getInstance().getElevator().isAtTop();
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
		super.interrupted();
	}
}
