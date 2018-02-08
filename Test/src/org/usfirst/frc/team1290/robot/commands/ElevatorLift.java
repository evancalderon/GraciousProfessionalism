package org.usfirst.frc.team1290.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1290.robot.subsystems.Elevator;

public class ElevatorLift extends Command
{
	protected void initialize()
	{
	}
	
	protected void execute()
	{
		Elevator.m_elevator.set(ControlMode.PercentOutput, Elevator.speed);
	}
	
	protected boolean isFinished()
	{
		// TODO: Make this return true when this Command no longer needs to run execute()
		return false;
	}
	
	protected void end()
	{
	}
	
	protected void interrupted()
	{
		super.interrupted();
	}
}
