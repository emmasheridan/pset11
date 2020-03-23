# Problem Set 11 
#### Maddie Dortignacq and Emma Sheridan, March 22, 2020

### About the Files
PSet11.java contains code for Block Stacking algorithm. 
PSet11.java can be run in the command line with the first argument being the name of the desired input file. An output file will contain the number of blocks and block orientation for the max height tower. 

### Documentation 
#### The Algorithm
The algorithm we implemented for the Block Stacking problem first generates all possible rotations for the blocks given as input. It then computes the area of these blocks and sorts all the blocks by area. 

#### The Code 
The code consists of 4 methods (blockOptions, sortBlocks, maxTower, maxHeight) and a class for a Block object. The 4 methods feed into each other. First, we used blockOptions to generate all combinations of the block's dimensions. We then use sortBlocks to all the block options by area. Then in maxTower, we find the combination of block options that creates the max tower (also filling in the DP table). Lastly, we use maxHeight to look into our DP for the height of the max tower. There is also a main method that handles file I/O and formats our output.

We originally tried to build our algorithm without creating a block object. Without a block object, the use of 2D arrays became complicated as well as comparing the area of blocks. After implementing a block object that holds information for the block's height, width, and depth, the code became much cleaner and easier to follow. 

We tested our code first by creating examples in our main, before the program had file I/O capability. We created some blocks that we knew the correct height for and fed them into our methods.  




