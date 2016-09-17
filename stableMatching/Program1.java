/*
 * Name: <your name>
 * EID: <your EID>
 */

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program1 extends AbstractProgram1 {
    /**
     * Determines whether a candidate Matching represents a solution to the
     * Stable Marriage problem. Study the description of a Matching in the
     * project documentation to help you with this.
	 * @param marriage The Matching to be checked
	 * @return true if the Matching is stable, false otherwise
     */
    public boolean isStableMatching(Matching marriage) {
		List<List <Integer>> listOfMen = marriage.getMenPreference();
		List<List <Integer>> listOfWomen = marriage.getWomanPreference();
		List<Integer> matchings = marriage.getWomenMatching();
		boolean stable=true;
		
		for(int currentMan = 0; currentMan < listOfMen.size() && stable; currentMan++){
			//Get who currentMan is paired with
		    int pairedWithMan = matchings.indexOf(currentMan);

			//Get where currentMan's partner is in his preference list
			int partnerLocation = listOfMen.get(currentMan).indexOf(pairedWoman);

			for(int woman = 0; woman < partnerLocation; woman++){
				if(listOfWomen.get(listOfMen.get(man).get(woman)).indexOf(currentMan) > matchings.get(woman)){
					stable = false;
				}
			}		
		}

		return stable;
			
    }


    /**
     * Determines a solution to the Stable Marriage problem from the given input
     * set. Study the project description to understand the variables which
     * represent the input to your solution.
     * @param marriage A Matching to start pairing up
     * @return A stable Matching.
     */
    public Matching stableMarriageGaleShapley(Matching marriage) {
        /* TODO implement this function */
        return null; /* TODO remove this line */
    }
}
