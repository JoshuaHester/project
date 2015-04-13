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
	
	//constructor for the generator class
	public Generator(Memory mem, Order order, Allocation alloc) {
		this.order = order;
		this.allocation = alloc;
		jobs = mem.getJobs();
		segments = mem.getSegments();
		createFile();
		sortJobs(order);
	}
	
	//checks to see if jobs need to be sorted
	public void sortJobs(Order ord) {
		if(ord == Order.SJF) {
			shortestJobFirst();
		}
		//printJobs();
	}
	
	//sorts the job into shortest job first order
	public void shortestJobFirst() {
		boolean flag = true;
		while(flag) {
			flag = false;
			for(int i = 0; i < 19; i++) {
				if(jobs[i].getTimeRemaining() > jobs[i+1].getTimeRemaining()) {
					Job temp = jobs[i];
					jobs[i] = jobs[i+1];
					jobs[i+1] = temp;
					flag = true;
				}
			}
		}
	}
	
	//puts job into the first segment it fits in
	public int firstFit(Job job) {
		for(int i = 0; i < segments.length; i++) {
			if(job.getMemoryRequest() < segments[i].getSize() && !segments[i].isOccupied()) {
				return i;
			}
		}
		return -1;	//returns this if no free segment has enough memory
	}
	
	/*public int bestFit(Job job) {
		
	}*/
	
	//prints the jobs; used for testing
	public void printJobs() {
		for(int i = 0; i < jobs.length; i++) {
			System.out.println(jobs[i].getID() + " " + jobs[i].getTimeRequest());
		}
	}
	
	//creates output files for each test case
	public void createFile() {
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
	
	//writes the results of each time unit to the screen and output file
	public void writeOutput() {
		System.out.println("TIME" + "\tID" + "\tSEGMENT" + "\tMEM REQUEST" + "\tTIME REMAINING" + "\tMESSAGES");
		outputFile.println("TIME" + "\tID" + "\tSEGMENT" + "\tMEM REQUEST" + "\tTIME REMAINING" + "\tMESSAGES");
		
		for(int i = 0; i < jobs.length; i++) {
			System.out.println(timer + "\t" + jobs[i].getID() + "\t" + jobs[i].getSegmentNumber() + "\t" + jobs[i].getMemoryRequest() + "\t" + jobs[i].getTimeRemaining() + "\t" + jobs[i].getStatus());
			outputFile.println(timer + "\t" + jobs[i].getID() + "\t" + jobs[i].getSegmentNumber() + "\t" + jobs[i].getMemoryRequest() + "\t" + jobs[i].getTimeRemaining() + "\t" + jobs[i].getStatus());
		}
		
		System.out.println("Wasted Space: " + totalWaste() + " MB");
		System.out.println("Jobs Waiting: " + waitingJobs());
		System.out.println();
		outputFile.println("Wasted Space: " + totalWaste() + " MB");
		outputFile.println("Jobs Waiting: " + waitingJobs());
		outputFile.println();
	}
	
	//returns the number of finished jobs
	public int finishedJobs() {
		int finishedJobs = 0;
		for(int i = 0; i < jobs.length; i++) {
			if(jobs[i].getStatus().equals("finished")){
				finishedJobs++;
			}
		}
		return finishedJobs;
	}
	
	//returns the number of waiting jobs
	public int waitingJobs() {
		int waitingJobs = 0;
		for(int i = 0; i < jobs.length; i++) {
			if(jobs[i].getStatus().equals("waiting")) {
				waitingJobs++;
			}
		}
		return waitingJobs;
	}
	
	//returns the total amount of wasted space
	public int totalWaste() {
		int wastedSpace = 0;
		for(int i = 0; i < segments.length; i++) {
			if(!segments[i].isOccupied()) {
				wastedSpace = wastedSpace + segments[i].getSize();
			}
			else {
				wastedSpace = wastedSpace + segments[i].getWastedSpace();
			}
		}
		return wastedSpace;
	}
	
	//checks to see if all jobs are finished
	public boolean allJobsFinished() {
		for(int i = 0; i < jobs.length; i++) {
			if(!jobs[i].getStatus().equals("finished")) {
				return false;
			}
		}
		return true;
	}
}