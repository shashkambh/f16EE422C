/*
 * Name: Shashank Kambhampati
 * EID: skk834
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
		ArrayList <ArrayList <Integer>> listOfMen = marriage.getMenPreference();
		ArrayList <ArrayList <Integer>> listOfWomen = marriage.getWomenPreference();
		ArrayList <Integer> matchings = marriage.getWomenMatching();
		boolean stable=true;
		
		for(int currentMan = 0; currentMan < listOfMen.size() && stable; currentMan++){
			ArrayList <Integer> manPrefList=listOfMen.get(currentMan);
			
			//Get who currentMan is paired with
		    int pairedWithMan = matchings.indexOf(currentMan);

			//Get where currentMan's partner is in his preference list
			int partnerLocation = manPrefList.indexOf(pairedWithMan);

			//Check all women that given man prefers to his current partner
			for(int womanInPrefList = 0; womanInPrefList < partnerLocation; womanInPrefList++){
				int woman=manPrefList.get(womanInPrefList);
				ArrayList <Integer> womanPrefList=listOfWomen.get(woman);
				
				//If said woman prefers him to her current partner, matching is unstable
				if(womanPrefList.indexOf(currentMan) > womanPrefList.indexOf(matchings.get(woman))){
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
		ArrayList <ArrayList <Integer>> listOfMen = marriage.getMenPreference();
		ArrayList <ArrayList <Integer>> listOfWomen = marriage.getWomenPreference();
		ArrayList <Integer> matchings = new ArrayList<>();
		
		int[] proposals = new int[listOfMen.size()];
		matchings.clear();
		ArrayList <Integer> menQueue=new ArrayList<>();
		for(int i = 0; i < listOfMen.size(); i++){
			matchings.add(-1);
			menQueue.add(i);
		}


		while(!listOfMen.isEmpty()){
			int manNumber = menQueue.get(0);
			int womanNumber = listOfMen.get(manNumber).get(proposals[manNumber]);
			proposals[manNumber]++;

			int currentEngageNumber = matchings.get(womanNumber);
			
			if(currentEngageNumber == -1){
				matchings.set(womanNumber, manNumber);
			} else {
			    ArrayList <Integer> womanPrefList = listOfWomen.get(womanNumber);
				int currentEngageRank = listOfWomen.indexOf(currentEngageNumber);
				int proposerRank = listOfWomen.indexOf(manNumber);

				if(currentEngageRank > proposerRank){
					matchings.set(womanNumber, manNumber);
					menQueue.add(currentEngageNumber);
				} else {
					menQueue.add(manNumber);
				}
			}
			
		}

		marriage.setWomanMatching(matchings);
		
		return marriage;
    }

	public static ArrayList <ArrayList <Integer>> deepCopy(ArrayList <ArrayList <Integer>> prefTable){
		ArrayList <ArrayList <Integer>> copied=new ArrayList<>();

		for(ArrayList <Integer> person : prefTable){
			copied.add(new ArrayList <Integer>());

			for(int pref : person){
				copied.get(copied.size()-1).add(pref);
			}
		}

		return copied;
	}
}
