package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
	/**
	 * Constructor initializes the dictionary as Linked List
	 */
	public DictionaryLL() {	
		this.dict = new LinkedList<>();
	}

    /** Add this word to the dictionary.  Convert it to lower case first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	if(dict.contains(word.toLowerCase()))
    		return false;
    	
    	//otherwise after converting to lower case 
    	//add word dict converting to 
    	dict.add(word.toLowerCase());
        return true;
    }


    


	/** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        return dict.contains(s.toLowerCase());
    }

    
}
