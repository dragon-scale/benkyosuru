package benkyosuru;

import java.io.Serializable;

/**
 * @author dragonscale
 */
public class Word implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4801476081680838061L;

	public String word;
	public Word_types type;
	public String pronunciation;
	public String definition;
	private int id = 0;

	public Word(String word, Word_types type, String pronunciation, String definition) {
		this.word = word;
		this.type = type;
		this.pronunciation = pronunciation;
		this.definition = definition;
		id++;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @return the type
	 */
	public Word_types getType() {
		return type;
	}

	/**
	 * @return the pronunciation
	 */
	public String getPronunciation() {
		return pronunciation;
	}

	/**
	 * @return the definition
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * @return the index
	 */
	public int getid() {
		return id;
	}

	/**
	 * @return returns the fields as a single string
	 */
	@Override
	public String toString() {
		return ("id: " + id + ", word: " + word + ", type: " + type + ", pronunciation: " + pronunciation
				+ ", definition: " + definition);
	}

}
