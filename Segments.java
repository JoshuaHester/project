/*
Nikki Pruitt
Joshua Hester
*/

public class Segment{
	private int segmentNumber;		//segment identification number
	private int segSize;				//size of the segment
	private boolean occupied;			//indicates if the segment is in use
	private Job job;					//job assigned to segment
	
	private static numberCounter;	//counter used to number each segment
	
	//constructor for the segment object
	public Segment(int size){
		this.segmentNumber=numberCounter;
		numberCounter++;
		this.segSize=size;
		this.occupied=false;
	}
	
	//assigns a job to a segment
	public void assignJob(Job j) {
		job = j;
		occupied = true;
		job.setSegmentNumber(segmentNumber);
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
}