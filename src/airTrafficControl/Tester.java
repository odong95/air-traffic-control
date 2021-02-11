package airTrafficControl;

import java.util.Scanner;


public class Tester
{

	public static void main(String[] args)
	{
		/*Airport [] airports = new Airport[5];
		airports[0] = new Airport("LAX");
		airports[1] = new Airport("JFK");
		airports[2] = new Airport("ORD");
		airports[3] = new Airport("DFW");
		airports[4] = new Airport("MIA");
		
		Corridor [] corridors = new Corridor[8];
		
		for(int i=0; i < 8; i++)
		{
			corridors[i] = new Corridor(i);
		}
		
		FlightLog log = new FlightLog(airports);
		
		AirTrafficControl atcs = new AirTrafficControl(corridors);*/
		
		//Scanner scan = new Scanner(System.in);
		
		//String airline;
		//Airport startAirport,endAirport;
		//int altitude, corridorNumber;
		
		//int [] altArr = {30000,35000,40000};
		//String [] alarr = {"American Airlines", "United", "Delta", "Southwest"};
		
		/*
		boolean go = true;
		do
		{
			System.out.println("Choose Airline:");
			System.out.println("Type 0 for American Airlines");
			System.out.println("Type 1 for United");
			System.out.println("Type 2 for Delta");
			System.out.println("Type 3 for Southwest Airlines");
			airline = alarr[scan.nextInt()];
			System.out.println();
			System.out.println("Choose starting airport:");
			System.out.println("Type 0 for LAX, 1 for JFK, 2 for ORD, 3 for DFW, 4 for MIA");
			startAirport = airports[scan.nextInt()];
			System.out.println();
			System.out.println("Choose ending airport:");
			System.out.println("Type 0 for LAX, 1 for JFK, 2 for ORD, 3 for DFW, 4 for MIA");
			endAirport = airports[scan.nextInt()];
			System.out.println();
			System.out.println("Choose altitude: Type 0 for 30k, 1 for 35k, 2 for 40k");
			altitude = altArr[scan.nextInt()];
			System.out.println();
			
			Plane a = new Plane(airline,startAirport,endAirport,altitude);
			corridors[a.getCorridorNum()].addPlane(a);
			startAirport.addPlane(a);
			System.out.println(a.toString() + " is about to take off.");
			//System.out.println(a.getFlightDetails() + " is currently at " + a.getCurrentPosition().getAirportName());
			System.out.println("Corridor #" + corridors[a.getCorridorNum()].getCorridorNumber() + " now has " + a.getFlightDetails());
			a.moveTo(endAirport);
			if(a.hasRefueled())
			{
				System.out.println(a.getFlightDetails() + " has refueled");
			}
			
			//System.out.println(a.getFlightDetails() + " now moved to " + a.getCurrentPosition().getAirportName());
			System.out.println("Corridor #" + corridors[a.getCorridorNum()].getCorridorNumber() + " now has " + a.getFlightDetails());
			corridors[a.getCorridorNum()].removePlanes();
			if(corridors[a.getCorridorNum()].hasPlanes())
			{
				System.out.println("There is still a plane on corridor " + corridors[a.getCorridorNum()].getCorridorNumber());
			}
			else
			{
				System.out.println("There are no more planes on corridor " + corridors[a.getCorridorNum()].getCorridorNumber());
			}
			log.setFlightLogs();
			System.out.println("Now displaying flight logs:");
			//System.out.println(log.getFlightLog(log.getFlightLog(endAirport.getAirportName()).get(0)));
			System.out.println("Continue? Press 1 for yes, 2 for no");
			int n = scan.nextInt();
			if(n == 2)
			{
				go = false;
			}
		}
		while(go);*/
		
		/*Plane a = new Plane("United", airports[2],airports[0],35000);
		System.out.println(a.getFlightDetails() + " is going from " + a.getStartingAirport().getAirportName()+ " to " + a.getEndingAirport()
		.getAirportName());
		corridors[a.getCorridorNum()].addPlane(a);
		System.out.println("Corridor #" + corridors[a.getCorridorNum()].getCorridorNumber() + " has " + a.getFlightDetails());
		a.moveTo(a.getEndingAirport());
		corridors[a.getCorridorNum()].addPlane(a);
		System.out.println("Now, Corridor #" + corridors[a.getCorridorNum()].getCorridorNumber() + " has " + a.getFlightDetails());
		System.out.println("Has " + a.getFlightDetails() + " refueled? " + a.hasRefueled());
		System.out.println("Corridor number this flight was just at is " + a.getCorridorNum());
		System.out.println("Does " + a.getEndingAirport().getAirportName() + " have planes? " + a.getEndingAirport().hasPlanes());
		System.out.println(a.getFlightDetails() + " is currently at " + a.getCurrentPosition().getAirportName());*/
		
		
		
	}

}
