package org.usfirst.frc.team1290.robot.subsystems;

import org.usfirst.frc.team1290.robot.Console;
import org.usfirst.frc.team1290.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Elevator extends UpDownSubsystem
{

	private static final double	TICKS_PER_REV			= 4096;

	private static final int	ELEVATOR_BOTTOM_POS		= 0 - (381);

	private static final double	ELEVATOR_TOP_POS		= 2 * TICKS_PER_REV + 381;

	private static final int	ELEVATOR_VELOCITY		= 10000;

	/** 
	 * Which PID slot to pull gains from. Starting 2018, you can choose from 
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based 
	 * configuration. 
	 */

	/* 
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For 
	 * now we just want the primary one. 
	 */
	private static final int	elevator_PIDLoop_Idx	= 0;

	/* 
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and 
	 * report to DS if action fails. 
	 */
	private static final int	elevator_TimeoutMs		= 10;

	/* choose so that Talon does not report sensor out of phase */
	private static boolean		elevator_sensorPhase	= true;

	/* choose based on what direction you want to be positive, 
		this does not affect motor invert. */
	private static boolean		elevator_MotorInvert	= false;

	private final TalonSRX		m_elevator				= new TalonSRX(RobotMap.CTL_ELEVATOR);

	public void initDefaultCommand()
	{
		m_elevator.setNeutralMode(NeutralMode.Brake);
		m_elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, elevator_PIDLoop_Idx,
				elevator_TimeoutMs);

		/* choose to ensure sensor is positive when output is positive */
		m_elevator.setSensorPhase(elevator_sensorPhase);

		/* choose based on what direction you want forward/positive to be. 
		 * This does not affect sensor phase. */
		m_elevator.setInverted(elevator_MotorInvert);

		/* set the peak and nominal outputs, 12V means full */
		m_elevator.configNominalOutputForward(0, elevator_TimeoutMs);
		m_elevator.configNominalOutputReverse(0, elevator_TimeoutMs);
		m_elevator.configPeakOutputForward(1, elevator_TimeoutMs);
		m_elevator.configPeakOutputReverse(-1, elevator_TimeoutMs);
		/* 
		 * set the allowable closed-loop error, Closed-Loop output will be 
		 * neutral within this range. See Table in Section 17.2.1 for native 
		 * units per rotation. 
		 */
		m_elevator.configAllowableClosedloopError(0, elevator_PIDLoop_Idx, elevator_TimeoutMs);

		/* set closed loop gains in slot0, typically kF stays zero. */
		m_elevator.config_kF(elevator_PIDLoop_Idx, 0.0, elevator_TimeoutMs);
		m_elevator.config_kP(elevator_PIDLoop_Idx, 0.1, elevator_TimeoutMs);
		m_elevator.config_kI(elevator_PIDLoop_Idx, 0.0, elevator_TimeoutMs);
		m_elevator.config_kD(elevator_PIDLoop_Idx, 0.0, elevator_TimeoutMs);

		/* 
		 * lets grab the 360 degree position of the MagEncoder's absolute 
		 * position, and intitally set the relative sensor to match. 
		 */
		int absolutePosition = m_elevator.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		if (elevator_sensorPhase)
			absolutePosition *= -1;
		if (elevator_MotorInvert)
			absolutePosition *= -1;
		/* set the quadrature (relative) sensor to match absolute */
		m_elevator.setSelectedSensorPosition(absolutePosition, elevator_PIDLoop_Idx, elevator_TimeoutMs);
	}

	public void moveTop()
	{
		m_elevator.set(ControlMode.Position, ELEVATOR_TOP_POS);
	}
  
	public void moveBottom()
	{
		m_elevator.set(ControlMode.Position, ELEVATOR_BOTTOM_POS);
	}

	public boolean isAtTop()
	{
		//		System.out.println(m_elevator.getSelectedSensorPosition(elevator_PIDLoop_Idx));
		return m_elevator.getSelectedSensorPosition(elevator_PIDLoop_Idx) >= ELEVATOR_TOP_POS;
	}

	public boolean isAtBottom()
	{
		//		System.out.println(m_elevator.getSelectedSensorPosition(elevator_PIDLoop_Idx));
		return m_elevator.getSelectedSensorPosition(elevator_PIDLoop_Idx) <= ELEVATOR_BOTTOM_POS;
	}

	public void stopMoving()
	{
		//System.out.println("Before " + ControlMode.Velocity);
		Console.Print("Before " + m_elevator.getSelectedSensorPosition(elevator_PIDLoop_Idx));
		m_elevator.set(ControlMode.Velocity, 0);
		Console.Print("After " + m_elevator.getSelectedSensorPosition(elevator_PIDLoop_Idx));
		//System.out.println("After " + ControlMode.Velocity);
		//TODO: terminate command
	}
	public TalonSRX getDriver()
	{
		return m_elevator;
	}

	public int getLoopThing()
	{
		return elevator_PIDLoop_Idx;
	}
}
