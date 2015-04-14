/*
Nikki Pruitt
Joshua Hester
*/

import java.util.Random;

public class Job{

	private int id;				//Job identification number
	private int memReq;			//Memory request (in MB)
	private int exTimeReq;		//Execution time request
	private int segNum;			//Memory assigned (segment number)
	private int exTimeRem;		//Execution time remaining (initially same as item c.)
	private String status;		//Job status: Waiting, Ready, Running, & Finished
	
	private static int idCounter = 0;	//counter used to number each segment
	
	//constructor for the job object
	public Job(){
		this.id=idCounter;
		idCounter++;
		this.memReq = generateMemoryReq(); //rand 16-64
		this.exTimeReq = generateTimeRequest(); //rand 2-10
		this.exTimeRem = exTimeReq;
		this.segNum = -1;		//indicates that job isn't in a segment
		this.status = "waiting";
	}
	
	//generates a random memory request between 16-64 megabytes
	public int generateMemoryReq(){
		Random rand = new Random();
		int n = rand.nextInt(48) + 16;
		return n;
	}
	
	//generates a random time request between 2-10 seconds
	public int generateTimeRequest(){
		Random rand = new Random();
		int n = rand.nextInt(8) + 2;
		return n;
	}
	
	//resets the job
	public void reset(){
		this.exTimeRem = exTimeReq;
		this.segNum = -1;
		this.status = "waiting";
	}
	
	//returns the job id number
	public int getID(){
		return id;
	}
	
	//returns the job's memory request
	public int getMemoryRequest(){
		return memReq;
	}
	
	//returns the job's execution time request
	public int getTimeRequest(){
		return exTimeReq;
	}
	
	//sets the segment number of the job
	public void setSegmentNumber(int segNum){
		this.segNum=segNum;
	}
	
	//returns the job's segment number
	public int getSegmentNumber(){
		return segNum;
	}
	
	//updates the job's time remaining
	public int decrementTime(){
		exTimeRem--;
		return exTimeRem;
	}
	
	//returns the job's remaining execution time
	public int getTimeRemaining(){
		return exTimeRem;
	}
	
	//sets the job's status
	public void setStatus(String status){
		this.status=status;
	}
	
	//returns the job's status
	public String getStatus(){
		return status;
	}
	
}