package airTrafficControl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class Plane
{
	private int flightNumber, altitude,defaultAltitude, corridorNumber,time=0,flashTime=0;
	private int w ;
	private int h ;
	double degrees;
	private double ULCx, ULCy;
	private double Vx,Vy,mag,speed = .45;
	private String airline; 
	private Airport startAirport, endAirport;
     private Random rng;
     private boolean reachedDest,ordRefuel,dfwRefuel, changed, go, takenOff;
     private BufferedImage image;
     private Corridor [] corridors;
     private boolean redirecting,done,completeMsg;
     private Airport [] airports;
     private FlightLog log;
	public Plane(String al, Airport a, Airport b, int alt, Corridor[] c, Airport [] ap, FlightLog log)
	{
		rng = new Random();
		setAirline(al);	
		setStartingAirport(a);
		setEndingAirport(b);
		airports = ap;
		this.log = log;
		setAltitude(alt);
		setCorridorNum();
		setFlightNumber();
		setCurrentPosition();
		setDirection();
		defaultAltitude = alt;
	}
	
	public void setAirline(String al)
	{
		airline=al;
	}
	
	public String getAirline()
	{
		return airline;//return airline of plane
	}
	
	public void setAltitude(int n)
	{
		altitude = n;// set altitude of plane
	}
	
	public int getAltitude()
	{
		return altitude;//return plane's altitude
	}
	
	public void setCorridorNum()
	{
		corridorNumber = findCorridor(); //find the corridor number based off the starting and ending airport			
	}
	
	public int getCorridorNum()
	{
		return corridorNumber; //return corridor number of plane
	
	}
	public void setInCorridor(Corridor []c)
	{
		corridors = c;
		for(int i = 1; i <= c.length; i++)
		{
			if(c[i-1].getCorridorNumber() == this.getCorridorNum())
			{
				c[i-1].addPlane(this);
			}
		}
	}
	public int findCorridor()
	{
		if(getStartingAirport().getAirportName().equals("LAX") && getEndingAirport().getAirportName().equals("JFK"))
		{
			
			ordRefuel = true;
			return 4;
		}
		else if(getStartingAirport().getAirportName().equals("LAX") && getEndingAirport().getAirportName().equals("ORD"))
		{
			
			return 4;
		}
		else if(getStartingAirport().getAirportName().equals("LAX") && getEndingAirport().getAirportName().equals("DFW"))
		{
			
			return 3;
		}
		else if(getStartingAirport().getAirportName().equals("LAX") && getEndingAirport().getAirportName().equals("MIA"))
		{
			dfwRefuel = true;
			return 3;
		}
		else if(getStartingAirport().getAirportName().equals("JFK") && getEndingAirport().getAirportName().equals("LAX"))
		{
			
			ordRefuel = true;
			return 5;
		}
		else if(getStartingAirport().getAirportName().equals("JFK") && getEndingAirport().getAirportName().equals("ORD"))
		{
			
			return 5;
		}
		else if(getStartingAirport().getAirportName().equals("JFK") && getEndingAirport().getAirportName().equals("MIA"))
		{
			
			return 1;
		}
		else if(getStartingAirport().getAirportName().equals("JFK") && getEndingAirport().getAirportName().equals("DFW"))
		{
			
			ordRefuel = true;
			return 5;
		}
		else if(getStartingAirport().getAirportName().equals("ORD") && getEndingAirport().getAirportName().equals("LAX"))
		{
			
			return 4;
		}
		else if(getStartingAirport().getAirportName().equals("ORD") && getEndingAirport().getAirportName().equals("JFK"))
		{
			
			return 5;
		}
		else if(getStartingAirport().getAirportName().equals("ORD") && getEndingAirport().getAirportName().equals("DFW"))
		{
			
			return 7;
		}
		else if(getStartingAirport().getAirportName().equals("ORD") && getEndingAirport().getAirportName().equals("MIA"))
		{
			
			return 6;
		}
		else if(getStartingAirport().getAirportName().equals("DFW") && getEndingAirport().getAirportName().equals("LAX"))
		{
			
			return 3;
		}
		else if(getStartingAirport().getAirportName().equals("DFW") && getEndingAirport().getAirportName().equals("JFK"))
		{
			
			ordRefuel = true;
			return 7;
		}
		else if(getStartingAirport().getAirportName().equals("DFW") && getEndingAirport().getAirportName().equals("ORD"))
		{
			
			return 7;
		}
		else if(getStartingAirport().getAirportName().equals("DFW") && getEndingAirport().getAirportName().equals("MIA"))
		{
			
			return 2;
		}
		else if(getStartingAirport().getAirportName().equals("MIA") && getEndingAirport().getAirportName().equals("LAX"))
		{
			dfwRefuel = true;
			return 2;
		}
		else if(getStartingAirport().getAirportName().equals("MIA") && getEndingAirport().getAirportName().equals("JFK"))
		{
			
			return 1;
		}
		else if(getStartingAirport().getAirportName().equals("MIA") && getEndingAirport().getAirportName().equals("ORD"))
		{
			
			return 6;
		}
		else if(getStartingAirport().getAirportName().equals("MIA") && getEndingAirport().getAirportName().equals("DFW"))
		{
			
			return 2;
		}
		
		return 0;
	}
	
	public void changeCorridor()
	{
		if(isORDRefuel())
		{
			if(corridorNumber == 4)
			{
				corridors[corridorNumber-1].removePlane(this);
				corridorNumber = 5;
			}
			else if(corridorNumber == 5)
			{
				corridors[corridorNumber-1].removePlane(this);
				if(getEndingAirport().getAirportName().equals("LAX"))
				{
					corridorNumber = 4;
				}
				else if(getEndingAirport().getAirportName().equals("DFW"))
				{
					corridorNumber = 7;
				}
			}
			else if(corridorNumber == 7)
			{
				corridors[corridorNumber-1].removePlane(this);
				corridorNumber = 5;
			}
				changed=true;
		}
		if(isDFWRefuel())
		{
			if(corridorNumber == 3)
			{
				corridors[corridorNumber-1].removePlane(this);
				corridorNumber = 2;
			}
			else if(corridorNumber == 2)
			{
				corridors[corridorNumber-1].removePlane(this);
				corridorNumber = 3;
			}
				changed=true;
		}
	}

	public boolean isORDRefuel()
	{
		return ordRefuel;
	}
	
	public boolean isDFWRefuel()
	{
		return dfwRefuel;
	}
	public void setFlightNumber()
	{
		//generate a random flight number, int randomNum =  rn.nextInt(range) + minimum;
		flightNumber = rng.nextInt(901)+100;	
	}
	
	public int getFlightNumber()
	{
		//get the plane's flight number
		return flightNumber;
	}
	
	public void setStartingAirport(Airport n)
	{
		startAirport=n; // set the plane's starting airport
	}
	
	public Airport getStartingAirport()
	{
		return startAirport; //return the plane's starting airport
	}
	
	public void setEndingAirport(Airport n)
	{
		endAirport=n; // set the plane's ending airport
	}
	
	public Airport getEndingAirport()
	{
		return endAirport; //return the plane's ending airport
	}
	
	public void takenOff()
	{
		takenOff = true;
	}
	public boolean hasTakenOff()
	{
		return takenOff;
	}
	public boolean hasReachedDestination()
	{
		return reachedDest;
	}
	
	public boolean hasRefueled()//check whether plane has reached middle airport,
	{
		double x1 = getULCx();
		double y1 = getULCy();
		double x2 = 0;
		double y2 = 0;
		if(isORDRefuel())
		{
			x2 = airports[2].getULCx();
			y2 = airports[2].getULCy();
		}
		else if(isDFWRefuel())
		{
			x2 = airports[3].getULCx();
			y2 = airports[3].getULCy();
		}
		int distance = (int)Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		if(distance < 5 && !hasReachedDestination())
		{
			if (isDFWRefuel() || isORDRefuel())
			{
				return true;
			}
		}
		
		return false;
	}
	public boolean hasContinued()
	{
		return go;
	}
	public void moveTo(Airport n)
	{		
		n.addPlane(this);	
	}
	
	
	public double getULCx()
	{
		return ULCx;
	}
	
	public double getULCy()
	{
		return ULCy;
	}
	public String toString()
	{
		// print a string representation of plane's attributes
		return getAirline()+ " Flight " +getFlightNumber();
	}
	public BufferedImage getImage()
	{
		return image;
	}
	public String getFlightDetails()
	{
		String air = "";
		if(getAirline().equals("American Airlines"))
		{
			air = "AA";
		}
		else if(getAirline().equals("Southwest"))
		{
			air = "SW";
		}
		else if(getAirline().equals("United"))
		{
			air = "UD";
		}
		else if(getAirline().equals("Delta"))
		{
			air = "DT";
		}
		
		return air+ " Flight " + getFlightNumber();
	}
	public void setCurrentPosition()
	{
		if(getStartingAirport().getAirportName().equals("LAX"))
		{
			ULCx=70; //78
			ULCy=330; //65
		}
		else if(getStartingAirport().getAirportName().equals("JFK"))
		{
			ULCx=598; //578
			ULCy=235; //65
		}
		else if(getStartingAirport().getAirportName().equals("ORD"))
		{
			ULCx=443;
			ULCy=230;
		}
		else if(getStartingAirport().getAirportName().equals("DFW"))
		{
			ULCx=358;
			ULCy=390;
		}
		else if(getStartingAirport().getAirportName().equals("MIA"))
		{
			ULCx=578;
			ULCy=460;
		}
		
	}
	
	public boolean inProximity()
	{
		double x1 = getULCx();
		double y1 = getULCy();
		double x2 = getEndingAirport().getULCx();
		double y2 = getEndingAirport().getULCy();
		
		int distance = (int)Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		if(distance < 5)
		{
			return true;
		}
		return false;
	}
	
	public void redirectingFlash()
	{
		redirecting = true;
	}
	
	public void redirectingFlashOff()
	{
		redirecting = false;
	}
	
	public boolean isRedirecting()
	{
		return redirecting;
	}
	public void paintPlane(Graphics g)
	{
		if(hasRefueled() && !go)
		{
			time+=30;
			
			g.drawImage(image,(int)ULCx-w,(int)ULCy-h,null);
			refuelMsg();
			changeCorridor();
			setDirection();
			if(time > 3000)
			{
				corridors[corridorNumber-1].addPlane(this);
				go = true;
			}
		}
		else if(!changed || go)
		{
			increment();
			if(inProximity())
			{
				reachedDest=true;
			}
			if(!isRedirecting())
			{
				
				g.drawImage(image,(int)ULCx-w,(int)ULCy-h,null);
			}
			if(isRedirecting())
			{
				if(altitude == 30000)
				{
					g.drawImage(image,(int)ULCx-w,(int)ULCy-h,image.getWidth()-8, image.getHeight()-9,null);
				}
				else
				{
					g.drawImage(image,(int)ULCx-w,(int)ULCy-h,image.getWidth()+8, image.getHeight()+9,null);
				}
				
				if(flashTime > 3500)
				{
					flashTime = 0;
					redirectingFlashOff();
					resetAltitude();
				}
			}
		}
		
	}
	public void setImage()
	    {
	        try
	        {
	     		image = ImageIO.read(getClass().getResource("/airTrafficControlGUI/plane.png"));
	     		w = image.getWidth()/2;
	 	     	h = image.getHeight()/2;
	 	          AffineTransform tx = new AffineTransform();	            
	 	          tx.rotate(Math.toRadians(degrees+90), w, h);            
	 	          AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
	 	          image = op.filter(image, null);
	 	          
	        }
	        
	        catch(Exception e)
	        {
	            
	        }

	    }
	public void increment()
	{
		ULCx += Vx;
		ULCy += Vy;
		if(isRedirecting())
		{
			flashTime+=30;
		}
		else
		{
			flashTime = 0;
		}
	}
	
	public void setDirection()
	{
		
		if(isORDRefuel() && !hasRefueled())
		{
			Vx = (airports[2].getULCx()-ULCx);
			Vy = (airports[2].getULCy()-ULCy);
			degrees = Math.toDegrees(Math.atan2(airports[2].getULCy() - getULCy(), airports[2].getULCx() - getULCx()));
		}
		else if( isDFWRefuel() && !hasRefueled())
		{
			Vx = (airports[3].getULCx()-ULCx);
			Vy = (airports[3].getULCy()-ULCy);
			degrees = Math.toDegrees(Math.atan2(airports[3].getULCy() - getULCy(), airports[3].getULCx() - getULCx()));
		}
		else
		{
			Vx = (getEndingAirport().getULCx()-ULCx);
			Vy = (getEndingAirport().getULCy()-ULCy);
			degrees = Math.toDegrees(Math.atan2(getEndingAirport().getULCy() - getULCy(), getEndingAirport().getULCx() - getULCx()));
		}
		mag = Math.sqrt((Vx*Vx)+(Vy*Vy));
		Vx = Vx*speed/mag;
		Vy = Vy*speed/mag;
		setImage();
	}
	
	public void removeSelf()
	{
		corridors[corridorNumber-1].removePlane(this);
	}
	
	public void resetAltitude()
	{
		setAltitude(defaultAltitude);
	}
	
	public boolean isDone()
	{
		return done;
	}
	
	
	public void refuelMsg()
	{
		if(!completeMsg)
		{
			if(hasRefueled() && isORDRefuel())
			{
				moveTo(airports[2]);
				log.setFlightLogs();
				airports[2].removePlane(this);
			}
		else if(hasRefueled() && isDFWRefuel())
		{
			moveTo(airports[3]);
			log.setFlightLogs();
			airports[3].removePlane(this);
		}
			completeMsg = true;
		}
	}
}
