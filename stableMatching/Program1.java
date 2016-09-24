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
        // Get lists out of Matching object for easy access
		ArrayList <ArrayList <Integer>> listOfMen = marriage.getMenPreference();
		ArrayList <ArrayList <Integer>> listOfWomen = marriage.getWomenPreference();
		ArrayList <Integer> matchings = marriage.getWomenMatching();
		boolean stable=true;
		
        // Go through each man one at a time and check if they are in an instablity
		for(int currentMan = 0; currentMan < listOfMen.size() && stable; currentMan++){
            // Get currentMan preference list
			ArrayList <Integer> manPrefList=listOfMen.get(currentMan);
			
			// Get who currentMan is paired with
		    int pairedWithMan = matchings.indexOf(currentMan);

			// Get where currentMan's partner is in his preference list
			int partnerRanking = manPrefList.get(pairedWithMan);

			// Run through each woman
			for(int currentWoman = 0; currentWoman < manPrefList.size(); currentWoman++){
                // If currentMan prefers currentWoman to his current partner, 
                // there might be an instability
				int womanRanking=manPrefList.get(currentWoman);

                if(partnerRanking > womanRanking){

                    ArrayList <Integer> womanPrefList=listOfWomen.get(currentWoman);
                    
                    int womanPairedWith = matchings.get(currentWoman);

                    // If said woman prefers him to her current partner, matching is unstable
                    if(womanPrefList.get(currentMan) < womanPrefList.get(womanPairedWith)){
                        stable = false;
                    }
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
        // Get lists out of Matching object for easy access
		ArrayList <ArrayList <Integer>> listOfMen = marriage.getMenPreference();
		ArrayList <ArrayList <Integer>> listOfWomen = marriage.getWomenPreference();
		ArrayList <Integer> matchings = new ArrayList<>();
		
        // Keeps track of the last person that a given man proposed to
		int[] lastProposed = new int[listOfMen.size()];

        // Initialize lists needed to create matchings
        // No one is matched yet, no one proposed, and all males are in the menQueue
		ArrayList <Integer> menQueue=new ArrayList<>();
		for(int i = 0; i < listOfMen.size(); i++){
			matchings.add(-1);
			menQueue.add(i);
            lastProposed[i] = -1;
		}

        // While there is a free man, choose such a free man and have him make his next proposal.
		while(!menQueue.isEmpty()){
			int manNumber = menQueue.remove(0);
			ArrayList<Integer> manPreference = listOfMen.get(manNumber);
            int womanNumber = getNextProposal(manPreference, lastProposed[manNumber]);

            // Store who he proposed to so he can decide his next proposal
			lastProposed[manNumber] = womanNumber;

            // Get who w is currently engaged to
			int currentEngageNumber = matchings.get(womanNumber);
			
            // If w is free, m and w become engaged.
			if(currentEngageNumber == -1){
				matchings.set(womanNumber, manNumber);
			} else {
			    ArrayList <Integer> womanPrefList = listOfWomen.get(womanNumber);
				int currentEngageRank = womanPrefList.get(currentEngageNumber);
				int proposerRank = womanPrefList.get(manNumber);
                
                // If w prefers m to m'(her current partner), 
                // m and w become engaged and m' becomes single.
                // Else m remains single.
                // Whoever is left single at the end goes back to the menQueue.
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
    

    /**
     * Gets the next proposal for the given man
     * @param manPrefList The preference list for the man in question
     * @param lastProposed The last person the man proposed to
     * @return The next person that the man will propose to
     */
    public static int getNextProposal(ArrayList <Integer> manPrefList, int lastProposed){
        int ranking = 1;

        // If m has already proposed, then the current ranking 
        // he is on is that of the last woman he proposed to
        if(lastProposed != -1) {
            ranking = manPrefList.get(lastProposed);
        }
        
        // Next proposal is to next wonam on prefList of the same ranking as last proposal
        int nextWoman = manPrefList.subList(lastProposed + 1, manPrefList.size()).indexOf(ranking);
        
        // If there is no one left of the current ranking, m goes to next ranking
        // Else index needs to be adjusted because of search method
        if(nextWoman == -1){
            nextWoman = manPrefList.indexOf(ranking + 1);
        } else {
			nextWoman += lastProposed + 1;
		}
		
        return nextWoman;
    }

}
