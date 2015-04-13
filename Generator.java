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
	private int timer;					//how much time has passed
	private Job[] jobs;					//list of jobs 
	private Segments[] segments;			//list of segments
	
	public Generator(Memory mem, Order order, Allocation alloc) {
		this.order = order;
		this.allocation = alloc;
		jobs = mem.getJobs();
		segments = mem.getSegments();
		createFiles();
	}
	
	//creates output files for each test case
	public void createFiles() {
		try {
			if(order == Order.FCFS && allocation == Allocation.FIRST_FIT){
				outputFile = new PrintWriter(new File("TestCaseOne.txt"));
			}
			else if(order == Order.FCFS && allocation == Allocation.BEST_FIT) {
				outputFile = new PrintWriter(new File("TestCaseTwo.txt"));
			}
			else if(order == Order.SJF && allocation == Allocation.BEST_FIT) {
				outputFile = new PrintWriter(new File("TestCaseThree.txt"));
			}
		} catch (FileNotFoundException e) {}
	}
	
	public void finishedJobs() {
		
	}
	
	public void waitingJobs() {
		
	}
	
	public void totalWaste() {
		
	}
}