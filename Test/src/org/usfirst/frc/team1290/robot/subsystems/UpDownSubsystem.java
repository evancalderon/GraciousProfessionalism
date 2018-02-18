package org.usfirst.frc.team1290.robot.subsystems;

import org.usfirst.frc.team1290.robot.Console;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public abstract class UpDownSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Command m_activeCommand = null;
	
    public abstract void moveTop();
	public abstract void moveBottom();
	public abstract boolean isAtTop();
	public abstract boolean isAtBottom();
	public abstract void stopMoving();
	
	public Command getActiveCommand()
	{
		return m_activeCommand;
	}

	public void setActiveCommand(Command cmd)
	{
		if(cmd != null)
		{
			Console.Print("setting command " + cmd);
		}else{
			Console.Print("clearing command");
		}
		
		m_activeCommand = cmd;
	}
}

