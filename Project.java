/*
Nikki Pruitt
Joshua Hester
*/

public class Project {
	
	private static Memory memory = new Memory();
	
	public static void main(String[] Args){
		//case One
		/*Jobs are moved from the job queue to the ready queue in First-Come First-Served 
		order and using the First-Fit allocation policy. The jobs in the ready queue are 
		executed on the four processors in a round robin fashion with a time slice of one
		time unit.  */
		System.out.println("~~~~~~CASE ONE~~~~~~");
		//System.out.println("TIME	ID	SEGMENT      MEM REQUEST	TIME REMAIN	MESSAGES");
		memory.listJobs();
		memory.listSegments();
		memory.reset();
		
		
		//case Two
		/*Jobs are moved from the job queue to the ready queue in First-Come First-Served 
		order and using the Best-Fit allocation policy. The jobs in the ready queue are 
		executed on the four processors in a round robin fashion with a time slice of one 
		time unit.*/
		System.out.println("~~~~~~CASE TWO~~~~~~");
		//output headings
		//System.out.println("TIME	ID	SEGMENT      MEM REQUEST	TIME REMAIN	MESSAGES");
		memory.listJobs();
		memory.listSegments();
		memory.reset();
		
		//case Three
		/*Jobs are moved from the job queue to the ready queue in Shortest-Job First-Served
		order (shortest means smallest execution time) and using the Best-Fit allocation 
		policy. The jobs in the ready queue are executed on the four processors in a round 
		robin fashion with a time slice of one time unit.*/
		System.out.println("~~~~~~CASE THREE~~~~~~");
		//output headings 
		//System.out.println("TIME	ID	SEGMENT      MEM REQUEST	TIME REMAIN	MESSAGES");
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
