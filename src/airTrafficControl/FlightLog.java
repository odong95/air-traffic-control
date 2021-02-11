package airTrafficControl;

import java.util.ArrayList;

import airTrafficControlGUI.TimerDisplay;


public class FlightLog
{
	private Airport [] airports;
	private ArrayList<String> laxLog, jfkLog,ordLog,dfwLog,miaLog;
	private ArrayList<String> log;
	public FlightLog(Airport [] n)
	{
		airports = n;//sets the airports
		log = new ArrayList<String>();
		//set flight log lists
		laxLog = new ArrayList<String>();
		jfkLog = new ArrayList<String>();
		ordLog = new ArrayList<String>();
		dfwLog = new ArrayList<String>();
		miaLog = new ArrayList<String>();
	}
	
	public void setFlightLogs()
	{
		// go through all the airports and add their log to the end of the individual lists
		for(Airport a: airports)
		{
			if(a.hasPlanes())
			{
				if(a.getAirportName().equals("LAX"))
				{
					laxLog.add(a.getFlightLog());
				}
				else if(a.getAirportName().equals("JFK"))
				{
					jfkLog.add(a.getFlightLog());
				}
				else if(a.getAirportName().equals("ORD"))
				{
					ordLog.add(a.getFlightLog());
				}
				else if(a.getAirportName().equals("DFW"))
				{
					dfwLog.add(a.getFlightLog());
				}
				else if(a.getAirportName().equals("MIA"))
				{
					miaLog.add(a.getFlightLog());
				}
			}
		}
	}
	
	public ArrayList<String> getFlightLog(String n)
	{
		// display the flight log of a specific airport
		
		if(n.equals("LAX"))
		{
			return laxLog;
		}
		else if(n.equals("JFK"))
		{
			return jfkLog;
		}
		else if(n.equals("ORD"))
		{
			return ordLog;
		}
		else if(n.equals("DFW"))
		{
			return dfwLog;
		}
		else if(n.equals("MIA"))
		{
			return miaLog;
		}

		return log;
	}
	

	
	
}
