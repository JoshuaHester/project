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
	private int jobsRunning;				//number of jobs currently running
	private int jobsReady;				//number of jobs in ready queue
	private int jobsFinished;				//number of jobs finished
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
	
	//checks to see if jobs need to be sorted
	public void sortJobs(Order ord) {
		if(ord == Order.SJF) {
			shortestJobFirst();
		}
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
			System.out.println("Job ID: " + jobs[i].getID() + " Time: " + jobs[i].getTimeRequest() + " Memory: " + jobs[i].getMemoryRequest());
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
		String header = "TIME" + "\tID" + "\tSEGMENT" + "\tMEM REQ" + "\tTIME REMAINING" + "\tMESSAGES";
		System.out.println(header);
		outputFile.println(header);
		
		for(int i = 0; i < jobs.length; i++) {
			String output = timer + "\t" + jobs[i].getID() + "\t" + jobs[i].getSegmentNumber() + "\t" + jobs[i].getMemoryRequest() + "\t\t" + jobs[i].getTimeRemaining() + "\t" + jobs[i].getStatus();
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
	
	//checks to see if the program has finished
	public boolean checkFinished() {
		if(allJobsFinished()) {
			finishedOutput();
			return true;
		}
		else if (timer == 30) {
			finishedOutput();
			return true;
		}
		else {
			return false;
		}
	}
	
	//output when the program finishes 
	public void finishedOutput() {
		if(allJobsFinished()) {
			String finished = "All jobs have been completed!";
			System.out.println(finished);
			outputFile.println(finished);
			outputFile.close();
		}
		else if(timer == 30) {
			String timesUp = "Time is up!";
			System.out.println(timesUp);
			outputFile.println(timesUp);
			String finishedJobs = "Number of finished jobs: " + finishedJobs();
			System.out.println(finishedJobs);
			outputFile.println(finishedJobs);
			outputFile.close();
		}
	}
	
	//brings jobs into the ready queue when currently loaded jobs are finished
	public void loadJobs() {
		jobsReady = 0;
		for(int i = jobsReady; i < 20; i++) {
			boolean check = true;
			int fit = 0;
			if(allocation == Allocation.FIRST_FIT) {
				fit = firstFit(jobs[i]);
			}
			else if (allocation == Allocation.BEST_FIT) {
				fit = bestFit(jobs[i]);
			}
			for(int j = 0; j < 7; j++) {
				if(fit != -1 && !segments[fit].isOccupied() && !jobs[i].getStatus().equals("finished")) {
					if(check) {
						check = false;
						segments[fit].assignJob(jobs[i]);
						if(jobsRunning < 4) {
							jobs[i].setStatus("running");
							jobsRunning++;
						}
						else {
							jobs[i].setStatus("ready");
							jobsReady++;
						}
					}
				}
			}
		}
	}
	
	//updates the ready queue
	public void updateReadyQueue() {
		if(checkFinished() == false) {
			int k;
			jobsFinished = 0;
			for(k = 0; k < 9; k++) {
				if(segments[k].getJob() != null && segments[k].getJob().getStatus().equals("running")) {
					segments[k].update();
					if(segments[k].getJob().getStatus().equals("finished")) {
						segments[k].getJob().setSegmentNumber(-1);
						jobsRunning--;
					}
				}
				if(!segments[k].isOccupied()){
					jobsFinished++;
				}
			}
			
			k = 0;
			while(jobsRunning < 4 && k < 9) {
				if(segments[k].getJob() != null && segments[k].getJob().getStatus().equals("finished") && k < 9) {
					int nextJob = findNextJob();
					if(nextJob != -1) {
						segments[jobs[nextJob].getSegmentNumber()].update();
						jobsRunning++;
					}
				}
				k++;
			}
			writeOutput();
			
			if(jobsFinished == 9) {
				jobsRunning = 0;
				refresh();
			}
		}
	}
	
	
	public void refresh() {
		jobsFinished = 0;
		loadJobs();
	}
	
	//finds the next ready job
	public int findNextJob() {
		int index = -1;
		for(int i = 0; i < 20; i++) {
			if(jobs[i].getStatus().equals("ready")) {
				index = i;
				return index;
			}
		}
		return index;
	}
	
	//executes the program for 30 seconds
	public void execute() {
		while(timer < 31) {
			updateReadyQueue();
			timer++;
		}
	}
}
