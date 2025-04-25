package benkyosuru;

/**
 * @author dragonscale
 */
public class Grammar {
	public String name;
	public String explanation;
	public String example;
	private int id = 0;

	public Grammar(String name, String explanation, String example) {
		this.name = name;
		this.explanation = explanation;
		this.example = example;
		id++;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * @return the example
	 */
	public String getExample() {
		return example;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "id: " + id + ", name: " + name + ", explanation: " + explanation + ", example: " + example;
	}

}
