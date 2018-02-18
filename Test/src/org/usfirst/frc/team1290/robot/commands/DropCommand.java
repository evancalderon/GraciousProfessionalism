package org.usfirst.frc.team1290.robot.commands;

import org.usfirst.frc.team1290.robot.subsystems.UpDownSubsystem;

public class DropCommand extends UpDownCommandBase
{
	public DropCommand(UpDownSubsystem upDown)
	{
		super(upDown);
	}

	@Override
	protected boolean isAtLevel()
	{
		return m_upDown.isAtBottom();
	}

	@Override
	protected void moveToLevel()
	{
		m_upDown.moveBottom();
	}
}
