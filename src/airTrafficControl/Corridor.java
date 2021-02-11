package airTrafficControl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Corridor
{
	private int number;
	private ArrayList<Plane>  planes;
	public Corridor(int n)
	{
		setCorridorNumber(n); //set corridor number
		planes = new ArrayList<Plane>();//create planes
	}
	
	public void setCorridorNumber(int n)
	{
		number = n;//set the number of the corridor
	}
	
	public int getCorridorNumber()
	{
		return number; //get the corridor number of the corridor
	}
	
	public void addPlane(Plane p)// add plane to the corridor
	{
		planes.add(p);
	}
	
	public boolean isFull()
	{
		return planes.size() >= 2;
	}

	// if planes has reached destination or refueled, remove it from the corridor
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
	
	public ArrayList<Plane> getPlanes()
	{
		return planes; //return the planes in the corridor
	}
	
	public boolean inProximity()
	{
		Plane a = getPlanes().get(0);
		Plane b = getPlanes().get(1);

		double x1 = a.getULCx();
		double y1 = a.getULCy();
		double x2 = b.getULCx();
		double y2 = b.getULCy();
		
		int distance = (int)Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		if(distance < 45 && (a.getAltitude() == b.getAltitude()) && (!a.getStartingAirport().getAirportName().equals(b.getStartingAirport().getAirportName())&&!a.getEndingAirport().getAirportName().equals(b.getEndingAirport().getAirportName())))
		{
			if(!b.isRedirecting())
			return true;
		}
		
		return false; 
	}
	
	public boolean hasPossibleCollision()
	{
		if(isFull())
		{
			if(inProximity())
			{
				return true;
			}
		}
		return false;
	}
}
