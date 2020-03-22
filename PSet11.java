package pset11java;

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
	public static void main(String [] args) {

		// an example 
		Block[] exblockarray = new Block[3];
		exblockarray[0] = new Block(1,2,3);
		exblockarray[1] = new Block(4,3,6);
		exblockarray[2] = new Block(2,7,5);

	    Block[] blockArr = blockOptions(exblockarray, 3);
	    blockArr = sortBlocks(blockArr);
	    int[] dpTable = maxTower(blockArr, 3);
	    int height = maxHeight(dpTable, 3);
	    
	    
	   
	    //CALCULATE THIS
	    //number of blocks in the tower
	    int numBlocks = 0;

	    System.out.println("The tallest tower has " + numBlocks + " block and a height of " + height);

	    //OUTPUT FILE WITH NUMBLOCKS AND EACH BLOCK PRINTED


	    
	}
	
	
	
	
	
}
