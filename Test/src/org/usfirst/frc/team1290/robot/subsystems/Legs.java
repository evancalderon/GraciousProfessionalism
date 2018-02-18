package org.usfirst.frc.team1290.robot.subsystems;

import org.usfirst.frc.team1290.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Legs extends UpDownSubsystem
{
	private static final double	TICKS_PER_REV		= 4096;

	// Added 381 to compensate for setting position because position is borked
	private static final int	LIFT_LEGS_BOTTOM_POS	= 0 + (381);
	private static final double	LIFT_LEGS_TOP_POS		= -(140 * TICKS_PER_REV + 381);

	/** 
	 * Which PID slot to pull gains from. Starting 2018, you can choose from 
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based 
	 * configuration. 
	 */

	/* 
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For 
	 * now we just want the primary one. 
	 */
	private static final int	LiftLegs_PIDLoop_Idx	= 0;

	/* 
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and 
	 * report to DS if action fails. 
	 */
	private static final int	LiftLegs_TimeoutMs		= 10;

	/* choose so that Talon does not report sensor out of phase */
	private static boolean		LiftLegs_sensorPhase	= true;

	/* choose based on what direction you want to be positive, 
		this does not affect motor invert. */
	private static boolean		LiftLegs_MotorInvert	= false;

	private final TalonSRX		m_liftLegs_LF_Driver	= new TalonSRX(RobotMap.CTL_LEG_LF_DRIVER);
	private final TalonSRX		m_liftLegs_LB_Follower	= new TalonSRX(RobotMap.CTL_LEG_LB_FOLLOW);
	private final TalonSRX		m_liftLegs_RF_Follower	= new TalonSRX(RobotMap.CTL_LEG_RF_FOLLOW);
	private final TalonSRX		m_liftLegs_RB_Follower	= new TalonSRX(RobotMap.CTL_LEG_RB_FOLLOW);

	public void initDefaultCommand()
	{
		m_liftLegs_LB_Follower.set(ControlMode.Follower, m_liftLegs_LF_Driver.getDeviceID());
		m_liftLegs_RF_Follower.set(ControlMode.Follower, m_liftLegs_LF_Driver.getDeviceID());
		m_liftLegs_RB_Follower.set(ControlMode.Follower, m_liftLegs_LF_Driver.getDeviceID());

		m_liftLegs_LF_Driver.setNeutralMode(NeutralMode.Brake);
		m_liftLegs_LF_Driver.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, LiftLegs_PIDLoop_Idx,
				LiftLegs_TimeoutMs);

		/* choose to ensure sensor is positive when output is positive */
		m_liftLegs_LF_Driver.setSensorPhase(LiftLegs_sensorPhase);

		/* choose based on what direction you want forward/positive to be. 
		 * This does not affect sensor phase. */
		m_liftLegs_LF_Driver.setInverted(LiftLegs_MotorInvert);

		/* set the peak and nominal outputs, 12V means full */
		m_liftLegs_LF_Driver.configNominalOutputForward(0, LiftLegs_TimeoutMs);
		m_liftLegs_LF_Driver.configNominalOutputReverse(0, LiftLegs_TimeoutMs);
		m_liftLegs_LF_Driver.configPeakOutputForward(1, LiftLegs_TimeoutMs);
		m_liftLegs_LF_Driver.configPeakOutputReverse(-1, LiftLegs_TimeoutMs);
		/* 
		 * set the allowable closed-loop error, Closed-Loop output will be 
		 * neutral within this range. See Table in Section 17.2.1 for native 
		 * units per rotation. 
		 */
		m_liftLegs_LF_Driver.configAllowableClosedloopError(0, LiftLegs_PIDLoop_Idx, LiftLegs_TimeoutMs);

		/* set closed loop gains in slot0, typically kF stays zero. */
		m_liftLegs_LF_Driver.config_kF(LiftLegs_PIDLoop_Idx, 0.0, LiftLegs_TimeoutMs);
		m_liftLegs_LF_Driver.config_kP(LiftLegs_PIDLoop_Idx, 0.1, LiftLegs_TimeoutMs);
		m_liftLegs_LF_Driver.config_kI(LiftLegs_PIDLoop_Idx, 0.0, LiftLegs_TimeoutMs);
		m_liftLegs_LF_Driver.config_kD(LiftLegs_PIDLoop_Idx, 0.0, LiftLegs_TimeoutMs);

		/* 
		 * lets grab the 360 degree position of the MagEncoder's absolute 
		 * position, and intitally set the relative sensor to match. 
		 */
		int absolutePosition = m_liftLegs_LF_Driver.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		if (LiftLegs_sensorPhase)
			absolutePosition *= -1;
		if (LiftLegs_MotorInvert)
			absolutePosition *= -1;
		/* set the quadrature (relative) sensor to match absolute */
		m_liftLegs_LF_Driver.setSelectedSensorPosition(absolutePosition, LiftLegs_PIDLoop_Idx, LiftLegs_TimeoutMs);
	}

	public void moveTop()
	{
		m_liftLegs_LF_Driver.set(ControlMode.Position, LIFT_LEGS_TOP_POS);
	}

	public void moveBottom()
	{
		m_liftLegs_LF_Driver.set(ControlMode.Position, LIFT_LEGS_BOTTOM_POS);
	}

	public boolean isAtTop()
	{
		return m_liftLegs_LF_Driver.getSelectedSensorPosition(LiftLegs_PIDLoop_Idx) <= LIFT_LEGS_TOP_POS;
	}

	public boolean isAtBottom()
	{
		return m_liftLegs_LF_Driver.getSelectedSensorPosition(LiftLegs_PIDLoop_Idx) >= LIFT_LEGS_BOTTOM_POS;
	}

	public void stopMoving()
	{
		m_liftLegs_LF_Driver.set(ControlMode.PercentOutput, 0);
	}

	public TalonSRX getDriver()
	{
		return m_liftLegs_LF_Driver;
	}

	public int getLoopThing()
	{
		return LiftLegs_PIDLoop_Idx;
	}
}
