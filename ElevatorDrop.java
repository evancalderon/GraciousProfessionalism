package org.usfirst.frc.team1290.robot.commands; 

 

import edu.wpi.first.wpilibj.command.Command;



 
public class ElevatorDrop extends Command 
{

	//private OI elevator_oi = new OI();
	//private Elevator temp_elevator = new Elevator();

	protected void initialize()
	{
		/* choose the sensor and sensor direction */

	}

	protected void execute()
	{
	//	temp_elevator.set_elevator_pos(elevator_oi.getLeft());
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