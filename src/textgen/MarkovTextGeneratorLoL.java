package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) throws NullPointerException
	{
		// TODO: Implement this method
		if (sourceText == null)
			throw new NullPointerException("empty sourceText");
		String[] words = sourceText.split(" ");
		int len = words.length;
		if (len == 0)
			throw new NullPointerException("empty array");
		starter = words[0];
		String prevNode = starter;
		
		for (int i = 1; i <= len; i ++) {
			String w = words[i % len];
			boolean inList = false;
			for (ListNode word : wordList) {
				if (prevNode.equals(word.getWord())) {
					inList = true;
					word.addNextWord(w);
				}
			}
			if (inList == false) {
				ListNode pNode = new ListNode(prevNode);
				wordList.add(pNode);
				pNode.addNextWord(w);
			}
			prevNode = w;
		}
		
		/** test train. if you train your generator on the string "hi there hi Leo",
		 *  calling toString on the MarkovTextGenerator should produce:
		 *  hi: there->Leo->
		 *  there: hi->
		 *  Leo: hi->
		 */
        /* for (ListNode word : wordList) {
			System.out.println(word.toString());
		} */
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) throws IllegalArgumentException {
	    // TODO: Implement this method
		if (numWords < 0)
			throw new IllegalArgumentException("illegal argument");
		if (!wordList.isEmpty())
			starter = wordList.get(0).getWord();
		String currWord = starter;
		String output = "";
		if (numWords == 0)
			return output;
		output += starter;
		for (int numGen = 1; numGen < numWords; numGen ++) {
			for (ListNode node : wordList) {
				if (currWord.equals(node.getWord())) {
					String w = node.getRandomNextWord(rnGenerator);
					output = output + " " + w;
					currWord = w;
					break;
				}
			}
		}
		
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) throws NullPointerException
	{
		// TODO: Implement this method.
		if (sourceText == null)
			throw new NullPointerException("empty sourceText");
		String[] words = sourceText.split(" ");
		int len = words.length;
		if (len == 0)
			throw new NullPointerException("empty array");
		starter = words[0];
		String prevNode = starter;
		wordList.clear();
		
		for (int i = 1; i <= len; i ++) {
			String w = words[i % len];
			boolean inList = false;
			for (ListNode word : wordList) {
				if (prevNode.equals(word.getWord())) {
					inList = true;
					word.addNextWord(w);
				}
			}
			if (inList == false) {
				ListNode pNode = new ListNode(prevNode);
				wordList.add(pNode);
				pNode.addNextWord(w);
			}
			prevNode = w;
		}
	}
	
	// TODO: Add any private helper methods you need here.


	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		/* test 
		String textString = "hi there hi Leo";
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		gen.train(textString);
		//System.out.println(gen);
		//System.out.println(gen.generateText(20));
		//String text2 = "hi hello hi this hi that";
		//gen.retrain(text2);
		gen.train("");
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		*/
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
	    return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


