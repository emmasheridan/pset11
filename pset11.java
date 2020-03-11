

/* Create the possible block rotations */ 
private static int[][] blockOptions(int[][] inputBlocks) {





}

/* Sort all possible block options smallest to largest by first index and then by second index*/
private static int[][] sortBlocks(int[][] allBlocks) {







}






/* Find the blocks that fit on top of each other */ 
private static int[][] compareBlocks(int[][] sortedBlocks) {

    // Starting from the back of sorted blocks
    // For each block, assume that it is the top block 
    for(int i = sortedBlocks.length; i > 0; i--) {

        int numBlocks = 0; // number of blocks in current tower

        // Save height, length, and width of assumed top block
        int tempHeight, tempLength, tempWidth;
        tempLength = sortedBlocks[i][0];
        tempWidth = sortedBlocks[i][1];
        tempHeight = sortedBlocks[i][2];

        // Go through all of the possible block options to go the assumed top block
        for(int j = 0; j < sortedBlocks.length; j++) {

            // Check if j is in DP lookup table, else do ...

            
                // Call possibleTower on each j
                // possible towers will be an array that stores all the possible towers for j things 
                possibleTowers = possibleTower(sortedBlocks, );





        }



    }



}

/* Calculates a possible tower given an assumed top block and second block */ 
private static int[][] possibleTower(int[][] sortedBlocks, int[] topBlock, int[] secondBlock) {

            int[][] tower = [][];
            tower[numBlocks] = topBlock;

            // Compare the lengths and widths of assumed top with this block option
            if(topBlock[0] < sortedBlocks[j][0] && tempWidth < sortedBlocks[j][1]) {

                // if the block fits, add the height of this block option to the height with the assumed top block 
                towerHeight += sortedBlocks[j][2];
                
                // update tower as well
                tower[j][numBlocks+1] = [sortedBlocks[j][0], sortedBlocks[j][1], sortedBlocks[j][2]];
                numBlocks++; 

                // recurse 

            }

     return tower;       
}


private static int[][] computeHeights(int[][] possibleTowers) {


    // Compute all the heights for all towers 

    // Find maximum height 

    // Return tower with maximum height



}






public static void main(String [] args) {


    // initialize 1xD DP table 

    // block options 

    // sort blocks 

    // compare blocks (calls possible tower)

    // compute heights (returns a tower)

    // add tower returned from computing heights to DP table 

    // handle input 



    // compute max height using DP lookup table 
    





}