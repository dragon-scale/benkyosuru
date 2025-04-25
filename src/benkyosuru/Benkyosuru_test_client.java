package benkyosuru;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * a cli test client for the app to get the basic functionality working before
 * porting to a gui
 */
public class Benkyosuru_test_client {

	/**
	 * main function
	 * @param args
	 */
	public static void main(String[] args) {
		List<Word> words = new ArrayList<>();
		List<Grammar> grammar_points = new ArrayList<>();
		Scanner user_input = new Scanner(System.in);
		boolean exit_pressed = false;

		while (!exit_pressed) {
			System.out.println(
					"Please enter your selection \n1: view all words \n2: view all grammar \n3: add new word \n4: add new grammar \n5: delete word \n6: delete grammar \n7: exit \n");
			int selection = user_input.nextInt();

			switch (selection) {
				case 1: {
					// view all words
					printwords(words);
					break;
				}
				case 2: {
					// view all grammar
					printgrammar(grammar_points);
					break;
				}
				case 3: {
					// add new word
					addword(words);
					break;
				}
				case 4: {
					// add new grammar
					addgrammar(grammar_points);
					break;
				}
				case 5: {
					// delete word
					deleteword(words);
					break;
				}
				case 6: {
					// delete grammar
					deletegrammar(grammar_points);
					break;
				}
				case 7: {
					// exit
					exit_pressed = true;
					System.err.println("user exited program");
					break;
				}
				default: {
					// print to try again
					System.err.println("user inputted an incorect input");
					break;
				}
			}
		}

	}

	/**
	 * prints all items in the list
	 * @param words
	 */
	private static void printwords(List<Word> words) {
		// TODO Auto-generated method stub
		for (Word current_word : words) {
			System.out.println(current_word.toString());
		}
		System.out.println();
	}

	/**
	 * prints all items in the list
	 * @param words
	 */
	private static void printgrammar(List<Grammar> grammar_points) {
		for (Grammar current_point : grammar_points) {
			System.out.println(current_point.toString());
		}
		System.out.println();
	}

	/**
	 * prompts the user about various things of the word and constructs the word to add to the list
	 * @param words
	 */
	private static void addword(List<Word> words) {
		Word_types wtype;
		Scanner user_input = new Scanner(System.in);
		System.out.println("please enter the word: \n");
		String word_name = user_input.nextLine();
		System.out.println("please enter the Pronunciation: \n");
		String pronunciation = user_input.nextLine();
		System.out.println("what type of word is this?\n" + "1: NOUN\n" + "2: VERB\n" + "3: ADJECTIVE\n" + "4: ADVERB\n"
				+ "5: PRONOUN\n" + "6: PREPOSITION\n" + "7: CONJUNCTION\n" + "8: DETERMINER\n");
		int choice = user_input.nextInt();
		switch (choice) {
			case 1: {
				wtype = Word_types.NOUN;
				break;
			}
			case 2: {
				wtype = Word_types.NOUN;
				break;
			}
			case 3: {
				wtype = Word_types.NOUN;
				break;
			}
			case 4: {
				wtype = Word_types.NOUN;
				break;
			}
			case 5: {
				wtype = Word_types.NOUN;
				break;
			}
			case 6: {
				wtype = Word_types.NOUN;
				break;
			}
			case 7: {
				wtype = Word_types.NOUN;
				break;
			}
			case 8: {
				wtype = Word_types.NOUN;
				break;
			}
			default: {
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		}
		
		System.out.println("please enter the definition: \n");
		String def = user_input.nextLine();
		user_input.next();
		Word new_word = new Word(word_name, wtype, pronunciation, def);
		words.add(new_word);
		System.out.println();
	}

	/**
	 * prompts the user varous things about the grammar point and constructs a new grammar object to add to the list
	 * @param grammar_points
	 */
	private static void addgrammar(List<Grammar> grammar_points) {
		Scanner user_input = new Scanner(System.in);
		System.out.println("please enter the name of the grammar point: \n");
		String grammar_name = user_input.nextLine();
		System.out.println("please enter the way this point works: \n");
		String grammar_explination = user_input.nextLine();
		System.out.println("please enter an example: \n");
		String example = user_input.nextLine();
		Grammar new_grammar = new Grammar(grammar_name, grammar_explination, example);
		grammar_points.add(new_grammar);
		System.out.println();
	}

	/**
	 * deletes item based on id
	 * @param words
	 */
	private static void deleteword(List<Word> words) {
		Scanner user_input = new Scanner(System.in);
		System.out.println("please enter the id of the word you want to delete: \n");
		int deleteid = user_input.nextInt();
		words.removeIf(w -> w.getid() == deleteid);
		System.out.println();
	}

	/**
	 * deletes item based on id
	 * @param words
	 */
	private static void deletegrammar(List<Grammar> grammar_points) {
		Scanner user_input = new Scanner(System.in);
		System.out.println("please enter the id of the grammar point you want to delete: \n");
		int deleteid = user_input.nextInt();
		grammar_points.removeIf(gp -> gp.getId() == deleteid);
		System.out.println();
	}
}
