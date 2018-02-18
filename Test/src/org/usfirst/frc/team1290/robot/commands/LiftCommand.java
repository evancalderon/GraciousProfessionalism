package org.usfirst.frc.team1290.robot.commands;

import org.usfirst.frc.team1290.robot.subsystems.UpDownSubsystem;

public class LiftCommand extends UpDownCommandBase
{
	public LiftCommand(UpDownSubsystem upDown)
	{
		super(upDown);
	}

	@Override
	protected boolean isAtLevel()
	{
		return m_upDown.isAtTop();
	}

	@Override
	protected void moveToLevel()
	{
		m_upDown.moveTop();
	}
}
