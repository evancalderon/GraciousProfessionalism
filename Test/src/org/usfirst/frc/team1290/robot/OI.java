package org.usfirst.frc.team1290.robot;

import org.usfirst.frc.team1290.robot.commands.DropCommand;
import org.usfirst.frc.team1290.robot.commands.LiftCommand;
import org.usfirst.frc.team1290.robot.commands.ReleaseBox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	static Joystick	joystick	= new Joystick(RobotMap.joystick);

	private Button	buttonA		= new JoystickButton(joystick, 2);
	private Button	buttonB		= new JoystickButton(joystick, 3);
	private Button	buttonX		= new JoystickButton(joystick, 1);
	private Button	buttonY		= new JoystickButton(joystick, 4);
	private Button	buttonL		= new JoystickButton(joystick, 5);
	private Button	buttonR		= new JoystickButton(joystick, 6);
	private Button	button7		= new JoystickButton(joystick, 7);
	private Button	button8		= new JoystickButton(joystick, 8);

	public OI()
	{
		// There are a few additional built in buttons you can use. Additionally,
		// by subclassing Button you can create custom triggers and bind those to
		// commands the same as any other Button.

		//// TRIGGERING COMMANDS WITH BUTTONS
		// Once you have a button, it's trivial to bind it to a button in one of
		// three ways:

		// Start the command when the button is pressed and let it run the command
		// until it is finished as determined by it's isFinished method.
		// button.whenPressed(new ExampleCommand());

		// Run the command while the button is being held down and interrupt it once
		// the button is released.
		// button.whileHeld(new ExampleCommand());

		// Start the command when the button is released and let it run the command
		// until it is finished as determined by it's isFinished method.
		// button.whenReleased(new ExampleCommand());
		//		buttonA.toggleWhenPressed(new AcquireBox());
		buttonB.toggleWhenPressed(new ReleaseBox());
		buttonY.whenPressed(new LiftCommand(Robot.getInstance().getElevator()));
		buttonA.whenPressed(new DropCommand(Robot.getInstance().getElevator()));
		buttonL.whenPressed(new LiftCommand(Robot.getInstance().getLegs()));
		buttonR.whenPressed(new DropCommand(Robot.getInstance().getLegs()));

	}

	public double getLeft()
	{
		return -(joystick.getRawAxis(RobotMap.left_axis));
	}

	public double getRight()
	{
		return joystick.getRawAxis(RobotMap.right_axis);
	}
}