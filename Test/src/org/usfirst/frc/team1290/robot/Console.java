package org.usfirst.frc.team1290.robot;

public class Console
{
	enum LogType
	{
		Print, Warning, Error
	}

	public static LogType Minimum = LogType.Print;

	static void Log(LogType type, String message)
	{
		if (type.ordinal() >= Minimum.ordinal())
		{
			System.out.println(message);
		}
	}

	public static void Error(String message)
	{
		Log(LogType.Error, message);
	}

	public static void Error(String format, Object... args)
	{
		Log(LogType.Error, String.format(format, args));
	}

	public static void Warn(String message)
	{
		Log(LogType.Warning, message);
	}

	public static void Warn(String format, Object... args)
	{
		Log(LogType.Warning, String.format(format, args));
	}

	public static void Print(String message)
	{
		Log(LogType.Print, message);
	}

	public static void Print(String format, Object... args)
	{
		Log(LogType.Print, String.format(format, args));
	}
}
