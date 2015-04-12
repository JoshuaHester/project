/*
Nikki Pruitt
Joshua Hester
*/

public class Memory {
	private Segements[] segments;		//array of memory segments
	
	public Memory() {
		//the 10 segments of memory with specifed sizes
		segements = new Segments[10];
		segments[0] = new Segments(32);
		segments[1] = new Segments(48);
		segments[2] = new Segments(24);
		segments[3] = new Segments(16);
		segments[4] = new Segments(64);
		segments[5] = new Segments(48);
		segments[6] = new Segments(32);
		segments[7] = new Segments(64);
		segments[8] = new Segments(48);
		segments[9] = new Segments(32);
	}
}