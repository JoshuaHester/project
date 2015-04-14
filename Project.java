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
		caseOne.execute();
		memory.reset();
		
		//case Two - First Come, First Serve order, Best Fit allocation policy
		System.out.println("~~~~~~CASE TWO~~~~~~");
		Generator caseTwo = new Generator(memory, Generator.Order.FCFS, Generator.Allocation.BEST_FIT);
		caseTwo.execute();
		memory.reset();
		
		//case Three - Shortest Job First order, Best Fit allocation policy
		System.out.println("~~~~~~CASE THREE~~~~~~");
		Generator caseThree = new Generator(memory, Generator.Order.SJF, Generator.Allocation.BEST_FIT);
		caseThree.execute();
	}
}
