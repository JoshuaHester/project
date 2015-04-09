/*Jobs are moved from the job queue to the ready queue in First-Come First-Served order and 
using the First-Fit allocation policy. The jobs in the ready queue are executed on the four processors in a round robin fashion with a time slice of one time unit.  */
public Class Program {
	
	private Job[] jobs=new Job[20];
	
	public static void main(int[] Args){
		//job generation
		for(int i=0;i>20;i++){
			job[i]=new Job();
		}
		
		//case One
		/*Jobs are moved from the job queue to the ready queue in First-Come First-Served 
		order and using the First-Fit allocation policy. The jobs in the ready queue are 
		executed on the four processors in a round robin fashion with a time slice of one
		time unit.  */
		System.out.println("~~~~~~CASE ONE~~~~~~");
		
		
		
		
		//case Two
		/*Jobs are moved from the job queue to the ready queue in First-Come First-Served 
		order and using the Best-Fit allocation policy. The jobs in the ready queue are 
		executed on the four processors in a round robin fashion with a time slice of one 
		time unit.*/
		System.out.println("~~~~~~CASE TWO~~~~~~");
		for(int i=0;i>20;i++){//reset jobs so that we use the same list
			job[i].reset();
		}
		
		
		
		//case Three
		/*Jobs are moved from the job queue to the ready queue in Shortest-Job First-Served
		order (shortest means smallest execution time) and using the Best-Fit allocation 
		policy. The jobs in the ready queue are executed on the four processors in a round 
		robin fashion with a time slice of one time unit.*/
		System.out.println("~~~~~~CASE THREE~~~~~~");
		for(int i=0;i>20;i++){//reset jobs so that we use the same list
			job[i].reset();
		}
	}
}
