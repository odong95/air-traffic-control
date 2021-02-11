package airTrafficControlGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import airTrafficControl.FlightLog;

public class FlightLogPanel extends JPanel implements ActionListener
{
	private JTextArea textArea;
	private JLabel flogLabel;
	private FlightLog log;
	private JComboBox box;
	private StringBuilder sb;
	private Timer tm;
	private JScrollPane scrollPane;
	public FlightLogPanel(FlightLog log)
	{
		this.setLayout(new BorderLayout());
		String [] ap = {"LAX", "JFK", "ORD","DFW","MIA"};
		this.box = new JComboBox(ap);
		box.setSelectedIndex(0);
		box.addActionListener(this);
		box.setBackground(Color.LIGHT_GRAY);
		this.log = log;
		flogLabel = new JLabel("Flight Log");
		flogLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textArea = new JTextArea(15, 35);
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea,scrollPane.VERTICAL_SCROLLBAR_ALWAYS,scrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		this.add(scrollPane,BorderLayout.SOUTH);
		this.add(box,BorderLayout.CENTER);
		this.add(flogLabel,BorderLayout.NORTH);
		tm = new Timer(5,this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		//when user selects an option
		//get the flight log based off input
		//display that airports log
		String x = box.getSelectedItem().toString();	
		displayLog(log.getFlightLog(x));

	}
	
	public void displayLog(ArrayList<String> log)
	{
		String st = "";
		sb = new StringBuilder(st);
		String s1 = "";
		textArea.setText(s1);
		for(int i = 0; i <log.size(); i++)
		{
			if(log.size() > 0 && log.get(i) != null)
			{
			
				st = log.get(i) + " \n";
				sb.insert(0,st);
				textArea.setText(s1);
				String s = sb.toString();
				textArea.append(s);
				textArea.setCaretPosition(0);
			}
		}
	}
	
	public void run()
	{
		tm.start();
	}
}
