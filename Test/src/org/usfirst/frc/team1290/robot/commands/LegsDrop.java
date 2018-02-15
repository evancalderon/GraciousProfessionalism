package org.usfirst.frc.team1290.robot.commands;

import org.usfirst.frc.team1290.robot.Robot;

/**
 *
 */
public class LegsDrop extends LegCommandBase
{
	@Override
	protected boolean isAtLevel()
	{
		return Robot.getInstance().getLegs().isAtBottom();
	}

	@Override
	protected void moveToLevel()
	{
		Robot.getInstance().getLegs().moveBottom();
	}
}
