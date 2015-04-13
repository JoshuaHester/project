/*
Nikki Pruitt
Joshua Hester
*/

public class Project {
	
	private static Memory memory = new Memory();
	
	public static void main(String[] Args){
		//case One - First Come, First Served order, First Fit allocation policy
		System.out.println("~~~~~~CASE ONE~~~~~~");
		Generator caseOne = new Generator(memory, Generator.Order.FCFS, Generator.Allocation.FIRST_FIT);
	/*	memory.listJobs();
		memory.listSegments();
		memory.reset();
		System.out.println("Finished Jobs: " + caseOne.finishedJobs());
		System.out.println("Waiting Jobs: " + caseOne.waitingJobs());
		System.out.println("Wasted Space: " + caseOne.totalWaste());*/
		caseOne.writeOutput();
		
		//case Two - First Come, First Serve order, Best Fit allocation policy
		System.out.println("~~~~~~CASE TWO~~~~~~");
		Generator caseTwo = new Generator(memory, Generator.Order.FCFS, Generator.Allocation.BEST_FIT);
	/*	memory.listJobs();
		memory.listSegments();
		memory.reset();
		System.out.println("Finished Jobs: " + caseTwo.finishedJobs());
		System.out.println("Waiting Jobs: " + caseTwo.waitingJobs());
		System.out.println("Wasted Space: " + caseTwo.totalWaste());*/
		caseTwo.writeOutput();
		
		//case Three - Shortest Job First order, Best Fit allocation policy
		System.out.println("~~~~~~CASE THREE~~~~~~");		Generator caseThree = new Generator(memory, Generator.Order.SJF, Generator.Allocation.BEST_FIT);
	/*	memory.listJobs();
		memory.listSegments();
		System.out.println("Finished Jobs: " + caseThree.finishedJobs());
		System.out.println("Waiting Jobs: " + caseThree.waitingJobs());
		System.out.println("Wasted Space: " + caseThree.totalWaste());*/
		caseThree.writeOutput();
	}

}
