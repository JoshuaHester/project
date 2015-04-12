/*
Nikki Pruitt
Joshua Hester
*/

import java.io.*;

public class Generator {
	
	public enum Order {FCFS, SJF};			        //order for sorting - First Come First Serve and Shortest Job First 
	public enum Allocation {FIRST_FIT, BEST_FIT};  //allocation policies - First Fit and Best Fit
	
	private PrintWriter outputFile;		//output file for results
	private Order order;					//order used to pull jobs into ready queue
	private Allocation allocation;		//allocation used to put jobs into segments
	private int runningJobs;				//number of jobs currently running
	private int finishedJobs;				//number of jobs completed
	
	public Generator(Order order, Allocation alloc) {
		this.order = order;
		this.allocation = alloc;
	}
	
	
}