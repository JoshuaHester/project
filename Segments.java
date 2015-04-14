/*
Nikki Pruitt
Joshua Hester
*/

public class Segments{
	private int segmentNumber;		//segment identification number
	private int segSize;				//size of the segment
	private boolean occupied;			//indicates if the segment is in use
	private Job job;					//job assigned to segment
	private int wastedSpace;			//amount of space wasted per segment
	
	private static int numberCounter;	//counter used to number each segment
	
	//constructor for the segment object
	public Segments(int size){
		this.segmentNumber=numberCounter;
		numberCounter++;
		this.segSize=size;
		this.occupied=false;
		this.wastedSpace = 0;
	}
	
	//returns the segment number
	public int getSegmentNumber() {
		return segmentNumber;
	}
	
	//assigns a job to a segment
	public void assignJob(Job j) {
		job = j;
		occupied = true;
		job.setSegmentNumber(segmentNumber);
		job.setStatus("running");
	}
	
	//removes the assigned job from the segment
	public void removeJob() {
		job.setStatus("finished");
		job = null;
		occupied = false;
		wastedSpace = 0;
	}
	
	//returns the job currently in segment
	public Job getJob() {
		return job;
	}
	
	//returns the size of the segment
	public int getSize(){
		return segSize;
	}
	
	//returns true if the segement is currently in use
	public boolean isOccupied(){
		return occupied;
	}
	
	//sets if the segement occupied or not
	public void setOccupied(boolean occ){
		occupied=occ;
	}
	
	//returns the amount of wasted space 
	public int getWastedSpace() {
		wastedSpace = segSize - job.getMemoryRequest();
		return wastedSpace;
	}
	
	//resets the segment
	public void reset() {
		job = null;
		occupied = false;
		wastedSpace = 0;
	}
}