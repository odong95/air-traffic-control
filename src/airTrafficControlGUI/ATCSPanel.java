package airTrafficControlGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import airTrafficControl.AirTrafficControl;

public class ATCSPanel extends JPanel
{
	private JTextArea textArea;
	private JLabel atcsLabel;
	private ArrayList<String>log;
	private AirTrafficControl atc;
	private String str;
	private StringBuilder sb;
	private JScrollPane scrollPane;
	public ATCSPanel(AirTrafficControl atc)
	{
		this.setLayout(new BorderLayout());
		str = "";
		sb = new StringBuilder(str);
		
		atcsLabel = new JLabel("Air Traffic Control Log");
		atcsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textArea = new JTextArea(15,45);
		textArea.setCaretPosition(0);
		scrollPane = new JScrollPane(textArea,scrollPane.VERTICAL_SCROLLBAR_ALWAYS,scrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		textArea.setEditable(false);

		this.atc = atc;
		this.add(scrollPane);	
		this.add(atcsLabel,BorderLayout.NORTH);
	}
	
	public void update()
	{
		this.log = atc.getRedirectionLog();
		setText();
		atc.reset();
	}
	
	public void setText()
	{
		if(log.size() > 0 && log.get(log.size()-1) != null)
		{
				str = log.get(log.size()-1) + " \n";
				sb.insert(0,str);
				String s = sb.toString();
				String s1 = "";
				textArea.setText(s1);
				textArea.append(s);
				textArea.setCaretPosition(0);
		}
	}
}
