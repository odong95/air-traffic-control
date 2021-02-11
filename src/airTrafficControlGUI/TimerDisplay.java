package airTrafficControlGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerDisplay implements ActionListener
{
	private Timer tm;
	private long elapsedTime = 0;
	private long elapsedSeconds; 
	private long secondsDisplay; 
	private long minutesDisplay; 

	public TimerDisplay()
	{
		tm = new Timer(1000,this);
		tm.start();
	}
	public void setTime()
	{
		elapsedTime+=1000;
		elapsedSeconds = elapsedTime / 1000;
		secondsDisplay = elapsedSeconds % 60;
		minutesDisplay = elapsedSeconds / 60;
	}

	public String getCurrentTime()
	{
		if (secondsDisplay < 10 && minutesDisplay < 10)
			return ("0" + minutesDisplay + ":0" + secondsDisplay);

		else if (secondsDisplay >= 10 && minutesDisplay < 10)
		{
			return ("0" + minutesDisplay + ":" + secondsDisplay);
		}

		else
		{
			return (minutesDisplay + ":" + secondsDisplay);
		}

	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		setTime();
	}
	
	public void pause()
	{
		tm.stop();
	}
	
	public void run()
	{
		tm.start();
	}
}
