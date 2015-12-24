package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Jingjing
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size = 0;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word) throws NullPointerException
	{
	    //TODO: Implement this method.
		if (word == null)
			throw new NullPointerException();
		String lowerWord = word.toLowerCase();
		if (isWord(lowerWord))
			return false;
		char [] charWord = lowerWord.toCharArray();
		TrieNode curr = root;
		for (char c : charWord) {
			TrieNode next = curr.insert(c);
			if (next == null)
				curr = curr.getChild(c);
			else
				curr = next;
		}
		curr.setEndsWord(true);
		size ++;
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
		/* int num = preOrder(root);
	    return num;*/
		return size;
	}
/*	
	private int preOrder(TrieNode node) {
		int count = 0;
		if (node != null) {
			if (node.endsWord())
				count ++;
			
			Set<Character> childen = node.getValidNextCharacters();
			for (Character c : childen) {
				preOrder(node.getChild(c));
			}
		}
		return count;
	}
*/	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) throws NullPointerException
	{
	    // TODO: Implement this method
		if (s == null)
			throw new NullPointerException();
		String lowerS = s.toLowerCase();
		char[] charS = lowerS.toCharArray();
		TrieNode curr = root;
		for (char c : charS) {
			Set<Character> childen = curr.getValidNextCharacters();
			if (!childen.contains((Character)c))
				return false;
			else
				curr = curr.getChild(c);
		}
		if (curr.endsWord())
			return true;
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
    		 throws IllegalArgumentException
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 if (numCompletions < 0)
    		 throw new IllegalArgumentException();
    	 TrieNode curr = root;
    	 List<String> predictions = new LinkedList<String>();
    	 if (prefix != null) {
    	     String lowerPrefix = prefix.toLowerCase();
    	     char[] charPrefix = lowerPrefix.toCharArray();
 		     for (char c : charPrefix) {
 			     Set<Character> childen = curr.getValidNextCharacters();
 			     if (!childen.contains((Character)c))
 				     return predictions;
 			     else
 				     curr = curr.getChild(c);
 		     }
    	 }
    	 
 		 Queue<TrieNode> BSFNodes = new LinkedList<TrieNode>();
 		 BSFNodes.add(curr);
 		 
 		 int generated = 0;
 		 while (!BSFNodes.isEmpty() && generated < numCompletions) {
 			 TrieNode head = BSFNodes.remove();
 			 if (head.endsWord()) {
 				 predictions.add(head.getText());
 				 generated ++;
 			 }
 			 Set<Character> childen = head.getValidNextCharacters();
			 for (Character c : childen)
				 BSFNodes.add(head.getChild(c));
 		 }
 		 
         return predictions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}

 /*	
 	public static void main(String args[]) {
 		AutoCompleteDictionaryTrie emptyDict = new AutoCompleteDictionaryTrie();
 		System.out.println(emptyDict.size());
 		emptyDict.addWord("hello");
 		//emptyDict.printTree();
 		//emptyDict.printNode(emptyDict.root);
 		//System.out.println(emptyDict.isWord("hello"));
 		//System.out.println(emptyDict.size());
 		emptyDict.addWord("hi");
 		emptyDict.addWord("hey");
 		emptyDict.addWord("hurry");
 		emptyDict.addWord("hem");
 		System.out.println(emptyDict.predictCompletions("h", 4));
 		System.out.println(emptyDict.predictCompletions("", 4));
 		System.out.println(emptyDict.predictCompletions("H", 6));
 		System.out.println(emptyDict.predictCompletions("x", 4).size());
 	}
*/ 	
	
}

