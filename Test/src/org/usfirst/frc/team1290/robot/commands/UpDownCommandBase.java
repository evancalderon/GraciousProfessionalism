package org.usfirst.frc.team1290.robot.commands;

import org.usfirst.frc.team1290.robot.Console;
import org.usfirst.frc.team1290.robot.subsystems.UpDownSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class UpDownCommandBase extends Command
{
	protected abstract boolean isAtLevel();

	protected abstract void moveToLevel();

	protected final UpDownSubsystem m_upDown;

	public UpDownCommandBase(UpDownSubsystem upDown)
	{
		m_upDown = upDown;
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (m_upDown.getActiveCommand() == this)
		{
			return;
		}
		if (m_upDown.getActiveCommand() != null)
		{
			m_upDown.getActiveCommand().cancel();
		}
		m_upDown.setActiveCommand(this);
		if (isFinished())
		{
			return;
		}
		moveToLevel();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		if (m_upDown.getActiveCommand() != this)
		{
			return true;
		}
		if (isAtLevel())
		{
			m_upDown.setActiveCommand(null);
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
	}
}
