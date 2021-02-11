package airTrafficControlGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;

import airTrafficControl.Airport;
import airTrafficControl.Corridor;
import airTrafficControl.FlightLog;
import airTrafficControl.Plane;

public class Canvas extends JPanel implements ActionListener
{
	private Timer tm;
	private TimerDisplay timerDisplay;
	private ArrayList<Plane> planes;
	private Corridor [] corridors;
	private Airport [] airports;
	private boolean blinking;
	private int k = 0, duration = 0;
	private URL path;
	private Clip clip;
	private FlightLog log;
	private Font font = new Font("Tahoma", Font.PLAIN, 18);
	private BufferedImage bg,aport;
	public Canvas(int wid, int hei)
	{
		this.setPreferredSize(new Dimension(wid, hei));
		setBG();
		planes = new ArrayList<Plane>();
		tm = new Timer(30,this);	
		getBeepSound();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		playBeep();
		
		if(blinking)
		{
			if(duration <= 2750)
			{
				k+=30;
			}
			duration+=30;
			if(duration >= 2750)
			{
				blinking = false;
				duration = 0;
			}
		}
		removePlane();
		repaint();
	}
	
	@Override 
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		tm.start();
		g.drawImage(bg,0,100,null);
		g.setColor(Color.LIGHT_GRAY);
		g.drawImage(aport,56, 307, null); // 5,-13
		g.drawImage(aport,584, 212, null);
		g.drawImage(aport,430, 207, null);
		g.drawImage(aport,344, 367, null);
		g.drawImage(aport,564, 437, null);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.BOLD, 12));
		g.drawString("LAX", 65, 337); //add 11, add 17
		g.drawString("JFK", 593, 242);
		g.drawString("ORD", 437, 237);
		g.drawString("DFW", 351, 397);
		g.drawString("MIA", 573, 467);
		
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(timerDisplay.getCurrentTime(),580 ,90);
		g.drawOval(295, 500, 25, 25);
		
		
		if(planes.size()>0)
		{
			for(Plane p: planes)
			{
				if(!p.hasReachedDestination())
				{
					p.paintPlane(g);
				}
			}
		}
		for(Corridor c: corridors)
		{
			if(c.hasPossibleCollision())
			{
				blinking = true;
			}
		}
		
		if(blinking)
		{
			if((k < 500))
			{
				g.setColor(Color.RED);
				g.fillOval(295, 500, 25, 25);
			}
			else if(k >= 500 && k < 1000)
			{
				g.setColor(Color.BLACK);
				g.drawOval(295, 500, 25, 25);		
			}
			else if(k >= 1000)
			{
				k = 0;
			}
		}
		
	}
	
	public void getBeepSound()
	{     
	        try
	        {
	            path = getClass().getResource("/airTrafficControlGUI/beep.wav");
	            AudioInputStream stream = AudioSystem.getAudioInputStream(path);
	            clip = AudioSystem.getClip();
	            clip.open(stream);            
	        }
	        catch(Exception e)
	        {
	            
	        }
	}
	public void playBeep()
	{	
		if(blinking)
		{
	        clip.start();
	        clip.loop(clip.LOOP_CONTINUOUSLY);
		}
		else if(!blinking)
		{
			clip.stop();
			clip.setFramePosition(0);
		}	
	}
	 
	public ArrayList<Plane> getPlanes()
	{
		return planes;
	}
	
	public void addPlane(Plane p)
	{
		planes.add(p);
		p.setInCorridor(corridors);
	}
	
	public void addCorridors(Corridor [] c)
	{
		corridors = c;
	}
		
	
	public void addTimerDisplay(TimerDisplay d)
	{
		timerDisplay =d;
	}
	
	public void addAirports(Airport [] air)
	{
		airports = air;
	}
	

	public void removePlane()
	{
		Iterator<Plane> it = planes.iterator();
		while (it.hasNext()) 
		{
			Plane pl = it.next();
			if (pl.hasReachedDestination()) 
			{
				pl.moveTo(pl.getEndingAirport());
				log.setFlightLogs();
				pl.getEndingAirport().removePlane(pl);
				pl.removeSelf();
				it.remove();			
			}

		}

	}
	
	public void addLog(FlightLog l)
	{
		log = l;
	}
	public void pause()
	{
		tm.stop();
	}
	
	public void run()
	{
		tm.start();
	}
	
	public void setBG()
	{
		try
	        {
			bg = ImageIO.read(getClass().getResource("/airTrafficControlGUI/USMap.jpg"));
			aport = ImageIO.read(getClass().getResource("/airTrafficControlGUI/airport.png"));
	        }
	        
	        catch(Exception e)
	        {
	            
	        }
	}
}
