package spelling;

import java.util.List;
import java.util.Set;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Prasoon Birla
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size=0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		//convert to LowerCaps word before inserting
		String lcWord = word.toLowerCase();
		TrieNode current = root;
		char currChar;
		for(int i = 0; i<lcWord.length(); i++) {
			currChar=lcWord.charAt(i);
			if(current.getChild(currChar)==null) 
				current = current.insert(currChar);
			else 
				current = current.getChild(currChar);
		}
		current.setEndsWord(true);
		size++;
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
		TrieNode current = root;
		String word = s.toLowerCase();
		char currChar;
		for(int i=0; i<word.length(); i++) {
			currChar = word.charAt(i);
			if(current.getChild(currChar)==null) {
				return false;
			}
			else {
				current = current.getChild(currChar);
			}
		}
		return current.endsWord();
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 List<String> predictions = new LinkedList<String>();
    	 String lcPrefix = prefix.toLowerCase();
    	 TrieNode curr = root;
    	 char currChar;
    	 
    	 for(int i =0;i<lcPrefix.length(); i++) {
    		 currChar = lcPrefix.charAt(i);
    		 if(curr.getChild(currChar)==null) 
    			 return predictions;
    		 else 
    			 curr = curr.getChild(currChar);
    	 }
    	 Set<Character> nextChars = curr.getValidNextCharacters();
    	 
    	 Queue<TrieNode> nextNodes = new Queue<TrieNode>();
    	 nextNodes.enqueue(curr);
    	 TrieNode temp; 
    	 while(!nextNodes.isEmpty() && predictions.size()<numCompletions) {
    		 temp = nextNodes.dequeue();
    		 if(temp.endsWord())
    			 predictions.add(temp.getText());
    		 for(Character c:temp.getValidNextCharacters()) {
    			 nextNodes.enqueue(temp.getChild(c));
    		 }
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
 	
 	public static void main(String []args) {
 		AutoCompleteDictionaryTrie trie = new AutoCompleteDictionaryTrie();
 		System.out.println("Adding Hello : " + trie.addWord("Hello"));
 		System.out.println("isWord Hello : " + trie.isWord("Hello"));
 		System.out.println("isWord hello : " + trie.isWord("hello"));
 		System.out.println("isWord HelLo : " + trie.isWord("HelLO"));
 		System.out.println("isWord HLO : " + trie.isWord("HLO"));
 		trie.addWord("Hen");
 		trie.printTree();
 		System.out.println("isWord Hen : " + trie.isWord("Hen"));
 	}
 	
	
}