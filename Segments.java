public class Segment{
	private int segmentNumber;		//segment identification number
	private int segSize;				//size of the segment
	private boolean occupied;			//indicates if the segment is in use
	private Job job;					//job assigned to segment
	
	private static numberCounter;
	public Segment(int size){
		this.segmentNumber=numberCounter;
		numberCounter++;
		this.segSize=size;
		this.occupied=false;
	}
	
	public void assignJob(Job j) {
		job = j;
		occupied = true;
		job.setSegmentNumber(segmentNumber);
	}
	
	public int getSize(){
		return segSize;
	}
	
	public boolean isOccupied(){
		return occupied;
	}
	
	public void setOccupied(boolean occ){
		occupied=occ;
	}
}