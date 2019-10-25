package poker.version_graphics.model;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import poker.version_graphics.model.Card.Rank;

public class HandTypeTest {
	// We define the hands using abbreviations. The code at the bottom
	// of this class can translate one of these strings into a card.
	//
	// Another method takes a set of five cards, and translates the whole hand
	//
	// Yet another method does this for a whole set of hands
	private static String[][] highCards = {
			{ "2S", "9C", "3H", "5D", "7H" },
			{ "7S", "5C", "AH", "JD", "6H" },
			{ "2S", "3S", "4S", "5H", "7S" },
			{ "AS", "KC", "QH", "6D", "TH" }
			};
	
	private static String[][] pairs = {
			{ "2S", "2C", "3H", "5D", "7H" },
			{ "2S", "AC", "3H", "5D", "AH" },
			{ "3S", "2C", "3H", "KD", "QH" },
			{ "9S", "2C", "2H", "5D", "7H" }
			};

	private static String[][] twoPairs = {
			{ "2S", "2C", "7H", "5D", "7H" },
			{ "2S", "AC", "5H", "5D", "AH" },
			{ "3S", "2C", "3H", "2D", "QH" },
			{ "9S", "2C", "2H", "5D", "5H" }
			};
	
	private static String[][] threeOfAKinds = {
			{ "2S", "2C", "2H", "5D", "7H" },
			{ "2S", "5C", "5H", "5D", "AH" },
			{ "3S", "3C", "4H", "3D", "QH" },
			{ "9S", "2C", "2H", "2D", "5H" }
			};
	
	private static String[][] straights = {
			{ "2S", "3C", "4H", "5D", "6H" },
			{ "6S", "7C", "5H", "4D", "3H" },
			{ "AS", "JC", "QH", "KD", "TH" },
			{ "9S", "TC", "8H", "7D", "JH" }
			};
	
	private static String[][] flushs = {
			{ "2S", "3S", "QS", "5S", "6S" },
			{ "6D", "7D", "2D", "4D", "9D" },
			{ "AH", "JH", "5H", "KH", "TH" },
			{ "6C", "TC", "8C", "QC", "JC" }
			};
	
	private static String[][] fullHouses = {
			{ "2S", "2C", "2H", "5C", "5S" },
			{ "6S", "6D", "9H", "9C", "9D" },
			{ "AH", "2C", "AD", "2H", "AC" },
			{ "6C", "8D", "8C", "8H", "6H" }
			};
	
	private static String[][] fourOfAKinds = {
			{ "2S", "2C", "2H", "2D", "5S" },
			{ "9S", "6D", "9H", "9C", "9D" },
			{ "AH", "2C", "2D", "2H", "2S" },
			{ "8S", "8D", "8C", "8H", "6H" }
			};
	
	private static String[][] straightFlushs = {
			{ "2S", "3S", "4S", "5S", "6S" },
			{ "6D", "7D", "5D", "4D", "3D" },
			{ "9H", "JH", "QH", "KH", "TH" },
			{ "9C", "TC", "8C", "7C", "JC" }
			};
	
	private static String[][] royalFlushs = {
			{ "AS", "QS", "TS", "JS", "KS" },
			{ "TD", "AD", "QD", "KD", "JD" },
			{ "AH", "JH", "QH", "KH", "TH" },
			{ "QC", "KC", "AC", "TC", "JC" }
			};
	
	private static String[][] highs = {
			{ "2S", "2C", "2H", "2D", "5S"},
			{ "2S", "2C", "3H", "5D", "7H"  },
			{ "2S", "5C", "5H", "5D", "AH" },
			{ "QC", "KC", "AC", "TC", "JC" }
			};
	
	// This is where we store the translated hands
	ArrayList<ArrayList<Card>> highCardHands;
	ArrayList<ArrayList<Card>> pairHands;
	ArrayList<ArrayList<Card>> twoPairHands;
	ArrayList<ArrayList<Card>> threeOfAKindHands;
	ArrayList<ArrayList<Card>> straightHands;
	ArrayList<ArrayList<Card>> flushHands;
	ArrayList<ArrayList<Card>> fullHouseHands;
	ArrayList<ArrayList<Card>> fourOfAKindHands;
	ArrayList<ArrayList<Card>> straightFlushHands;
	ArrayList<ArrayList<Card>> royalFlushHands;
	ArrayList<ArrayList<Card>> highHands;
	
	/**
	 * The makeHands method is called before each test method,
	 * and prepares the translated hands. We recreate these for
	 * each test method, in case the test method damages the data.
	 */
	@Before
	public void makeHands() {
		highCardHands = makeHands(highCards);
		pairHands = makeHands(pairs);
		twoPairHands = makeHands(twoPairs);
		threeOfAKindHands = makeHands(threeOfAKinds);
		straightHands = makeHands(straights);
		flushHands = makeHands(flushs);
		fullHouseHands = makeHands(fullHouses);
		fourOfAKindHands = makeHands(fourOfAKinds);
		straightFlushHands = makeHands(straightFlushs);
		royalFlushHands = makeHands(royalFlushs);
		highHands = makeHands(highs);
		System.out.println("Hallo");
	}
	

	/**
	 * This is a test method for the isOnePair method in HandType.
	 * We expect all HighCard hands to be false, all OnePair hands to
	 * be true, all TwoPair hands to be true, etc.
	 */
	@Test
	public void testIsOnePair() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertTrue(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertTrue(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertTrue(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertTrue(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertTrue(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isOnePair(hand));
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertFalse(HandType.isOnePair(hand));
		}
	}

	/**
	 * This is the test method for the isTwoPair in HandType.
	 */
	@Test
	public void testIsTwoPair() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertTrue(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertTrue(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertTrue(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertFalse(HandType.isTwoPair(hand));
		}
	}
	
	@Test
	public void testIsThreeOfAKind() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertTrue(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertTrue(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertTrue(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertFalse(HandType.isThreeOfAKind(hand));
		}
	}
	
	@Test
	public void testIsStraight() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : straightHands) {
			assertTrue(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertFalse(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertFalse(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertTrue(HandType.isStraight(hand));
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertTrue(HandType.isStraight(hand));
		}
	}
	
	@Test
	public void testIsFlush() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : flushHands) {
			assertTrue(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertFalse(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertFalse(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertTrue(HandType.isFlush(hand));
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertTrue(HandType.isFlush(hand));
		}
	}
	
	@Test
	public void testIsFullHouse() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertTrue(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertFalse(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isFullHouse(hand));
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertFalse(HandType.isFullHouse(hand));
		}
	}
	
	@Test
	public void testIsFourOfAKind() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertTrue(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertTrue(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertFalse(HandType.isFourOfAKind(hand));
		}
	}
	
	@Test
	public void testIsStraightFlush() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertFalse(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertTrue(HandType.isStraightFlush(hand));
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertTrue(HandType.isStraightFlush(hand));
		}
	}
	
	@Test
	public void testIsRoyalFlush() {
		for (ArrayList<Card> hand : highCardHands) {
			assertFalse(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : pairHands) {
			assertFalse(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : twoPairHands) {
			assertFalse(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : threeOfAKindHands) {
			assertFalse(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : straightHands) {
			assertFalse(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : flushHands) {
			assertFalse(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : fullHouseHands) {
			assertFalse(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : fourOfAKindHands) {
			assertFalse(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : straightFlushHands) {
			assertFalse(HandType.isRoyalFlush(hand));
		}
		for (ArrayList<Card> hand : royalFlushHands) {
			assertTrue(HandType.isRoyalFlush(hand));
		}
	}
	@Test
	public void testGetHighestRankIfOnePair() {
		ArrayList<Card> cards = pairHands.get(0);
		System.out.println(cards);
		System.out.println(HandType.getHighestRankIfOnePair(cards));
		assertTrue(HandType.getHighestRankIfOnePair(cards) == Rank.Seven);
		
		cards = pairHands.get(1);
		System.out.println(cards);
		System.out.println(HandType.getHighestRankIfOnePair(cards));
		assertTrue(HandType.getHighestRankIfOnePair(cards) == Rank.Five);
		
		
	}
	
	
//	@Test
//	public void testGetHighestRankIfTwoPair() {
//		ArrayList<Card> cards = twoPairHands.get(0);
//		System.out.println(cards);
//		System.out.println(HandType.getHighestRankIfTwoPair(cards));
//		assertTrue(HandType.getHighestRankIfTwoPair(cards) == Rank.Five);
//		
//		cards = twoPairHands.get(1);
//		System.out.println(cards);
//		System.out.println(HandType.getHighestRankIfTwoPair(cards));
//		assertTrue(HandType.getHighestRankIfTwoPair(cards) == Rank.Two);
//		
//	}
	
	@Test
	public void testGetHighestRankIfThreeOfAKind() {
		ArrayList<Card> cards = threeOfAKindHands.get(0);
		System.out.println(cards);
		System.out.println(HandType.getHighestRankIfThreeOfAKind(cards));
		assertTrue(HandType.getHighestRankIfThreeOfAKind(cards) == Rank.Seven);
		
		cards = threeOfAKindHands.get(1);
		System.out.println(cards);
		System.out.println(HandType.getHighestRankIfThreeOfAKind(cards));
		assertTrue(HandType.getHighestRankIfThreeOfAKind(cards) == Rank.Ace);
		
	}
	
	@Test
	public void testGetHighestRankIfFourOfAKind() {
		ArrayList<Card> cards = fourOfAKindHands.get(0);
		System.out.println(cards);
		System.out.println(HandType.getHighestRankIfFourOfAKind(cards));
		assertTrue(HandType.getHighestRankIfFourOfAKind(cards) == Rank.Five);
		
		cards = fourOfAKindHands.get(1);
		System.out.println(cards);
		System.out.println(HandType.getHighestRankIfFourOfAKind(cards));
		assertTrue(HandType.getHighestRankIfFourOfAKind(cards) == Rank.Six);
		
	}
	
	
	
	/**
	 * Make an ArrayList of hands from an array of string-arrays
	 */
	private ArrayList<ArrayList<Card>> makeHands(String[][] handsIn) {
		ArrayList<ArrayList<Card>> handsOut = new ArrayList<>();
		for (String[] hand : handsIn) {
			handsOut.add(makeHand(hand));
		}
		return handsOut;
	}
	
	/**
	 * Make a hand (ArrayList<Card>) from an array of 5 strings
	 */
	private ArrayList<Card> makeHand(String[] inStrings) {
		ArrayList<Card> hand = new ArrayList<>();
		for (String in : inStrings) {
			hand.add(makeCard(in));
		}
		return hand;
	}
	
	/**
	 * Create a card from a 2-character String.
	 * First character is the rank (2-9, T, J, Q, K, A) 
	 * Second character is the suit (C, D, H, S)
	 * 
	 * No validation or error handling!
	 */
	private Card makeCard(String in) {
		char r = in.charAt(0);
		Card.Rank rank = null;
		if (r <= '9') rank = Card.Rank.values()[r-'0' - 2];
		else if (r == 'T') rank = Card.Rank.Ten;
		else if (r == 'J') rank = Card.Rank.Jack;
		else if (r == 'Q') rank = Card.Rank.Queen;
		else if (r == 'K') rank = Card.Rank.King;
		else if (r == 'A') rank = Card.Rank.Ace;
		
		char s = in.charAt(1);
		Card.Suit suit = null;
		if (s == 'C') suit = Card.Suit.Clubs;
		if (s == 'D') suit = Card.Suit.Diamonds;
		if (s == 'H') suit = Card.Suit.Hearts;
		if (s == 'S') suit = Card.Suit.Spades;

		return new Card(suit, rank);
	}
}
