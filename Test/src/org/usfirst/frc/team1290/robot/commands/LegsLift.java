package org.usfirst.frc.team1290.robot.commands;

import org.usfirst.frc.team1290.robot.Robot;

public class LegsLift extends LegCommandBase
{
	@Override
	protected boolean isAtLevel()
	{
		return Robot.getInstance().getLegs().isAtTop();
	}

	@Override
	protected void moveToLevel()
	{
		Robot.getInstance().getLegs().moveTop();
	}
}
