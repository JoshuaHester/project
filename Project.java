/*
Nikki Pruitt
Joshua Hester
*/

public class Project {
	
	private static Memory memory = new Memory();
	
	public static void main(String[] Args){
		//case One - First Come, First Served order, First Fit allocation policy
		System.out.println("~~~~~~CASE ONE~~~~~~");
		//System.out.println("TIME	ID	SEGMENT      MEM REQUEST	TIME REMAIN	MESSAGES");
		Generator caseOne = new Generator(Generator.Order.FCFS, Generator.Allocation.FIRST_FIT);
		memory.listJobs();
		memory.listSegments();
		memory.reset();
		
		
		//case Two - First Come, First Serve order, Best Fit allocation policy
		System.out.println("~~~~~~CASE TWO~~~~~~");
		//output headings
		//System.out.println("TIME	ID	SEGMENT      MEM REQUEST	TIME REMAIN	MESSAGES");
		Generator caseTwo = new Generator(Generator.Order.FCFS, Generator.Allocation.BEST_FIT);
		memory.listJobs();
		memory.listSegments();
		memory.reset();
		
		//case Three - Shortest Job First order, Best Fit allocation policy
		System.out.println("~~~~~~CASE THREE~~~~~~");
		//output headings 
		//System.out.println("TIME	ID	SEGMENT      MEM REQUEST	TIME REMAIN	MESSAGES");
		Generator caseThree = new Generator(Generator.Order.SJF, Generator.Allocation.BEST_FIT);
		memory.listJobs();
		memory.listSegments();
	}
	
	
	public void output(int time,Job job){
		System.out.println(time+"/t");
		System.out.print(job.getID()+"/t");
		System.out.print(job.getSegmentNumber()+"/t");
		System.out.print(job.getMemoryRequest()+"/t");
		System.out.print(job.getTimeRemaining()+"/t");
		System.out.print(job.getStatus());
	}
}
