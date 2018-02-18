package org.usfirst.frc.team1290.robot.subsystems;

import org.usfirst.frc.team1290.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 */
public class DriveTrain extends Subsystem
{

	//TO-DO: Fine tune the seneativity and such. [possibly consult driver]
	
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private final TalonSRX	m_leftDriver	= new TalonSRX(RobotMap.CTL_DRIVETRAIN_L_DRIVER);
	private final TalonSRX	m_leftFollower	= new TalonSRX(RobotMap.CTL_DRIVETRAIN_L_FOLLOW);
	private final TalonSRX	m_rightDriver	= new TalonSRX(RobotMap.CTL_DRIVETRAIN_R_DRIVER);
	private final TalonSRX	m_rightFollower	= new TalonSRX(RobotMap.CTL_DRIVETRAIN_R_FOLLOW);
	
	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		//Change Talon #2 to follow Talon #1
		m_leftFollower.set(ControlMode.Follower, m_leftDriver.getDeviceID());
		//Change Talon #3 to follow Talon #4
		m_rightDriver.set(ControlMode.Follower, m_rightFollower.getDeviceID());
 
		m_leftDriver.setNeutralMode(NeutralMode.Brake);
		m_leftFollower.setNeutralMode(NeutralMode.Brake);
		m_rightDriver.setNeutralMode(NeutralMode.Brake);
		m_rightFollower.setNeutralMode(NeutralMode.Brake);
	}

	public void setSpeedRight(double speed)
	{
		m_rightDriver.set(ControlMode.PercentOutput, Sensitivity.calculate(speed));
	}

	public void setSpeedLeft(double speed)
	{
		m_leftDriver.set(ControlMode.PercentOutput, Sensitivity.calculate(speed));
	}
}