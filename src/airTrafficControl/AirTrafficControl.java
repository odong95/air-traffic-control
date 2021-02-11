package airTrafficControl;

import java.util.ArrayList;

import airTrafficControlGUI.TimerDisplay;


public class AirTrafficControl
{
	private Corridor [] corridors;
	private ArrayList<String> redirectionLog;
	private TimerDisplay tm;
	private boolean madeRedirection;
	public AirTrafficControl(Corridor [] c)
	{
		corridors = c;
		redirectionLog = new ArrayList<String>();
		madeRedirection = false;
	}
	
	public void handleCollisions()
	{
		
		//go through the corridors, see if it has any collisions
			for(Corridor cd: corridors)
			{
				if(cd.hasPossibleCollision())
				{
					
					Plane a = cd.getPlanes().get(0); 
					Plane b = cd.getPlanes().get(1);
					
					int altitude = 0;
					if( b.getAltitude() == 30000)
					{
						altitude = 35000;
					}
					else if(b.getAltitude() == 35000)
					{
						altitude = 40000;
					}
					else if(b.getAltitude() == 40000)
					{
						altitude = 30000;
					}
					b.setAltitude(altitude);//alter one of the planes altitude
					b.redirectingFlash();
					setRedirectionLog(a,b,altitude);
					madeRedirection = true;
					//printRedirectionLog(); //leave it commented, just for text simulations
				}
				
					
		     }
		
		
	}
	
	public boolean madeRedirection()
	{
		return madeRedirection;
	}
	public void printRedirectionLog()
	{
		for(int i = 0; i < getRedirectionLog().size(); i++)
		{
			System.out.println(getRedirectionLog().get(i));
		}
		
	}
	
	public void reset()
	{
		madeRedirection = false;
	}
	public void setRedirectionLog(Plane a, Plane b, int n)
	{
		redirectionLog.add(b.getFlightDetails() + " was redirected to " + n + "ft cruising altitude to avoid " + a.getFlightDetails()+ " at " + tm.getCurrentTime());
	}
	
	public ArrayList<String> getRedirectionLog()
	{
		return redirectionLog;
	}
	
	public void addTimer(TimerDisplay t)
	{
		tm = t;
	}
		
}
