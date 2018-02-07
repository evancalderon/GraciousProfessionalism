package org.usfirst.frc.team1290.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1290.robot.RobotMap;
import org.usfirst.frc.team1290.robot.commands.ElevatorDrop;
import org.usfirst.frc.team1290.robot.commands.ElevatorLift;

public class Elevator extends Subsystem
{
	public static final WPI_TalonSRX m_elevator = new WPI_TalonSRX(RobotMap.CTL_ELEVATOR);
	public static       double       speed      = 0.25;
	
	public void initDefaultCommand()
	{
	}
}
