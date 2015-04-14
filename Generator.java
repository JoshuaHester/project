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
		timer = 0;
		jobs = mem.getJobs();
		segments = mem.getSegments();
		createFile();
		sortJobs(order);
	}
	
	//test run to see if statuses update/allocation methods work
	public void test() {
		for(int i = 0; i < jobs.length; i++) {
			if(allocation == Allocation.FIRST_FIT) {
				int fit = firstFit(jobs[i]);
				//System.out.println("Job " + i + " first fit is " + fit);
				if(fit != -1 && !segments[fit].isOccupied()) {
					segments[fit].assignJob(jobs[i]);
				}
			}
			else if(allocation == Allocation.BEST_FIT) {
				int fit = bestFit(jobs[i]);
				//System.out.println("Job " + i + " best fit is " + fit); 
				if(fit != -1 && !segments[fit].isOccupied()) {
					segments[fit].assignJob(jobs[i]);
				}
			}
		}
		
	/*	for(int i = 0; i < segments.length; i++){
			if(segments[i].isOccupied()) {
				segments[i].removeJob();
			}
		}*/
	}
	
	//checks to see if jobs need to be sorted
	public void sortJobs(Order ord) {
		if(ord == Order.SJF) {
			shortestJobFirst();
		}
		//printJobs();
	}
	
	//sorts the job into shortest job first orde
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
	
	//puts job into the segment that it best fits in
	public int bestFit(Job job) {
		int initalBestFit = 100;	//value set higher than possible waste 
		int index = -1;			//returns this is no free segment has enough free space
		for(int i = 0; i < segments.length; i++) {
			int currentBestFit = segments[i].getSize() - job.getMemoryRequest();
			if(segments[i].getSize() > job.getMemoryRequest() && !segments[i].isOccupied()) {
				if(currentBestFit < initalBestFit) {
					initalBestFit = currentBestFit;
					index = i;
				}
			}
		}
		
		return index;
	}
	
	//prints the jobs; used for testing
	public void printJobs() {
		for(int i = 0; i < jobs.length; i++) {
			System.out.println(jobs[i].getID() + " " + jobs[i].getTimeRequest());
		}
	}
	
	//creates output files for each test case
	public void createFile() {
		try{
			if(order == Order.FCFS && allocation == Allocation.FIRST_FIT) {
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
		if(timer == 0) {
			String header = "TIME" + "\tID" + "\tSEGMENT" + "\tMEM REQUEST" + "\tTIME REMAINING" + "\tMESSAGES";
			System.out.println(header);
			outputFile.println(header);
		}
		
		for(int i = 0; i < jobs.length; i++) {
			String output = timer + "\t" + jobs[i].getID() + "\t" + jobs[i].getSegmentNumber() + "\t" + jobs[i].getMemoryRequest() + "\t" + jobs[i].getTimeRemaining() + "\t" + jobs[i].getStatus();
			System.out.println(output);
			outputFile.println(output);
		}
					
		String wasted = "Wasted Space: " + totalWaste() + " MB";
		String waiting = "Jobs Waiting: " + waitingJobs();
		System.out.println(wasted);
		System.out.println(waiting);
		System.out.println();
		outputFile.println(wasted);
		outputFile.println(waiting);
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
	
	public int[] nextSegments(int[] segment) {//4 segments are supposed to execute simultaneously, "Segments" are the segment numbers to be executing this round. If [2,3,4,5] is passed in, [6,7,8,9] should be returned.

		//return portion. This does not currently account for a segment not running due to having nothing to run. in those cases, the segment number for each subsequent segment should be incremented
		int[] returns = new int[4];
		boolean[] open = new boolean[10];
			for(int i = 0; i<10; i++){
				open[i] = !segments[i].isOccupied();
			}
			boolean flag = true;
			//step 1 (+4)
			int[] temp = {segment[0]+4,segment[1]+4,segment[2]+4,segment[3]+4}; //0,1,2,3
			for(int i=0;i<4;i++){
				//step 2 (WRAP)
				int diff = temp[i] - 10;
				if(diff>=0){
					temp[i]=diff;
				}
			}
			//step 3 (is empty? if yes, add and wrap when needed)
			for(int j=0;j<10;j++) {
				
				
				
			}
			
		/*	
		for(int z=0;z<10;z++) {
			for(int i=0; i<4; i++){
				while(!segments[i+1].isOccupied()){
					for(i=i;i<4;i++){
						int temp = segment[i]+1;
						
					}
				}
				
				
				int temp = segment[i]+4;
				int diff = temp - 10;
				if(diff>=0){
					returns[i] = diff;
				}
				else {
					returns[i] = temp;
				}
				
			*/
			}
			System.out.println(returns[0]+returns[1]+returns[2]+returns[3]);
		}
		
		return returns; // [6,7,8,9] --> [0,1,2,3] \, &  [0,1,2,3] --> [0,3,4,5] --> [0,3,5,6] & [6,7,8,9] --> [6,9,0,3]
	}
	
	public void execute() {
		int[] segs = {6,7,8,9};
		//allocation of segments
		while(timer<=30) {
			for(int i = 0; i < segments.length; i++) {
				if(allocation == Allocation.FIRST_FIT) {
					int fit = firstFit(jobs[i]);
					if(fit != -1 && !segments[fit].isOccupied()) {
						segments[fit].assignJob(jobs[i]);
					}
				}
				else if(allocation == Allocation.BEST_FIT) {
					int fit = bestFit(jobs[i]);
					if(fit != -1 && !segments[fit].isOccupied()) {
						segments[fit].assignJob(jobs[i]);
					}
				}
			}
			segs = nextSegments(segs);
			writeOutput();
			for(int i=0;i<4;i++) {
				segments[segs[i]].getJob().decrementTime();
				segments[segs[i]].getJob().setStatus("running");
				if(segments[segs[i]].getJob().getTimeRemaining()==0) {
					segments[segs[i]].getJob().setStatus("finish");
					segments[segs[i]].removeJob();
				}
			}
			timer++;
			writeOutput();
			
			
		}
	}
	
	
	
}
