/*
Nikki Pruitt
Joshua Hester
*/

public class Memory {
	private Segments[] segments;		//array of memory segments
	private Job[] jobs;				//array of jobs
	
	//constructor for memory
	public Memory() {
		//the 10 segments of memory with specifed sizes
		segments = new Segments[10];
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
		
		jobs = new Job[20];
		generateJobs();
	}
	
	//generates a list of 20 jobs
	public void generateJobs() {
		for(int i=0;i<20;i++){
			jobs[i]=new Job();
		}
	}
	
	//prints out the list of job information including job id, memory request, and time request
	public void listJobs() {
		for(int i = 0; i < 20; i++) {
			System.out.println(jobs[i].getID() + " " + jobs[i].getMemoryRequest() + " " + jobs[i].getTimeRequest());
		}
	}
	
	//prints out the list of segment information including segment number and size
	public void listSegments() {
		for(int i = 0; i < 10; i++) {
			System.out.println(segments[i].getSegmentNumber() + " " + segments[i].getSize());
		}
	}
	
	//resets the jobs and segments
	public void reset() {
		for(int i = 0; i < 20; i++) {
			jobs[i].reset();
		}
		
		for(int i = 0; i < 10; i++) {
			segments[i].reset();
		}
	}
}