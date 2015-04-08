using java.util.Random();

public class Job{

	private int iD;				//Job identification number
	private int memReq;			//Memory request (in MB)
	private int exTimeReq;		//Execution time request
	private int segNum;			//Memory assigned (segment number)
	private int exTimeRem;		//Execution time remaining (initially same as item c.)
	private String status;		//Job status: Waiting, Ready, Running, & Finished
	
	private static idCounter=0;
	public Job(){
		this.id=idCounter;
		idCounter++;
		this.memReq=generateMemoryReq();//rand 16-64
		this.exTimeReq=generateTimeRequest();//rand 2-10
		this.exTimeRem=exTimeReq;
		this.segNum=-1;
		this.status="Waiting";
	}
	
	public int generateMemoryReq(){
		Random rand = new Random();
		int  n = rand.nextInt(48) + 16;
		return n;
	}
	
	public int generateTimeRequest(){
		Random rand = new Random();
		int  n = rand.nextInt(8) + 2;
		return n;
	}
	
	public void reset(){
		this.exTimeRem=exTimeReq;
		this.segNum=-1;
		this.status="Waiting";
	}
	
	public int getID(){return id;}
	public int getMemoryRequest(){return memReq;}
	public int getTimeRequest(){return exTimeReq;}
	public void setSegmentNumber(int segNum){this.segNum=segNum;}
	public int getSegmentNumber(){return segNum;}
	public void setTimeRemaining(int time){this.Rem=time;}
	public int getTimeRemaining(){return Rem;}
	public void setStatus(String status){this.status=status;}
	public String getStatus(){return status;}
	
}