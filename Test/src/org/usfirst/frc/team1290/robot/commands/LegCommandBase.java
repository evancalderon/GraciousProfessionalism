package org.usfirst.frc.team1290.robot.commands;

import org.usfirst.frc.team1290.robot.Robot;
import org.usfirst.frc.team1290.robot.subsystems.Legs;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class LegCommandBase extends Command {
	protected abstract boolean isAtLevel();
	protected abstract void moveToLevel();

    public LegCommandBase() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Legs legs = Robot.getInstance().getLegs();
		if (legs.getActiveCommand() == this)
		{
			return;
		}
		if (legs.getActiveCommand() != null)
		{
			legs.stopMoving();
		}
		legs.setActiveCommand(this);
		moveToLevel();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		Legs legs = Robot.getInstance().getLegs();
		if (isAtLevel())
		{
			legs.stopMoving();
			legs.setActiveCommand(null);
			return true;
		}
		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
