/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1290.robot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import org.usfirst.frc.team1290.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1290.robot.subsystems.Elevator;
import org.usfirst.frc.team1290.robot.subsystems.Legs;
import org.usfirst.frc.team1290.robot.subsystems.Pincer;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot
{
	private static Robot				ROBOT_SINGLETON;
	private DriveTrain					m_drivetrain	= new DriveTrain();
	private Pincer						m_pincer		= new Pincer();
	private Elevator					m_elevator		= new Elevator();
	private Legs						m_legs			= new Legs();
	private OI							m_oi			= null;
	//private Command						m_cmdAutonomous;
	private SendableChooser<Command>	m_chooser		= new SendableChooser<>();

	public static Robot getInstance()
	{
		return ROBOT_SINGLETON;
	}

	/**
	* This function is run when the robot is first started up and should be
	* used for any initialization code.
	*/
	@Override
	public void robotInit()
	{
		Thread t = new Thread(() ->
		{
			ByteBuffer bufInput = ByteBuffer.allocate(256);
			DatagramChannel chan = null;
			SocketAddress addr = new InetSocketAddress("172.22.11.1", 4678);
			try
			{
				chan = DatagramChannel.open();
				DatagramSocket s = chan.socket();
				s.connect(addr);
				byte[] tosend = "Hello!".getBytes();
				
				s.send(new DatagramPacket(tosend, tosend.length));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		t.start();

		try (DatagramSocket s = new DatagramSocket(4678))
		{
			InetSocketAddress address = new InetSocketAddress(4678);
			s.connect(address);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}

		ROBOT_SINGLETON = this;
		m_oi = new OI();
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit()
	{

	}

	@Override
	public void disabledPeriodic()
	{
		//Scheduler.getInstance().run();
	}

	public OI getOperatorInterface()
	{
		return m_oi;
	}

	public DriveTrain getDrivetrain()
	{
		return m_drivetrain;
	}

	public Pincer getPincer()
	{
		return m_pincer;
	}

	public Elevator getElevator()
	{
		return m_elevator;
	}
  
	public Legs getLegs()
	{
		return m_legs;
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit()
	{
		//		m_cmdAutonomous = m_chooser.getSelected();
		//
		//		/*
		//		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		//		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		//		 * = new MyAutoCommand(); break; case "Default Auto": default:
		//		 * autonomousCommand = new ExampleCommand(); break; }
		//		 */
		//
		//		// schedule the autonomous command (example)
		//		if (m_autonomousCommand != null)
		//		{
		//			m_autonomousCommand.start();
		//		}

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		//		if (m_autonomousCommand != null)
		//		{
		//			m_autonomousCommand.cancel();
		//		}
		Console.Print("Starting Robot Teleop");
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic()
	{
		m_drivetrain.setSpeedLeft(m_oi.getLeft());
		m_drivetrain.setSpeedRight(m_oi.getRight());
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic()
	{

	}
}
