/*Jobs are moved from the job queue to the ready queue in First-Come First-Served order and 
using the First-Fit allocation policy. The jobs in the ready queue are executed on the four processors in a round robin fashion with a time slice of one time unit.  */
public class Project {
	
	private static Job[] job=new Job[20];
	
	public static void main(String[] Args){
		//job generation
		for(int i=0;i<20;i++){
			job[i]=new Job();
			//System.out.println(job[i].getTimeRequest());
		}
		
		//case One
		/*Jobs are moved from the job queue to the ready queue in First-Come First-Served 
		order and using the First-Fit allocation policy. The jobs in the ready queue are 
		executed on the four processors in a round robin fashion with a time slice of one
		time unit.  */
		System.out.println("~~~~~~CASE ONE~~~~~~");
		System.out.println("TIME	ID	SEGMENT      MEM REQUEST	TIME REMAIN	MESSAGES");
		
		
		
		//case Two
		/*Jobs are moved from the job queue to the ready queue in First-Come First-Served 
		order and using the Best-Fit allocation policy. The jobs in the ready queue are 
		executed on the four processors in a round robin fashion with a time slice of one 
		time unit.*/
		System.out.println("~~~~~~CASE TWO~~~~~~");
		System.out.println("TIME	ID	SEGMENT      MEM REQUEST	TIME REMAIN	MESSAGES");
		for(int i=0;i<20;i++){//reset jobs so that we use the same list
			job[i].reset();
			//System.out.println(job[i].getTimeRequest());
		}
		
		
		
		//case Three
		/*Jobs are moved from the job queue to the ready queue in Shortest-Job First-Served
		order (shortest means smallest execution time) and using the Best-Fit allocation 
		policy. The jobs in the ready queue are executed on the four processors in a round 
		robin fashion with a time slice of one time unit.*/
		System.out.println("~~~~~~CASE THREE~~~~~~");
		System.out.println("TIME	ID	SEGMENT      MEM REQUEST	TIME REMAIN	MESSAGES");
		for(int i=0;i<20;i++){//reset jobs so that we use the same list
			job[i].reset();
			//System.out.println(job[i].getTimeRequest());
		}
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
