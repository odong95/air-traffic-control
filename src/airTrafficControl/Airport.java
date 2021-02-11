package airTrafficControl;

import java.util.ArrayList;
import java.util.Iterator;

import airTrafficControlGUI.TimerDisplay;


public class Airport
{
	private String name;
	private int ULCx,ULCy;
	private ArrayList<Plane> planes;
	private TimerDisplay tm;
	public Airport(String n)
	{
		setName(n);//set name of airport
		planes = new ArrayList<Plane>();//create planes
		setCoordinates();
	}
	
	public String getAirportName()
	{
		return name; //return name of airport
	}
	
	public void addPlane(Plane p)
	{
		planes.add(p);//add plane to the airport
	}
	
	public void setName(String n)
	{
		name = n;	
	}
	public boolean hasPlanes()
	{
		if(planes.size() > 0)
		{
			return true;
		}
		return false;
	}
	
	public String getFlightLog()//displays last incoming/outgoing flight
	{
		if (hasPlanes())
		{
			if(planes.get(planes.size()-1).hasRefueled() && !planes.get(planes.size()-1).hasContinued())
			{
				return planes.get(planes.size()-1).toString() + " has stopped for refuel in " + getAirportName() + " at " +  tm.getCurrentTime();
			}
			
			if(planes.get(planes.size()-1).hasReachedDestination()) 
			{
				return planes.get(planes.size()-1).toString() + " has arrived in " + planes.get(planes.size()-1).getEndingAirport().getAirportName() + " at " + tm.getCurrentTime();
			}
			else if (!planes.get(planes.size()-1).hasReachedDestination() && !planes.get(planes.size()-1).hasTakenOff())
			{
				return planes.get(planes.size()-1).toString() + " has taken off from " + getAirportName()+ " at " + tm.getCurrentTime();
			}
		}
		return "";
	}
	
	public void removePlane(Plane p)
	{
		Iterator<Plane> it = planes.iterator();
		while (it.hasNext()) 
		{
			Plane pl = it.next();
			if (pl == p) 
			{
				it.remove();			
			}
		}
	}
	public int getULCx()
	{
		return ULCx;
	}
	
	public int getULCy()
	{
		return ULCy;
	}
	
	public void setCoordinates()

	{
		if(getAirportName().equals("LAX"))
		{
			ULCx = 70; // 78
			ULCy = 330; // 65
		}
		else if(getAirportName().equals("JFK"))
		{
			ULCx = 598; //578
			ULCy = 235; //65
		}
		else if(getAirportName().equals("ORD"))
		{
			ULCx = 443; //320
			ULCy = 230; //280
		}
		else if(getAirportName().equals("DFW"))
		{
			ULCx = 358; //78
			ULCy = 390; //455
		}
		else if(getAirportName().equals("MIA"))
		{
			ULCx = 578; //578
			ULCy = 460; //455
		}
	}
	
	public void addTimer(TimerDisplay t)
	{
		tm = t;
	}
	
}
