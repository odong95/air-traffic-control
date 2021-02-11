package airTrafficControlGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import airTrafficControl.AirTrafficControl;
import airTrafficControl.Airport;
import airTrafficControl.Corridor;
import airTrafficControl.FlightLog;
import airTrafficControl.Plane;

public class Window extends JFrame implements ActionListener
{
	private JFrame window;
	private JPanel mainPanel;
	private JPanel sidePanel;
	private Canvas canvas;
	private ATCSPanel atcsP;
	private FlightLogPanel flog;
	private JButton addFlightButton;
	private AirTrafficControl atcs;
	private FlightLog flightLog;
	private Corridor [] corridors;
	private Airport [] airports;
	private TimerDisplay timerDisplay;
	public Window(int wid, int hei)
	{
		setup();
		window.getContentPane().add(addFlightButton);
		window.getContentPane().add(mainPanel);
		initializeWindow(wid,hei);
		
	}
	
	public void initializeWindow(int w, int h)
	{
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(w,h);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		run();
	}
	
	public void setup()
	{
		window = new JFrame("Air Traffic Control Simulator");
		airports = new Airport[5];
		airports[0] = new Airport("LAX");
		airports[1] = new Airport("JFK");
		airports[2] = new Airport("ORD");
		airports[3] = new Airport("DFW");
		airports[4] = new Airport("MIA");
		corridors = new Corridor[7];
		for(int i=1; i <= 7; i++)
		{
			corridors[i-1] = new Corridor(i);
		}
		
		flightLog = new FlightLog(airports);
		atcs = new AirTrafficControl(corridors);
		
		mainPanel = new JPanel();
		sidePanel = new JPanel();

		timerDisplay = new TimerDisplay();
		addFlightButton = new JButton("Add Flight");
		canvas = new Canvas(670,530);
		
		canvas.addTimerDisplay(timerDisplay);
		canvas.addCorridors(corridors);
		canvas.addLog(flightLog);
		canvas.addAirports(airports);
		atcs.addTimer(timerDisplay);
		for(Airport a: airports)
		{
			a.addTimer(timerDisplay);
		}
		atcsP = new ATCSPanel(atcs);
		flog = new FlightLogPanel(flightLog);
		setMainPanel();
			
		addFlightButton.setBounds(270, 580, 100, 30);
		addFlightButton.addActionListener(this);
		
	}
	public void run()
	{
		Timer t = new Timer(5,this);	
		t.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == addFlightButton)
		{
			//timerDisplay.pause();
			//canvas.pause();
			userInputDialog();
			//timerDisplay.run();
			//canvas.run();
		}
		update();
		
	}
	
	public void setSidePanel()
	{
		GroupLayout layout1 = new GroupLayout(sidePanel);
		sidePanel.setLayout(layout1);
		ParallelGroup g = layout1.createParallelGroup(Alignment.CENTER);
		g.addComponent(atcsP,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
     	          GroupLayout.PREFERRED_SIZE)
          .addComponent(flog,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
     	          GroupLayout.PREFERRED_SIZE);
		SequentialGroup hGroup1 = layout1.createSequentialGroup();
			hGroup1.addGroup(g);
		layout1.setHorizontalGroup(hGroup1);
		SequentialGroup vGroup1 = layout1.createSequentialGroup().addGap(30);
			vGroup1.addComponent(atcsP,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
			          GroupLayout.PREFERRED_SIZE).addGap(60).addComponent(flog,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
					          GroupLayout.PREFERRED_SIZE);
		layout1.setVerticalGroup(vGroup1);
	}
	
	public void setMainPanel()
	{
		setSidePanel();
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		SequentialGroup hGroup = layout.createSequentialGroup();
		     hGroup.addComponent(canvas,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				          GroupLayout.PREFERRED_SIZE).addGap(30);      
			hGroup.addComponent(sidePanel,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
					          GroupLayout.PREFERRED_SIZE);
		
			layout.setHorizontalGroup(hGroup);
			
		
		SequentialGroup vGroup = layout.createSequentialGroup();
				   vGroup.addGroup(layout.createParallelGroup(Alignment.LEADING)
				           .addComponent(canvas,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				          	          GroupLayout.PREFERRED_SIZE)
				           .addComponent(sidePanel,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
					          GroupLayout.PREFERRED_SIZE).addGap(50));
		
		layout.setVerticalGroup(vGroup);
		layout.setAutoCreateContainerGaps(true);

	}
	
	public void update() // for simulator updates
	{
		atcs.handleCollisions();
		flog.run();
		
		if(atcs.madeRedirection())
		{
			atcsP.update();
		}
		
	}
	
	public void userInputDialog()
	{
		 JFrame frame = new JFrame("Adding Flight");
		 String [] airlines = {"American Airlines", "United", "Delta", "Southwest"};
		 String [] aports = {"LAX","JFK","ORD","DFW","MIA"};
		 Integer [] altitudes = {30000,35000,40000};
		 Object al = JOptionPane.showInputDialog(frame, "Select an Airline", "Flight Details", JOptionPane.QUESTION_MESSAGE,null, airlines,airlines[0]);
		 if(al != null)
		 {
			 Object start = JOptionPane.showInputDialog(frame, "Select a Starting Airport", "Flight Details", JOptionPane.QUESTION_MESSAGE,null, aports,aports[0]);
		if(start!= null)
		{
		 	
			 String [] aports2 = new String[4];
		 	
		 int d = 0;
		 for(int i = 0; i < 5; i++)
		 {
			if(start.equals(aports[i]))
			 {
				d = i;
			 }
		 }
		 for(int j = 0; j < 4; j++)
		 {
			 if(j >= d)
			 {
				 aports2[j] = aports[j+1];
			 }
			 else
			 {
				 aports2[j] = aports[j];
			 }
		 }
		 Object end = JOptionPane.showInputDialog(frame, "Select an Ending Airport", "Flight Details", JOptionPane.QUESTION_MESSAGE,null, aports2,aports2[0]);
		 if( end != null)
		 {
		 Object alt = JOptionPane.showInputDialog(frame, "Select Cruising Altitude", "Flight Details", JOptionPane.QUESTION_MESSAGE,null, altitudes,altitudes[0]);
		 if(al != null && start != null && end != null && alt != null )
		 {
		 int i = 0;
		 int j = 0;
		 String n = (String)start;
			 if(n.equals("LAX"))
			 {
				 i = 0;			 
			 }
			 if(n.equals("JFK"))
			 {
				 i = 1;			 
			 }
			 if(n.equals("ORD"))
			 {
				 i = 2;			 
			 }
			 if(n.equals("DFW"))
			 {
				 i = 3;			 
			 }
			 if(n.equals("MIA"))
			 {
				 i = 4;
			 }
		 n = (String)end;
			 if(n.equals("LAX"))
			 {
				 j = 0;			 
			 }
			 if(n.equals("JFK"))
			 {
				 j = 1;			 
			 }
			 if(n.equals("ORD"))
			 {
				 j = 2;			 
			 }
			 if(n.equals("DFW"))
			 {
				 j = 3;			 
			 }
			 if(n.equals("MIA"))
			 {
				 j = 4;
			 }
		 
			 Plane p = new Plane((String)al,airports[i],airports[j],(Integer)alt,corridors,airports,flightLog);
			 if(!corridors[p.getCorridorNum()-1].isFull())
			 {
				 canvas.addPlane(p);
				 p.moveTo(p.getStartingAirport());
				 flightLog.setFlightLogs();
				 p.takenOff();
				 p.getStartingAirport().removePlane(p);
			 }
			 else
			 {
				 JOptionPane.showMessageDialog(frame, "The flight corridor from " + p.getStartingAirport().getAirportName() + " to " + p.getEndingAirport().getAirportName() 
						 + " is currently busy. (2 max planes)\n" + "Please try again when it's cleared.","Flight Delayed", JOptionPane.WARNING_MESSAGE);
			 }
		 }
		 }
		}
	}
	}
	
	public AirTrafficControl getATCS()
	{
		return atcs;
	}
}
