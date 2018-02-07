package org.usfirst.frc.team1290.robot.subsystems;

import org.usfirst.frc.team1290.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pincer extends Subsystem
{

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private final WPI_TalonSRX	m_leftPincer	= new WPI_TalonSRX(RobotMap.CTL_PINCER_L_DRIVER);
	private final WPI_TalonSRX	m_rightPincer	= new WPI_TalonSRX(RobotMap.CTL_PINCER_R_FOLLOW);
	private final WPI_TalonSRX	m_lift			= new WPI_TalonSRX(RobotMap.CTL_ELEVATOR);
	private final double		SpeedConstant	= 50;

	public void initDefaultCommand()
	{
		m_rightPincer.setNeutralMode(NeutralMode.Coast);
		m_leftPincer.setNeutralMode(NeutralMode.Coast);
		m_lift.setNeutralMode(NeutralMode.Brake);
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public void aquireBox()
	{
		m_leftPincer.set(SpeedConstant);
		m_rightPincer.set(SpeedConstant);//m_rightPincer.set(-SpeedConstant);
	}

	public void releaseBox()
	{
		m_leftPincer.set(-SpeedConstant);
		m_rightPincer.set(-SpeedConstant);//m_rightPincer.set(SpeedConstant);
	}

	public void stopSpinning()
	{
		m_leftPincer.set(0);
		m_rightPincer.set(0);
	}

}
