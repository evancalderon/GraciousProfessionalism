package org.usfirst.frc.team1290.robot;

import org.usfirst.frc.team1290.robot.commands.AcquireBox;
import org.usfirst.frc.team1290.robot.commands.ElevatorDrop;
import org.usfirst.frc.team1290.robot.commands.ElevatorLift;
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
	//Joystick joy2 = new Joystick(RobotMap.joy2);

	//	Button j2b1 = new JoystickButton(joy2, 1);
	//	Button j2b2 = new JoystickButton(joy2, 2);
	//	Button j2b3 = new JoystickButton(joy2, 3);
	//	Button j2b4 = new JoystickButton(joy2, 4);
	//	Button j2b5 = new JoystickButton(joy2, 5);
	//	Button j2b6 = new JoystickButton(joy2, 6);
	//	Button j2b7 = new JoystickButton(joy2, 7);
	//	Button j2b8 = new JoystickButton(joy2, 8);

	static Button	buttonA		= new JoystickButton(joystick, 2);
	static Button	buttonB		= new JoystickButton(joystick, 3);
	static Button	buttonX		= new JoystickButton(joystick, 1);
	static Button	buttonY		= new JoystickButton(joystick, 4);
	static Button	buttonL		= new JoystickButton(joystick, 5);
	static Button	buttonR		= new JoystickButton(joystick, 6);
	static Button	button7		= new JoystickButton(joystick, 7);
	static Button	button8		= new JoystickButton(joystick, 8);

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
		buttonA.toggleWhenPressed(new AcquireBox());
		buttonB.toggleWhenPressed(new ReleaseBox());
		button7.whenReleased(new ElevatorLift());
		button8.whenReleased(new ElevatorDrop());

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