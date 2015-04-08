public class Segment{
	private int segmentNumber;
	private int segSize;
	private boolean occupied;
	
	private static numberCounter;
	public Segment(int size){
		this.segmentNumber=numberCounter;
		numberCounter++;
		this.segSize=size;
		this.occupied=false;
	}
	
	public int getSize(){return segSize;}
	public boolean isOccupied(){return occupied;}
	public void setOccupied(boolean occ){occupied=occ;}
}