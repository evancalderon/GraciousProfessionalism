package org.usfirst.frc.team1290.robot.commands;

import org.usfirst.frc.team1290.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReleaseBox extends Command 
{
	private long m_startTime;
	
	public ReleaseBox()
	{
		System.out.println( "ctor");
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		m_startTime = System.currentTimeMillis();
		//System.out.println("Initialaden");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		//System.out.println("In Execute");
		Robot.getInstance().getPincer().releaseBox();
		//System.out.println("execute time" + m_startTime );
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		//System.out.println("isFinished time:" + (System.currentTimeMillis() - m_startTime));
		//System.out.println("execute time" + m_startTime );
		return (System.currentTimeMillis() - m_startTime) > 1000;
		
	}

	// Called once after isFinished returns true
	protected void end()
	{
		//System.out.println("END NOW");
		Robot.getInstance().getPincer().stopSpinning();
	}
}