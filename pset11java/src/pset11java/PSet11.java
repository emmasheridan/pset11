package pset11java;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;



public class PSet11 {
	
	/* Block class to handle object elements (width, height, depth, area) */
	static class Block implements Comparable<Block> {

		int height, width, depth, area;
		
		// block object
		public Block(int height, int width, int depth) {
			this.height = height;
			this.width = width;
			this.depth = depth;
		}
		
		// compare areas
		@Override
		public int compareTo(Block o) {
			return o.area-this.area;
		}
	}

	
	/* Create all the possible block rotations */ 
	private static Block[] blockOptions(Block blocks[], int num) {

		// number of new rotated blocks
	    int len = num*3;
	    
	    // array for rotated blocks 
		Block[] rotArray = new Block[len]; 

		// create all combinations of width, height, and depth 
		for(int i = 0; i < num; i++){
	    	
	    		Block block = blocks[i];
	    	
	        rotArray[3*i] = new Block(block.height, Math.max(block.width, block.depth), Math.min(block.width, block.depth));
	        rotArray[3*i + 1] = new Block(block.width, Math.max(block.height, block.depth), Math.min(block.height, block.depth));	
	        rotArray[3*i + 2] = new Block(block.depth, Math.max(block.width, block.height), Math.min(block.width, block.height));	
	        
	    }
	    
	    return rotArray;

	}

	/* Sort all possible block options largest to smallest by surface area of the base*/
	private static Block[] sortBlocks(Block[] rotArray) {
	    
		for(int i = 0; i < rotArray.length; i++) {
			rotArray[i].area = rotArray[i].width * rotArray[i].depth;
		}
		
		Arrays.sort(rotArray);
	    return rotArray;
	}

	/* Find the tower with maximum height using a DP table */
	private static int[] maxTower(Block[] blockArr, int num){
	    
	    int len = num*3;
	    int[] dpTable = new int[len];
	    
	    // initialize values in the 1-dimensional DP table to just the height of the top block
	    for(int i = 0; i < len; i++){
	        dpTable[i] = blockArr[i].height;
	    }

	    // use the DP table to maximize the height of the blocks that can fit underneath the given top block
	    for(int i = 0; i < len; i++){
	        dpTable[i] = 0;

	        // current specified top block
	        Block currBlock = blockArr[i];
	        // height of the block or blocks that fit under the specified top block
	        int prevHeight = 0;

	        // look at all the blocks with bigger base areas than block i
	        for(int j = 0; j < i; j++){
	            Block prevBlock = blockArr[j];
	            
	            // if the length and width of the current top block are strictly smaller than the length and width of the block to be placed underneath
	            if(currBlock.width < prevBlock.width && currBlock.depth < prevBlock.depth){
	                // update currHeight to reflect whether the maximum tower height has increased
	                prevHeight = Math.max(prevHeight, dpTable[j]);
	            }
	        }
	        // add the max height of the previous blocks to the height of the current top block to get the maximum tower height given the specified top block
	        dpTable[i] = prevHeight + currBlock.height;
	    }
	    
	    return dpTable;

	}

	/* Lookup max height tower from DP table */
	private static int maxHeight(int[] dpTable, int num){
	    int len = num*3;
	    int max = -1;
	    
	    for(int i = 0; i < len; i++){
	        max = Math.max(max, dpTable[i]);
	    }

	    return max;
	}


	/* Testing block stacking */ 
	public static void main(String [] args) throws IOException {

//		// an example 
//		Block[] exblockarray = new Block[3];
//		exblockarray[0] = new Block(1,2,3);
//		exblockarray[1] = new Block(4,3,6);
//		exblockarray[2] = new Block(2,7,5);
//
//	    Block[] blockArr = blockOptions(exblockarray, 3);
//	    blockArr = sortBlocks(blockArr);
//	    int[] dpTable = maxTower(blockArr, 3);
//	    int height = maxHeight(dpTable, 3);
		
			
		String fileName = args[0]; // input the file name as an argument 
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		// read the first line for the number of blocks
		String line;
		line = br.readLine();
		int numBlocks = Integer.parseInt(line.trim());
		Block[] blockArr = new Block[numBlocks];
				
		int counter = 0;
		
		// read in the remaining lines and create blocks 
		while(line != null && counter < numBlocks) {
			
			// split the line into its 3 numbers
			line = br.readLine(); 
			String[] tempArray; 
			
			tempArray = line.split(" ");
			
			int height = Integer.parseInt(tempArray[0]);
			int width = Integer.parseInt(tempArray[1]);
			int depth = Integer.parseInt(tempArray[2]);
			
			// create block with the parsed h, w, and d; add to block array
			Block block = new Block(height, width, depth);
			blockArr[counter] = block;
			counter++;
			
		}
		br.close();		
				
		// find max height tower
		Block[] blockOpt = blockOptions(blockArr, numBlocks);
	    blockOpt = sortBlocks(blockOpt);
	    int[] dpTable = maxTower(blockOpt, numBlocks);
	    int height = maxHeight(dpTable, numBlocks);
	    
	    Block[] outputBlocks = null;
		
				
	    // CALCULATE THIS
	    // number of blocks in the tower
	    int numBlocksAns = 0;

	    System.out.println("The tallest tower has " + numBlocksAns + " blocks and a height of " + height);
	    
	    // create output file with name of second argument 
	    PrintWriter writer = new PrintWriter(args[1], "UTF-8");
	    writer.println(numBlocksAns);
	    for(int i = 0; i < outputBlocks.length; i++) {
	    		writer.println(outputBlocks[i].height + " " + outputBlocks[i].width + " " + outputBlocks[i].depth);
	    }
	    writer.close();


	    
	}
	
	
	
	
	
}
