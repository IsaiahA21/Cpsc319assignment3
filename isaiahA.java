//package lastproject;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class isaiahA {
    static File inputFile;
    static File outputFile;
    static FileWriter writer =null;
    static Scanner readerScanner =null;
    static BufferedWriter bw = null;
 
	
	private  Node root;
	static String[] arr = null;
	static String input_b;
	private static int firstLine = 0;
	  int maxElement=1; 
	
	 class Node{
		private int depthOfTree;
		private String payload;
		private Node leftNode;
		private Node rightNode;
		private Node MiddleNode;
		
		//defualt constructor
		Node () 
		{
			depthOfTree = 0; // the root is 0
			this.payload = "root";
			this.leftNode = null;
			this.MiddleNode = null;
			this.rightNode = null;
			arr = new String[4];
			arr[0] = this.payload;
		}
		
		Node(String input) 
		{
			depthOfTree = 1; // the root is 0
			this.payload = input;
			this.leftNode = null;
			this.MiddleNode = null;
			this.rightNode = null;
		}
	}
	
		//private TernaryTree rootNode;
		
	public isaiahA() 
	{
			root = new Node();	
	}

		
	public int maxDepth(Node node)
		    {
		        if (node == null)
		            return 0;
		        else
		        {
		            /* compute the depth of each subtree */
		            int lDepth = maxDepth(node.leftNode);
		            int rDepth = maxDepth(node.rightNode);
		            int mDepth = maxDepth(node.MiddleNode);
		            
//		            System.out.println("rdepth is " + rDepth + " ldepth is " + lDepth + " mDepth is " + mDepth);
		            /* use the larger one */
		            if (lDepth >= rDepth && lDepth >= mDepth) 
		                return (lDepth+1);
		             else if (rDepth >= lDepth && rDepth >= mDepth)
		                return (rDepth+1);
		            	 else 
		            		 return (mDepth+1);
		        }
		    }
	
	void AddToArray(int index, String input_b)
		 {
			 int localMax=1;
			 System.out.println("max depth is "+maxDepth(root));
			 int depth = maxDepth(root);
			 for (int g= 1; g<depth; g++) {
				 localMax +=(int) + Math.pow(3,g);
				System.out.println("creating local max depth is " + localMax);
			 }
			 maxElement =localMax;
			 System.out.println("max Element is "+ maxElement + "arr.length is " + arr.length);
			 
			 String temp[] = new String[maxElement];
			 System.out.println("temp arr has length of " + temp.length);
			 /*new additional significant
			 if (maxElement != arr.length && arr.length >maxElement) {
				 temp = new String[arr.length];
			 }*/
			 //copy old array into new array
			 int limited=arr.length;
			 if (temp.length < arr.length) {
				 limited = temp.length;
			 }
			 for (int h=0; h < limited; h++) {
				 temp[h] = arr[h];
			 }
			 temp[index] = input_b;
			 System.out.println("in AddToArray temp["+ index+"] is " + input_b);
			 arr = temp;
		 }


	private static int countNullsInALine(int leftBound, int rightBound) {
		int nonNulls=0;
		//System.out.println("In countNull left bound is " + leftBound + "rightBound is "+rightBound);
		for (int i=leftBound; i<=rightBound; i++) {
			if (arr[i] != null) {
				nonNulls++;
			}
		}
		return nonNulls;
	}
	public void movingThroughTree() 
	{
		System.out.println();
		StringBuilder outputStatement = new StringBuilder();
		int leftBoundary=0;
		int rightBoundary=0;
		int i=0;
		int nonNullsInALine =0;
		int lengthOfLine=0;
		Pattern pattern;
		Matcher myMatcher;
		String ret="";
		final String outRegex ="(.*[^ ; \\s])";
		// "([ ][;][ ]\\z)"
		// print root 
		
		final String regexForRemoving ="([\\n])([ ][;][ ])(.*)"/*"[\n][ ][;][ ](.*[\n]{0,1})"*//*"^([ ][;][ ])(.*[\n]{0,1})"*/;
		
		System.out.println(arr[i]);
		
		String tempLine = "";

			
//		outputStatement.append(arr[i]);
		ret += arr[i];
		
		 leftBoundary=1;
		 rightBoundary=leftBoundary*3;
		 lengthOfLine = rightBoundary - leftBoundary;
		 lengthOfLine++;
    	try {
    		i =1;
//    		System.out.println("inside print, the max eleemnt is " + maxElement);
    		while (rightBoundary+1 <= maxElement) {
//        		System.out.println("current boundary are " + leftBoundary +"  " + rightBoundary);
    			nonNullsInALine = countNullsInALine(leftBoundary,rightBoundary);
    			if (i == leftBoundary) {
    				if(arr[i] != null) {
//    					System.out.print("\n"+ arr[i] + "and i is " + i);
    					tempLine += "\n"+ arr[i];
    				}
    				else if(arr[i] == null /*&& nonNullsInALine != 0*/) {
//    					System.out.print("\n "+ "and i is " + i);
//    					System.out.println("leftBoundary is " + leftBoundary + " nonNulls is " + nonNullsInALine + "lengthOfLine is " + lengthOfLine);
    					tempLine += "\n";
    				}
    			}
    			// put element within the boundary into a string
    			else if (arr[i] != null) {
//    				System.out.print(" ; "+arr[i] + "and i is " + i);
					tempLine += " ; "+arr[i];
    			}
 

    			
    			if (i == rightBoundary) {

    			leftBoundary = rightBoundary+1;
   			 rightBoundary=leftBoundary*3;
   			 lengthOfLine = rightBoundary - leftBoundary;
   			lengthOfLine++;
   			 
// 			System.out.println(" And the line is " + tempLine);
 			pattern = Pattern.compile(regexForRemoving);
 			myMatcher = pattern.matcher(tempLine);
 			
 			if (myMatcher.find()) {
  			//System.out.print("There is a match " +tempLine + "\n\n");
 				//System.out.println("index of match is "+ myMatcher.group(1));
 				String checkedLine = myMatcher.group(1) + myMatcher.group(3);
 				ret += myMatcher.group(1) + myMatcher.group(3);
 	 			//System.out.println("The line added to ouput would be " + checkedLine + "verus the original line " + tempLine);
 	 			//System.out.println("ret is currently " + ret);
 	 			tempLine = "";
 			} else {
 				ret += tempLine;
 				tempLine ="";
 			}
    	}
    			
    			i++;
    		}
    		
    		writeToFile(ret.trim());
    		
//    		writeToFile(outputStatement.toString());
    	}
	catch (NullPointerException e) {
    		System.out.print("index at last element is null");
    	} 
    	finally {
    	}

	}

public  int AddL(String input_a, String input_b,int[] instrArray, boolean specialChar) 
		{
			Node move = root;
		try {
			// if the only instruction is root
			if (instrArray[instrArray.length-1] == 0) {
				if (move.leftNode == null) {
					System.out.println("Only one instruction which is " + instrArray[instrArray.length-1]+ " creating a tree named " + input_b + " to the left node of " + input_a);

					move.leftNode = new Node(input_b);
//				AddToArray((instrArray[instrArray.length-1])*3+2, input_b);
				return ((instrArray[instrArray.length-1])*3+1);
				}
				 else if (root.leftNode != null && specialChar == true) {
						System.out.println("The $ was in a AddL instr so node at the left of root was changed to " + input_b);
						move.leftNode.payload = input_b;
						return (((instrArray[instrArray.length-1])*3)+1);
					} 
				else if (root.leftNode != null && specialChar == false) {
					System.out.println("going to write to file Add operation not possible because " + arr[instrArray.length-1] + "is already at " + (instrArray.length-1));
					return -10;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			
			for (int i=0; i< instrArray.length; i++) 
			{
//				 	System.out.println("inside AddL else statement");
					double temp= ((double)instrArray[i]/3);
					temp = temp -(instrArray[i]/3);
					temp = Math.round(temp*100.0)/100.0;
//					System.out.println("current value of temp is "+ temp + "and the position is " + instrArray[i]);
					
					if (instrArray[i] ==0) {
	 					move = move;
					}
					else if (temp ==0 && (instrArray[i]%3) ==0) {
//	 					System.out.println("in move right");
	 					move = move.rightNode;
					}
					else if (temp == 0.33) {
//	 					System.out.println("in move left");
						move = move.leftNode;
					}
					else if (temp == 0.67) {
//	 					System.out.println("in move middle");
						move = move.MiddleNode;
					} else {
						System.out.println("don't know what to do with the value " + temp);
					}
			}
			if (move.leftNode != null) {
				System.out.println("move is pointing to "+ move.leftNode.payload);
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
//			System.out.println("I have moved the node inside addM, the current payload is " + move.payload);
			 if (move.leftNode != null && move.leftNode.payload != null && specialChar == false) {
				System.out.println("going to write to file Add operation not possible. move.leftNode.payload is " + move.leftNode.payload);
				return -10;
			}
			 else if (root.leftNode != null && specialChar == true) {
					System.out.println("The $ was in a AddL instr so node at the left of "+  input_a +" was changed to " + input_b);
					move.leftNode.payload = input_b;
					return (((instrArray[instrArray.length-1])*3)+1);
				} 
			 else if (move.leftNode == null) {
//				System.out.println("second option creating a tree named " + input_b + " to the left node of " + move.payload);
				move.leftNode = new Node(input_b);
//				AddToArray(instrArray[instrArray.length-1]*3+2, input_b);
				return ((instrArray[instrArray.length-1]*3)+1);
			}
			return -1;

	        //printArray();
	}
	
public int AddM(String input_a, String input_b,int[] instrArray, boolean specialChar) throws Exception
	{
		Node move = root;
		
		try {
		// if the only instruction is root
		if (instrArray[instrArray.length-1] == 0) {
			if (move.MiddleNode == null) {
//				System.out.println("Only one instruction which is " + instrArray[instrArray.length-1]+ " creating a tree named " + input_b + " to the middle node of " + input_a);

				move.MiddleNode = new Node(input_b);
//			AddToArray((instrArray[instrArray.length-1])*3+2, input_b);
			return ((instrArray[instrArray.length-1])*3+2);
			} else if (root.rightNode != null && specialChar == true) {
				System.out.println("The $ was in a AddM instr so node at the middle of root was changed to " + input_b);
				move.MiddleNode.payload = input_b;
				return (((instrArray[instrArray.length-1])*3)+2);
			} 
			else if (root.MiddleNode != null && specialChar == false) {
				System.out.println("going to write to file Add operation not possible because " + arr[instrArray.length-1] + "is already at " + (instrArray.length-1));
				return -10;
			}
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
		for (int i=0; i< instrArray.length; i++) 
		{
				double temp= ((double)instrArray[i]/3);
				temp = temp -(instrArray[i]/3);
				temp = Math.round(temp*100.0)/100.0;
//				System.out.println("current value of temp is "+ temp + "and the position is " + instrArray[i]);
				
				if (instrArray[i] ==0) {
 					move = move;
				}
				else if (temp ==0 && (instrArray[i]%3) ==0) {
 					System.out.println("in move right");
 					move = move.rightNode;
				}
				else if (temp == 0.33) {
 					System.out.println("in move left");
					move = move.leftNode;
				}
				else if (temp == 0.67) {
 					System.out.println("in move middle");
					move = move.MiddleNode;
				} else {
					System.out.println("don't know what to do with the value " + temp);
				}
		}
		if (move.MiddleNode != null) {
		System.out.println("move is pointing to "+ move.MiddleNode.payload);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
//		System.out.println("I have moved the node inside addM, the current payload is " + move.payload);
		 if (move.MiddleNode != null && move.MiddleNode.payload != null && specialChar == false) {
			System.out.println("going to write to file Add operation not possible. move.MiddleNode.payload is " + move.MiddleNode.payload);
			return -10;
		}
		 else if (move.MiddleNode != null && specialChar == true) {
				System.out.println("The $ was in a AddM instr so node at the middle of "+  input_a +" was changed to " + input_b);
				move.MiddleNode.payload = input_b;
				return (((instrArray[instrArray.length-1])*3)+2);
			}
		 
		 else if (move.MiddleNode == null) {
//			System.out.println("second option creating a tree named " + input_b + " to the middle node of " + move.payload);
			move.MiddleNode = new Node(input_b);
//			AddToArray(instrArray[instrArray.length-1]*3+2, input_b);
			return ((instrArray[instrArray.length-1]*3)+2);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;

        //printArray();
	}
	
	
public int AddR(String input_a, String a,int[] instrArray, boolean specialChar) 
	{
		
Node move = root;
		
		// if the only instruction is root
try {
		if (instrArray[instrArray.length-1] == 0) {
			if (move.rightNode == null) {
				System.out.println("Only one instruction which is " + instrArray[instrArray.length-1]+ " creating a tree named " + input_b + " to the right node of " + input_a);

				move.rightNode = new Node(input_b);
//			AddToArray((instrArray[instrArray.length-1])*3+2, input_b);
//				System.out.println(instrArray[instrArray.length-1])*3+3);
			return (((instrArray[instrArray.length-1])*3)+3);
			} else if (root.rightNode != null && specialChar == true) {
				System.out.println("The $ was in a AddR instr so node at the right of root was changed to " + input_b);
				move.rightNode.payload = input_b;
				return (((instrArray[instrArray.length-1])*3)+3);
			} 
			else if (root.rightNode != null && specialChar == false) {
				System.out.println("going to write to file Add operation not possible because " + arr[instrArray.length-1] + "is already at " + (instrArray.length-1));
				return -10;
			}
		}
} catch(Exception e) {
	e.printStackTrace();
}
		try {
		for (int i=0; i< instrArray.length; i++) 
		{
//			 	System.out.println("inside AddL else statement");
				double temp= ((double)instrArray[i]/3);
				temp = temp -(instrArray[i]/3);
				temp = Math.round(temp*100.0)/100.0;
//				System.out.println("current value of temp is "+ temp + "and the position is " + instrArray[i]);
				
				if (instrArray[i] ==0) {
 					move = move;
				}
				else if (temp ==0 && (instrArray[i]%3) ==0) {
// 					System.out.println("in move right");
 					move = move.rightNode;
				}
				else if (temp == 0.33) {
// 					System.out.println("in move left");
					move = move.leftNode;
				}
				else if (temp == 0.67) {
// 					System.out.println("in move middle");
					move = move.MiddleNode;
				} else {
					System.out.println("don't know what to do with the value " + temp);
				}
		}
		
		if (move.rightNode != null) {
		System.out.println("move is pointing to "+ move.rightNode.payload);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
		 if (move.rightNode != null && move.rightNode.payload != null && specialChar == false) {
			System.out.println("going to write to file Add operation not possible. move.leftNode.payload is " + move.rightNode.payload);
			return -10;
		}
		 else if (move.rightNode != null && specialChar == true) {
				System.out.println("The $ was in a AddR instr so node at the right of "+  input_a +" was changed to " + input_b);
				move.rightNode.payload = input_b;
				return (((instrArray[instrArray.length-1])*3)+3);
			}
		 else if (move.rightNode == null) {
//			System.out.println("second option creating a tree named " + input_b + " to the left node of " + move.payload);
			move.rightNode = new Node(input_b);
//			AddToArray(instrArray[instrArray.length-1]*3+2, input_b);
			return (instrArray[instrArray.length-1]*3+3);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
		
	}
	
	private int instruction_was_ADD(String readData, String aDDDREGEXinputs) 
	{
        final String ADDR_REGEX = "^([A][d]{2}[R][(])(.*[)])"; 
        final String ADDL_REGEX = "^([A][d]{2}[L][(])(.*[)])";
        final String ADDM_REGEX = "^([A][d]{2}[M][(])(.*[)])";
		
//    	System.out.println("yes "+ readData  + "has Add_(____)");
        Pattern myPattern;
        Matcher myMatcher;
        
        Node travel = root;

    	// looking for input
    	myPattern = Pattern.compile(aDDDREGEXinputs);
    	myMatcher = myPattern.matcher(readData);
    	
    	// checking if the argument format is valid
    	if(myMatcher.find()) {
    		String input_a = myMatcher.group(1);
    	  input_b = myMatcher.group(3);
    	  
    	  boolean specialChar= false;
    	  
    	  //checking if the $ sign is included in readData
    	  if (myMatcher.find(2) && myMatcher.group(2).equals("$")) {
      	String specialCase = myMatcher.group(2);
      	 specialChar = true;
      	System.out.println("special symbol is included " + specialChar);
    	  }

    		
    		System.out.println("input a is "+ input_a + " and "  + "input b is " + input_b); 
//            arr = new String[maxDepth(root)];
//            System.out.println("the first element of the array is " + arr[0]);
            
    		//starting from the right most element
            for (int p=arr.length-1; p>=0; p--) 
            {
//            	System.out.println("p is currently " + p);
            	try {
            		if (arr[p] != null) {
            	if (arr[p].equals(input_a)) {
            		System.out.println("a is found inside the node at the index of "+ p);
            		// find path from 0 to parent
            		// first find the length of the array
            		int findArraylength =1;
            		for (int toZero=p; toZero != 0; findArraylength++) {
            			toZero = (toZero-1)/3;
            		}
            		System.out.println("the instr size of the array is " + findArraylength);
            		int instrArray[] = new int[findArraylength];
            		// now going to add numbers to the arrays.
            		// based off then remainer when divided by 3, I route to take to the a
            		int into= p;
            		for(int i=0; i < findArraylength; i++) {
            			instrArray[i]=into;
            			into =(into-1)/3;
            		}
//            		System.out.println(instrArray[0]);
            		Arrays.sort(instrArray);
            		for (int i: instrArray) {
            			System.out.print(i+",");
            		}
            		System.out.println();
//            		System.out.println(instrArray[0]);
            		
            		// looking for AddM
                	myPattern = Pattern.compile(ADDM_REGEX);
                	myMatcher = myPattern.matcher(readData);
                	
                	//looking for AddL
                	myPattern = Pattern.compile(ADDL_REGEX);
                	Matcher myMatcher2 = myPattern.matcher(readData);
                	
                	//looking for AddR
                	myPattern = Pattern.compile(ADDR_REGEX);
                	Matcher myMatcher3 = myPattern.matcher(readData);
            		
                	if (myMatcher.find()) {
            				// going to pass the instruction
            			 int index = AddM(input_a,input_b,instrArray, specialChar);
      					System.out.println("in AddM");
      					System.out.println("return the index of " + index);
                        return index;
            		}
                	else if (myMatcher2.find()) {
                		int index = AddL(input_a,input_b,instrArray, specialChar);
     					System.out.println("in AddL");
      					System.out.println("return the index of " + index);
                        return index;
                	} 
                	else if (myMatcher3.find()) {
                		int index = AddR(input_a,input_b,instrArray, specialChar);
     					System.out.println("in AddR");
      					System.out.println("return the index of " + index);
                        return index;
                	}
                  //  printArray();

            	}
//            	else {
//            		System.out.println("Couldn't find " + input_a +"in the tree, so couldn't add " + input_b);
//            		return -50;
//            	}
            	}
            	} catch (Exception e) {
            		e.printStackTrace();
                	//System.out.printf("arr[" + p +"] is " + arr[1] + "\n");
            	}
            }
    	} 
    	else {
    		System.out.println(readData + "arguments are invalid input format. therefore input error has been written to file ");
    		return -3;
    		/*try {
//				writer = new FileWriter(outputFile);
				writer.write("Input error.");
//	            writer.close();
//                readerScanner.close();
//	            System.exit(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
    		
    	}
		System.out.println("Couldn't find " +  myMatcher.group(1) +"in the tree, so couldn't add " + input_b);

	return -50;
	}
	
	public void writeToFile(String input) 
	{
		try {
			System.out.println("''" +input +"''");

		System.out.println("calling write to file");
		
		if (firstLine == 0) {
			firstLine++;
			bw.write(input);
		} else {
		bw.write("\n");
		bw.write(input);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){

//	    File inputFile;
//	    File outputFile;
//	    FileWriter writer =null;
//	    Scanner readerScanner =null;
	    
        String readData =null;
        char [] charData;
        
        Pattern myPattern;
        Pattern myPattern2;
        Pattern myPattern3;
        Pattern myPattern5;
        Pattern myPattern6;
        
        Matcher myMatcher;
        Matcher myMatcher2;
        Matcher myMatcher3;
        Matcher myMatcher5;
        Matcher myMatcher6;

        
        final String ADDR_REGEX = "^([A][d]{2}[R][(])(.*[)])"; 
        final String ADDL_REGEX = "^([A][d]{2}[L][(])(.*[)])";
        final String ADDM_REGEX = "^([A][d]{2}[M][(])(.*[)])";
        final String ADDREGEXinputs = "[(]([^,]{1,50}[,]{0,50})[,]([$]{0,1})(.*)[)]"/*"[(]([^,]{1,50}[,]{0,50})[,](.*)[)]"*/;
        final String PrintREGEX = "^([P][r][i][n][t][(][)])";
        final String DELREGEX = "^[D][e][l][M,R,L]{1}[(](.*)[)]";
        final String EXCHANGEREGEX = "^[E][x][c][h][a][n][g][e][(]([^,]{1,50}[,]{0,50})[,]([$]{0,1})(.*)[)]";
        
		isaiahA Tree = new isaiahA();
		//System.out.println(Tree.arr[0]);
        try {
			 inputFile = new File(args[0]);
			 outputFile = new File(args[1]);

            readerScanner = new Scanner(inputFile);
			 writer = new FileWriter(outputFile);
			 bw = new BufferedWriter(writer);
			 
           // reading the file
          while (readerScanner.hasNextLine()) {
                readData = readerScanner.nextLine();
                
                // for ADDR(a,b)
                myPattern = Pattern.compile(ADDR_REGEX);
                myMatcher = myPattern.matcher(readData);
                
                // for ADDL(a,b)
                myPattern2 = Pattern.compile(ADDL_REGEX);
                myMatcher2 = myPattern2.matcher(readData);
          
                // for ADDM(a,b)
                myPattern3 = Pattern.compile(ADDM_REGEX);
                myMatcher3 = myPattern3.matcher(readData);
                
                // for Print()
                Pattern myPattern4 = Pattern.compile(PrintREGEX);
                Matcher myMatcher4 = myPattern4.matcher(readData);
                
                
                 myPattern5 = Pattern.compile(DELREGEX);
                 myMatcher5 = myPattern5.matcher(readData);
                 
                 myPattern6 = Pattern.compile(EXCHANGEREGEX);
                 myMatcher6 = myPattern6.matcher(readData);
                
                // if the instruction was AddR(a,b)
                if (myMatcher.find()) {
            		System.out.println("root is" + Tree.root.payload);
                	int index = Tree.instruction_was_ADD(readData, ADDREGEXinputs);
                	if (index>= 0) {
                    	Tree.AddToArray(index, input_b);
                	}
                	if (index == -10){
                		Tree.writeToFile("Add operation not possible.");
                	}
                	if (index ==-3 || index ==-1 ) {
                		Tree.writeToFile("Input error.");
                        Tree.closeEveryThing();
                		System.exit(1);
                	}
                	System.out.println();
                } 
                // if the instruction was AddL(a,b)
                else if (myMatcher2.find()) {
            		System.out.println("root is" + Tree.root.payload);
                	int index = Tree.instruction_was_ADD(readData, ADDREGEXinputs);
            		System.out.println("index is " +index );
                	if (index>= 0) {
                    	Tree.AddToArray(index, input_b);
                	}
                	if (index == -10){
                		Tree.writeToFile("Add operation not possible.");
                	}
                	if (index ==-3 || index ==-1) {
                		Tree.writeToFile("Input error.");
                        Tree.closeEveryThing();
                		System.exit(1);
                	}
                	System.out.println();

                } 
                // if the instruction was AddM(a,b)
                else if (myMatcher3.find()) {
            		System.out.println("root is" + Tree.root.payload);
                	int index = Tree.instruction_was_ADD(readData, ADDREGEXinputs);
                	if (index>= 0) {
                    	Tree.AddToArray(index, input_b);
                	}

                	if (index == -10){
                		Tree.writeToFile("Add operation not possible.");
                	} 
                	if (index ==-3 || index ==-1) {
                		System.out.println("Got something invalid inside instruction_was_ADD");
                		Tree.writeToFile("Input error.");
                		Tree.closeEveryThing();
                		System.exit(1);
                	}
                	System.out.println();
                }
                
                else if (myMatcher4.find()) {
//                	if (firstLine != 0) {
//                		writeToFile("\n");
//                	}
                	Tree.movingThroughTree();
                	System.out.println();
                	System.out.println();
                }
                //del
                else if (myMatcher5.find()) {
            		System.out.println("root is " + Tree.root.payload + " and instruction is " + readData);
                	int delIndex = Tree.instruction_was_DEL(readData,myMatcher5.group(1));
                	// we get the index of the node that was deleted so now we have to make sure to delete in and it kids from the array
            		int dep = Tree.maxDepth(Tree.root);
                	if (delIndex >0) {
                		Tree.deleteFromArray(delIndex,dep);
                	} else if (delIndex==-1) {
                		System.out.println("Got something invalid inside instruction_was_DEL");
                    	Tree.writeToFile("Input error.");
                    	Tree.closeEveryThing();
                        System.exit(0);
                	}
//                	System.out.println("The length of the array is " + Tree.maxElement + "element at " + delIndex + Tree.arr[delIndex]);
                	System.out.println("max Depth is now " + Tree.maxDepth(Tree.root));
                	System.out.println();

                }
                else if (myMatcher6.find()) {
            		System.out.println("root is" + Tree.root.payload);
                	// checking if the argument format is valid
                	  boolean specialChar= false;
                	  //checking if the $ sign is included in readData
                	  if (myMatcher6.group(2).equals("$")) {
                  	String specialCase = myMatcher6.group(2);
                  	 specialChar = true;
                  	System.out.println("special symbol is included in exchange " + specialChar);
                	  }
                	Tree.Exchange(myMatcher6.group(1), myMatcher6.group(3), specialChar);
                	System.out.println();
                	System.out.println();

                }
                else {
                	System.out.println("You have got an invalid line " + readData);
                	Tree.writeToFile("Input error.");
                	Tree.closeEveryThing();

                    System.exit(0);
                }
                
           }
//           System.out.println("root is currently " + Tree.root.payload);
          // movingThroughTree();
          Tree.closeEveryThing();
          System.out.println("done");
//          writer.close();
//          readerScanner.close();
           
        }catch (FileNotFoundException e) {
			System.out.println("error: Your file wasn't found");
		} 
        /*catch (NullPointerException e) {
            System.out.println("error: you ain't got any input");
        } */
        catch (IOException e) {
			// TODO Auto-generated catch block
        	e.printStackTrace();
			System.out.println("error: Input output error");
		}
	}


	private  void Exchange(String input_A, String input_B, boolean specialChar) {
		System.out.println("inside exchange");
		//going to check the entire array for input_A and whenever we find it we swap it with B
		int matches=0;
		String change=input_B;
		if (specialChar == true) {
			change = input_A + input_B;
		}
		Node tra = root;
		for(int q=0; q<arr.length;q++) {
			try {
				if (arr[q] != null) {
			if(arr[q].equals(input_A) ) {
				matches++;
        		System.out.println("Found the input " + input_A + " in the array at the index " +  q + " and swapped it out for "+ change);
				// if they are equal first we swap them out in the array, then we swamp it out in the tree
				arr[q] = change;
				
				//now we swap in the tree
				
	    		// Now we find the path from 0 to parent
	    		// first find the length of the array
	    		//Find the route back to zero
	    		int findArraylength =1;
	    		for (int toZero=q; toZero != 0; findArraylength++) {
	    			toZero = (toZero-1)/3;
	    		}
	    		System.out.println("the instr size of the array is " + findArraylength);
	    		int instrArray[] = new int[findArraylength];
	    		// now going to add numbers to the arrays.
	    		//  We put in the direction of the node we are going to 
	    		int into= q;
	    		for(int i=0; i < findArraylength; i++) {
	    			instrArray[i]=into;
	    			into =(into-1)/3;
	    		}
	    		
//	    		System.out.println(instrArray[0]);
	    		Arrays.sort(instrArray);
//	    		System.out.println(instrArray[0]);
	    		
	    		// Once I get the instruction Array we move the node there
	    		for (int i=0; i< instrArray.length; i++) 
	    		{
//	    			 	System.out.println("inside AddL else statement");
	    				double temp= ((double)instrArray[i]/3);
	    				temp = temp -(instrArray[i]/3);
	    				temp = Math.round(temp*100.0)/100.0;
//	    				System.out.println("current value of temp is "+ temp + "and the position is " + instrArray[i]);
	    				
	    				if (instrArray[i] ==0) {
	    					tra = tra;
	    				}
	    				else if (temp ==0 && (instrArray[i]%3) ==0) {
//	     					System.out.println("in move right");
	    					tra = tra.rightNode;
	    				}
	    				else if (temp == 0.33) {
//	     					System.out.println("in move left");
	    					tra = tra.leftNode;
	    				}
	    				else if (temp == 0.67) {
//	     					System.out.println("in move middle");
	    					tra = tra.MiddleNode;
	    				} else {
	    					System.out.println("don't know what to do with the value " + temp);
	    				}
	    		}
	    		// now we swap the payloads
	    		System.out.println("the payload we are switching out is " + tra.payload);
	    		tra.payload = change;
				tra = root;

			}
				}
		} catch(NullPointerException e) {
			System.out.println("null pointer error in Exchange. " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		}
		if (matches ==0) {
			System.out.println("Zero exchanges occurred in the Exchange method. " + "input a was " + input_A + " and the new was supposed to be " + change);
		}

	}


	private void deleteFromArray(int delIndex, int maxDepthBeforeDelete)
 {
		// need to find the children of this index and delete them also
		//going to create a new array instead
		System.out.println("inside delete from array");
		 int localMax=1;
		 System.out.println("max depth is "+maxDepthBeforeDelete);
//		 int depth = maxDepth(root);
		 for (int g= 1; g<maxDepthBeforeDelete; g++) {
			 localMax +=(int) + Math.pow(3,g);
//			System.out.println("creating local max depth is " + localMax);
		 }
		 maxElement =localMax;
		 /*critical ne addition
		 if (delIndex <= arr.length-1) {
		 arr[delIndex] =null;
		 String temp[] = new String[maxElement];
		 System.out.println("temp arr has length of " + temp.length);
		 //copy old array into new array
		 int limited=arr.length;
		 if (temp.length < arr.length) {
			 limited = temp.length;
		 }
		 for (int h=0; h < limited; h++) {
			 temp[h] = arr[h];
		 }
		 arr = temp;
		 }
		 /*critical ne addition*/

		 
		 System.out.println("delIndex is "+delIndex +" max is "+ localMax + " arr.length is " + arr.length);
		int lolMax = arr.length;
		 
		int maxLeftMost=0;
		int maxrightMost=0;
		 maxLeftMost = (delIndex *3)+1;
		 maxrightMost = (delIndex*3)+3;
		 do {
			 System.out.println("In del Array leftmost is " + maxLeftMost + " rightmost is " +maxrightMost);
			 if (maxrightMost < arr.length) {
			 for(int p=maxLeftMost; p <= maxrightMost; p++) {
				 if(p<= maxrightMost) {
					 arr[p] = null;
				 }
			 }
			 }
			 maxLeftMost = (maxLeftMost*3)+1;
			 maxrightMost = (maxrightMost*3)+3;

		 }while(maxrightMost < arr.length);
		 System.out.print("The payload at delIndex " + delIndex + " which is "+ arr[delIndex] + " has be set to " );
		 arr[delIndex] =null;
		 System.out.println(arr[delIndex]);
	}


	private  void closeEveryThing() throws IOException 
	{
	      bw.close();
      writer.close();
      readerScanner.close();		
	}
	
	private int instruction_was_DEL(String readData, String input_a) 
	{
        final String DELLR_REGEX = "^[D][e][l][R]{1}[(](.*)[)]"; 
        final String DELL_REGEX = "^[D][e][l][L]{1}[(](.*)[)]";
        final String DELM_REGEX = "^[D][e][l][M]{1}[(](.*)[)]";
		
//    	System.out.println("yes "+ readData  + "has Add_(____)");
        Pattern myPattern;
        Matcher myMatcher;
        Pattern myPattern2;
        Matcher myMatcher2;
        Pattern myPattern3;
        Matcher myMatcher3;
        
        int retIndex;
        Node travel = root;
        int IndexOfRightMost=-5;
        int  IndexIs=0;

    	
    	// checking if the argument format is valid
    		
    		System.out.println("In instrction Del, the input a is "+ input_a); 
    		//starting from the right most element
            for (int p=arr.length-1; p>=0; p--) 
            {
//            	System.out.print("The elemenet in the array are "+arr[p] +"p is " + p );
            	try {
            		if (arr[p] != null) {
            	if (arr[p].equals(input_a)) {
            		System.out.println("In Del, a is found inside the node at the index of "+ p + "arr[p] is " + arr[p]);
            		// find path from 0 to parent
            		// first find the length of the array
            		IndexOfRightMost = p;
            		break;
            	} 
            	else {
            		//System.out.println("couldn't perform Del because we couldn't find the element " + input_a + "arr[p] is " + arr[p]);
            	}
            		}
            	} 
            	catch (NullPointerException e) {
                	//System.out.printf("arr[" + p +"] is " + arr[1] + "\n");
            	}
            }
            
            // once we found the index of the right most we check that level to see if there one to the left
            System.out.println("Inside of Del, the total max element is " + maxElement + "the right most element is " + IndexOfRightMost);
            int rightBoundary=maxElement-1;
            int leftBoundary = (rightBoundary/3);
            // we look for the boundary of IndexOFRightMost
//        	System.exit(1);
            while(rightBoundary  > 0) {
                System.out.println("Inside while loop the boundary of the element at index " + IndexOfRightMost + " is " + leftBoundary + ", " + rightBoundary);
                if (leftBoundary <= IndexOfRightMost   && IndexOfRightMost <= rightBoundary) {
            		break;
            	} else {
            		rightBoundary = (rightBoundary-1)/3;
            		leftBoundary = rightBoundary/3;
            	}
            }
            
            System.out.println("the boundary of the element at index " + IndexOfRightMost + " is " + leftBoundary + ", " + rightBoundary);
//        	System.exit(1);
            //once we find the boundaries we then find the lowest index
            for (int i=leftBoundary; i <= rightBoundary; i++) {
            	try {
            	if (arr[i].equals(input_a)) {
            		System.out.println("The lowest index on the highest level where "+ input_a +" is found is " + i);

            		IndexIs = i;
            		break;
            }
            	}catch(NullPointerException e) {
            		
            	}
           }
    		// Now we find the path from 0 to parent
    		// first find the length of the array
    		//Find the route back to zero
    		int findArraylength =1;
    		for (int toZero=IndexIs; toZero != 0; findArraylength++) {
    			toZero = (toZero-1)/3;
    		}
    		System.out.println("the instr size of the array is " + findArraylength);
    		int instrArray[] = new int[findArraylength];
    		// now going to add numbers to the arrays.
    		//  We put in the direction of the node we are going to    		int into= IndexIs;
    		int into= IndexIs;
    		for(int i=0; i < findArraylength; i++) {
    			instrArray[i]=into;
    			into =(into-1)/3;
    		}
    		
//    		System.out.println(instrArray[0]);
    		Arrays.sort(instrArray);
//    		System.out.println(instrArray[0]);
    		
    		// Once I get the instruction Array we move the node there
    		Node move = root;
    		System.out.println("root is" + move.payload);
    		for (int i=0; i< instrArray.length; i++) 
    		{
    			try {
//    			 	System.out.println("inside AddL else statement");
    				double temp= ((double)instrArray[i]/3);
    				temp = temp -(instrArray[i]/3);
    				temp = Math.round(temp*100.0)/100.0;
//    				System.out.println("current value of temp is "+ temp + "and the position is " + instrArray[i]);
    				
    				if (instrArray[i] ==0) {
     					System.out.println("stay");
    					move = move;
    				}
    				else if (temp ==0 && (instrArray[i]%3) ==0) {
     					System.out.println("in move right");
    					move = move.rightNode;
     					System.out.println("root.rightNode is " + move.rightNode );

    				}
    				else if (temp == 0.33) {
     					System.out.println("in move left");
     					move = move.leftNode;
    				}
    				else if (temp == 0.67) {
     					System.out.println("in move middle");
    					move = move.MiddleNode;
    				} else {
    					System.out.println("don't know what to do with the value " + temp);
    				}
    			}catch(NullPointerException e) {
                	e.printStackTrace();
    	            }
    		}
    		// next I check if the node if the instruction was to del left right or Middle 
    		
            myPattern = Pattern.compile(DELLR_REGEX);
            myMatcher = myPattern.matcher(readData);
    		
            myPattern2 = Pattern.compile(DELL_REGEX);
            myMatcher2 = myPattern2.matcher(readData);
            
            myPattern3 = Pattern.compile(DELM_REGEX);
            myMatcher3 = myPattern3.matcher(readData);
    		
            // checking if it was a DelR instruction
            try {
            if (myMatcher.find()) {
            	System.out.println("Deleting the rightNode ");
//            	System.out.println("move.rightNode.payload is" + root.rightNode.payload);
//            	move.rightNode.payload =null;
            	move.rightNode = null;
            	retIndex = (IndexIs*3)+3;
            	System.out.println("move.rightNode is" + root.rightNode);
            	System.out.println("Deleted every in the rightNode " + move.rightNode);
            	return retIndex;
            } 
            // if it was DELL
            else if (myMatcher2.find()) {
            	System.out.println("Deleting the leftNode " + ((IndexIs*3)+1) + "max element is " + maxElement + "arr at index is " /*+ arr[((IndexIs*3)+1)]*/ + "root.payload is " );
//            	if(move.leftNode != null) {
//            	System.out.println("Deleting " +move.leftNode.payload+ " the index is "+ ((IndexIs*3)+1));
            	retIndex = (IndexIs*3)+1;
//            	move.leftNode.payload =null;
            	move.leftNode = null;
            	System.out.println("Deleted every in the LeftNode ");
            	return retIndex;
//            	} else {
////            		System.out.println("root.payload is " + root.rightNode.payload +" move.payload is "+ move.payload);
//                	System.out.println("root.leftNode doesn't exist ");
//            	}
            }
            // if it was DELM
            else if (myMatcher3.find()) {
            	System.out.println("Deleting the MiddleNode ");
            	retIndex = (IndexIs*3)+2;
//            	move.MiddleNode.payload =null;
            	move.MiddleNode = null;
            	System.out.println("Deleted every in the MiddleNode " + move.MiddleNode);
            	return retIndex;
            } else {
            	System.out.println("didn't match with any delete Left, Right or Middle ");
            }
            
            } catch(NullPointerException e) {
//            	System.out.println("Null error:when trying to do. " + e.getLocalizedMessage());
            	//e.printStackTrace();
            }
//            catch(Exception e) {
//            }
	return -1;
	}


}
