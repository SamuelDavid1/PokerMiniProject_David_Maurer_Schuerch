package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import poker.version_graphics.model.Card.Rank;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush, RoyalFlush;
    
    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        
        if (isOnePair(cards)) currentEval = OnePair;
        if (isTwoPair(cards)) currentEval = TwoPair;
        if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
        if (isStraight(cards)) currentEval = Straight;
        if (isFlush(cards)) currentEval = Flush;
        if (isFullHouse(cards)) currentEval = FullHouse;
        if (isFourOfAKind(cards)) currentEval = FourOfAKind;
        if (isStraightFlush(cards)) currentEval = StraightFlush;
        if (isRoyalFlush(cards)) currentEval = RoyalFlush; 
        
        return currentEval;
    }
    
    public static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) found = true;
            }
        }
        return found;
    }
    public static Rank getPairRank(ArrayList<Card> cards) {
    	boolean found = false;
    	Rank searchedRank = null;
    	if(isOnePair(cards) == true) {
    		 for (int i = 0; i < cards.size() - 1 && !found; i++) {
    	            for (int j = i+1; j < cards.size() && !found; j++) {
    	                if (cards.get(i).getRank() == cards.get(j).getRank()) {
    	                	found = true;
    	                	searchedRank = cards.get(i).getRank();
    	                }
    	            }
    	        }
    		 return searchedRank;
    		
    	} else {
    		return null;
    	}
    }
    
    public static Rank getHighestRankIfOnePair(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	boolean found = false;
    	 Rank searchedRank = null;
         for (int i = 0; i < clonedCards.size() - 1 && !found; i++) {
             for (int j = i+1; j < clonedCards.size() && !found; j++) {
                 if (clonedCards.get(i).getRank() == cards.get(j).getRank()) { 
                	 found = true;
                	 clonedCards.remove(j);
                	 clonedCards.remove(i);
                 } 
             }
         }
         Collections.sort(clonedCards);
         searchedRank = clonedCards.get(clonedCards.size() - 1).getRank();
    	
    	return searchedRank;
    }
    
    public static boolean isTwoPair(ArrayList<Card> cards) {
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        // If a first pair was found, see if there is a second pair
        return firstPairFound && isOnePair(clonedCards);
    }
    
    public static Rank getHighestRankIfTwoPair(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	Rank searchedRank = null;
        
        int tries = 0;
        if(tries < 2) {
        	for (int i = 0; i < clonedCards.size() - 1; i++) {
        		for (int j = i+1; j < clonedCards.size(); j++) {
        			if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
        				
        				clonedCards.remove(j); 
        				clonedCards.remove(i);  
        				tries++;
        			}
        		}
        	}
        }
        searchedRank = clonedCards.get(clonedCards.size() - 1).getRank();    	
    	return searchedRank;
    }
//    public static Rank getTwoPairRank(ArrayList<Card> cards) {
//    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
//    	Rank searchedRankOne = null;
//    	Rank searchedRankTwo = null;
//        
//        int tries = 0;
//        if(tries < 2) {
//        	for (int i = 0; i < clonedCards.size() - 1; i++) {
//        		for (int j = i+1; j < clonedCards.size(); j++) {
//        			if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
//        				searchedRankOne = clonedCards.get(i).getRank();
//        				clonedCards.remove(j); 
//        				clonedCards.remove(i);  
//        				tries++;
//        			}
//        		}
//        	}
//        }
//        if(tries >= 2) {
//        	for (int i = 0; i < clonedCards.size() - 1; i++) {
//        		for (int j = i+1; j < clonedCards.size(); j++) {
//        			if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
//        				clonedCards.remove(j); 
//        				clonedCards.remove(i);  
//        				tries++;
//        			}
//        		}
//        	}
//        }
//        
//        return null;
//    }
    
    public static boolean isThreeOfAKind(ArrayList<Card> cards) {
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
        
        int sameRank = 0;
        	for (int i = 0; i < clonedCards.size() - 1; i++) {
        		for (int j = i+1; j < clonedCards.size(); j++) {
        				if(clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
        					sameRank++;
        				}
        		}
        	}
        if(sameRank >= 3) {
        	return true;
        } else {
        	return false;
        } 
    }
 
    private static Rank getThreeOfAKindRank(ArrayList<Card> cards) {
    	   ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
           
    	   Rank searchedRank = null;
           int sameRank = 0;
           	for (int i = 0; i < clonedCards.size() - 1; i++) {
           		for (int j = i+1; j < clonedCards.size(); j++) {
           				if(clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
           					sameRank++;
           					if(sameRank == 3) {
           						searchedRank = clonedCards.get(i).getRank();
           					}
           				}
           		}
           	}
           if(sameRank >= 3) {
           	return searchedRank;
           } else {
        	   return null;
           }             
       }
    	
    public static Rank getHighestRankIfThreeOfAKind(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	
    	Rank threeRank = getThreeOfAKindRank(clonedCards);
    	Rank searchedRank = null;
	    	
	    	 Iterator<Card> c = clonedCards.iterator();
	    	 while(c.hasNext()){
	    		 Card card = c.next();
	    		 if(card.getRank() == threeRank) {
	    			 c.remove();
	    		 }
	    	 }
	    	 
	    Collections.sort(clonedCards);
    	
    	searchedRank = clonedCards.get(clonedCards.size() - 1).getRank();
    	
    	return searchedRank;
    }
    
    public static boolean isStraight(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	boolean straightFound = false;
    	int successfulTries = 0;
    	boolean next = true;
    	
    	Collections.sort(clonedCards);

    		for(int i = 0; i < clonedCards.size() && next; i++) {
    			if(clonedCards.get(i).getRank().compareTo(clonedCards.get(i+1).getRank()) == -1) {
    				successfulTries++;
    			} else {
    				next = false;
    			}
    			if(successfulTries == 4) {
    				next = false;
    				straightFound = true;
    			}	
    		}
    	 
    	return straightFound;
    }
    
    public static boolean isFlush(ArrayList<Card> cards) {
    	boolean flushFound = false;
    	int successfulTries = 0;
    	boolean next = true;
    	
    	for(int i = 0; i < cards.size() && next; i++) {
    		if(cards.get(i).getSuit().compareTo(cards.get(i+1).getSuit()) == 0) {
    			successfulTries++;
    		} else {
    			next = false;
    		}
    		if(successfulTries == 4) {
				next = false;
				flushFound = true;
			}	
    	}
    	
      return flushFound; 
    }
 
    public static boolean isFullHouse(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	Rank searchedRank;
        boolean foundThree = false;
         
         foundThree = isThreeOfAKind(clonedCards);
         System.out.println(foundThree);
        
         if(foundThree == true) {
        	 searchedRank = getThreeOfAKindRank(clonedCards);
        	 System.out.println(searchedRank);
        	 
        	 Iterator<Card> c = clonedCards.iterator();
        	 while(c.hasNext()){
        		 Card card = c.next();
        		 if(card.getRank() == searchedRank) {
        			 c.remove();
        		 }
        	 }
        	 
        	 System.out.println(clonedCards);
        	 
        	 return(foundThree && isOnePair(clonedCards));
        	 
         } else {
        	 return false;
         }
              
    }
        		
    public static boolean isFourOfAKind(ArrayList<Card> cards) {
    	
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
        
        int sameRank = 0;
        	for (int i = 0; i < clonedCards.size() - 1; i++) {
        		for (int j = i+1; j < clonedCards.size(); j++) {
        				if(clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
        					sameRank++;
        				}
        		}
        	}
        if(sameRank >= 4) {
        	return true;
        } else {
        	return false;
        } 
    }
    
    public static Rank getHighestRankIfFourOfAKind(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
        
    	Rank searchedRank = null;
    	Rank fourRank = null;
        int sameRank = 0;
        	for (int i = 0; i < clonedCards.size() - 1; i++) {
        		for (int j = i+1; j < clonedCards.size(); j++) {
        				if(clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
        					sameRank++;
        					if(sameRank == 4) {
        						fourRank = clonedCards.get(i).getRank();
        					}
        				}
        		}
        	}
        	
        	Iterator<Card> c = clonedCards.iterator();
	    	 while(c.hasNext()){
	    		 Card card = c.next();
	    		 if(card.getRank() == fourRank) {
	    			 c.remove();
	    		 }
	    	 }
        	
	    	 searchedRank = clonedCards.get(clonedCards.size() - 1).getRank();
        	
        	if(sameRank >= 4) {
        		return searchedRank;
        	} else {
        		return null;
        	}
    }
    
    public static boolean isStraightFlush(ArrayList<Card> cards) {
             
        return isStraight(cards) && isFlush(cards);   
    }
    
    public static boolean isRoyalFlush(ArrayList<Card> cards) {
    	boolean lastCardAce = false;
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	Collections.sort(clonedCards);
    	
    	lastCardAce = (clonedCards.get(clonedCards.size()-1).getRank() == Rank.Ace) ? true : false;
       
    	return isStraightFlush(clonedCards) && lastCardAce;
    }
}
