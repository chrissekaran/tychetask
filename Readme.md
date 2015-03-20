
## Design Considerations
  
>In order to validate user input we have used regex's to provide a first-level Validation.

>Further validation after parsing these values in the context of rover coordinates and boundary conditions is done withing the Rover class.

>NavigationService is defined to command Rover movement although the actual Rover's 
move is determined by the rover itself.

>CommandCenter is class that initializes a session and is responsible for redirecting flow back to the console and for invoking NavigationService operations
depending on whether user input validation.

> One of the considerations in the program in order for it to be able to terminate gracefully was to add another command ":q" for quitting and exiting the Command session

>Please run the program by running the NasaControl#main method



***

## A small note on Unit testing

>Unit testing uses the mockito and hamcrest libraries for mocking and matching respectively.

>Most of the unit testing has coverage but some edge cases and code paths are not quite covered as much as should be ideally.



***

Java Code Exercise Programming Instructions 
Introduction 
This exercise should take a few hours to complete. Please ensure you state any reasons and provide a list of missing functionality if you are not able to provide complete solution. 
Please submit all of the following in one zip or tar.gz file: 
Source code Unit tests (the more the better) Validation against input data Demonstrated error handling Comments as deemed necessary Documentation (as necessary) Any assumptions that you have made 
Optionally, you can also include a brief explanation of your solution (such as design considerations). 
Problem 
A squad of robotic rovers is to be landed by NASA on a plateau on Mars. This plateau, which is curiously rectangular, must be navigated by the rovers so that their on-board cameras can get a complete view of the surrounding terrain to send back to Earth. A rover's position and location is represented by a combination of x and y co-ordinates and a letter representing one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the rover is in the bottom left corner and facing North. In order to control a rover, NASA sends a simple string of letters. The possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the rover spin 90 degrees left or right respectively, without moving from its current spot. 'M' means move forward one grid point, and maintain the same heading. Assume that the square directly North from (x, y) is (x, y+1). Input: The first line of input is the upper-right coordinate of the plateau, the lower-left coordinates are assumed to be (0,0). The rest of the input is information pertaining to the rovers that have been deployed. Each rover has two lines of input. The first line gives the rover's position, and the second line is a series of instructions telling the rover how to explore the plateau. The position is made up of two integers and a letter separated by spaces, corresponding to the x and y co-ordinates and the rover's orientation. Each rover will be finished sequentially, which means that the second rover won't start to move until the first one has finished moving. Output: The output for each rover should be its final co-ordinates and heading. Example: 
Test Input  
5 5 
1 2 N 
LMLMLMLMM 
3 3 E 
MMRMMRMRRM 

Expected Output
1 3 N 
5 1 E 
