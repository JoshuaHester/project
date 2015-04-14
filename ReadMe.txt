The main for this program can be found in the Project.java file. Compiling and running this file 
The results for the three test cases can be found 

Project.java - This file is the main for the program. The three test cases are created here. The output for the tests are displayed on the command line and is printed out to the test case text files.

Generator.java - This class simulates a case. The required parameters are a Memory object, an order for the job queue, and an allocation policy. The class simulates the operation of the hypothetical operating system based on the specified cases. It also makes use of four processors.

Job.java - This class creates a Job object. It creates an ID number, a memory request, and an execution time request. The default status is “waiting.”

Segments.java - This class creates a Segment object. It takes in a size in Megabytes.

Memory.java - This class creates the Memory object for the hypothetical operating system. It takes in the required amount of Segment objects with the required sizes. It also generates the 20 jobs required for this project. 