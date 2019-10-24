package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;

public class Player implements Comparable<Player> {
    public static final int HAND_SIZE = 5;
    
    private final String playerName; // This is the ID
    private final ArrayList<Card> cards = new ArrayList<>();
    private HandType handType;
    
    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public void addCard(Card card) {
        if (cards.size() < HAND_SIZE) cards.add(card);
    }
    
    public void discardHand() {
        cards.clear();
        handType = null;
    }
    
    public int getNumCards() {
        return cards.size();
    }

    /**
     * If the hand has not been evaluated, but does have all cards, 
     * then evaluate it.
     */
    public HandType evaluateHand() {
        if (handType == null && cards.size() == HAND_SIZE) {
            handType = HandType.evaluateHand(cards);
        }
        return handType;
    }

    /**
     * If two hands are the same compare the highest ranked card
     * returning 1, 0 or -1 depending on whether the highest given card is higher, equal or lower than the other
     */
   
    	// not done yet:
//    private int compareHighestRank(Player p) {
//		ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
//		ArrayList<Card> pClonedCards = (ArrayList<Card>) p.getCards().clone();
//	
//    	Collections.sort(clonedCards);
//		Collections.sort((pClonedCards));
//		
//		if(clonedCards.get(clonedCards.size() - 1).getRank().compareTo(pClonedCards.get(pClonedCards.size() - 1).getRank()) > 1) {
//			return 1;
//		}
//		if(clonedCards.get(clonedCards.size() - 1).getRank().compareTo(pClonedCards.get(pClonedCards.size() - 1).getRank()) < 1) {
//			return -1;
//		} else {
//			return 0;
//		}
//	}
    /**
     * Hands are compared, based on the evaluation they have.
     * Added compareHighestRank method in order to be able to find a winner in case of a tie.
     */
    @Override
    public int compareTo(Player o) {
//      not done yet:  
//    	if(handType.compareTo(o.handType) == 0) {
//        	return this.compareHighestRank(o);
//        }
    	
    	return handType.compareTo(o.handType);
    }
}
