package benkyosuru;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class benkyosuru_ui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	List<Word> words = new ArrayList<>();
	List<Grammar> grammar_points = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					benkyosuru_ui frame = new benkyosuru_ui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public benkyosuru_ui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lbl_word_label = new JLabel("Words");
		lbl_word_label.setFont(new Font("Monospaced", Font.BOLD, 13));
		lbl_word_label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lbl_word_label = new GridBagConstraints();
		gbc_lbl_word_label.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_word_label.gridx = 1;
		gbc_lbl_word_label.gridy = 0;
		contentPane.add(lbl_word_label, gbc_lbl_word_label);
		
		JLabel lbl_grammar_label = new JLabel("Grammar");
		lbl_grammar_label.setFont(new Font("Monospaced", Font.BOLD, 13));
		GridBagConstraints gbc_lbl_grammar_label = new GridBagConstraints();
		gbc_lbl_grammar_label.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_grammar_label.gridx = 6;
		gbc_lbl_grammar_label.gridy = 0;
		contentPane.add(lbl_grammar_label, gbc_lbl_grammar_label);
		
		JTextArea textArea_word_list = textarea_word_list();
		GridBagConstraints gbc_textArea_word_list = new GridBagConstraints();
		gbc_textArea_word_list.gridwidth = 4;
		gbc_textArea_word_list.gridheight = 7;
		gbc_textArea_word_list.insets = new Insets(0, 0, 0, 5);
		gbc_textArea_word_list.fill = GridBagConstraints.BOTH;
		gbc_textArea_word_list.gridx = 1;
		gbc_textArea_word_list.gridy = 1;
		contentPane.add(textArea_word_list, gbc_textArea_word_list);
		
		JTextArea textArea_grammar_list = textArea_grammar_list();
		GridBagConstraints gbc_textArea_grammar_list = new GridBagConstraints();
		gbc_textArea_grammar_list.gridheight = 7;
		gbc_textArea_grammar_list.gridwidth = 4;
		gbc_textArea_grammar_list.insets = new Insets(0, 0, 0, 5);
		gbc_textArea_grammar_list.fill = GridBagConstraints.BOTH;
		gbc_textArea_grammar_list.gridx = 6;
		gbc_textArea_grammar_list.gridy = 1;
		contentPane.add(textArea_grammar_list, gbc_textArea_grammar_list);
		
		JButton btn_remove_grammar = new JButton("Remove grammar");
		btn_remove_grammar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id_entered = JOptionPane.showInputDialog("please enter the id of the word you want to remove");
				int id_as_int = Integer.parseInt(id_entered);
				grammar_points.removeIf(w -> w.getid() == id_as_int);
				textArea_grammar_list.setText(grammar_printer());
			}
		});
		
		JButton btn_remove_word = new JButton("Remove word");
		btn_remove_word.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id_entered = JOptionPane.showInputDialog("please enter the id of the word you want to remove");
				int id_as_int = Integer.parseInt(id_entered);
				words.removeIf(w -> w.getid() == id_as_int);
				textArea_word_list.setText(word_printer());
			}
		});
		
		JButton btn_add_gramar = new JButton("Add grammar");
		btn_add_gramar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String grammar_name = JOptionPane.showInputDialog("please say the name of the grammar point");
				String grammar_explination = JOptionPane.showInputDialog("please enter the explination for this grammar point");
				String grammar_example = JOptionPane.showInputDialog("please put down an example of the grammar point");
				grammar_points.add(new Grammar(grammar_name, grammar_explination, grammar_example));
				textArea_grammar_list.setText(grammar_printer());
			}
		});
		
		JButton btn_add_word = new JButton("Add word");
		btn_add_word.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String word_name = JOptionPane.showInputDialog("Please enter the word");
				String pronunciation = JOptionPane.showInputDialog("please enter the pronunciation");
				String definition = JOptionPane.showInputDialog("please enter the definition");
				String wtype = JOptionPane.showInputDialog("what type of word is this?\n" + "1: NOUN\n" + "2: VERB\n" + "3: ADJECTIVE\n" + "4: ADVERB\n" + "5: PRONOUN\n" + "6: PREPOSITION\n" + "7: CONJUNCTION\n" + "8: DETERMINER\n");
				Integer wtypenum = Integer.parseInt(wtype);
				Word_types wordtype = typefromnum(wtypenum);
				words.add(new Word(word_name, wordtype, pronunciation, definition));
				textArea_word_list.setText(word_printer());
			}
		});
		
		JButton btn_save = new JButton("save");
		btn_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				serialize_word();
				serialize_grammar();
			}
		});
		btn_save.setFont(new Font("Monospaced", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_save = new GridBagConstraints();
		gbc_btn_save.insets = new Insets(0, 0, 5, 0);
		gbc_btn_save.gridx = 11;
		gbc_btn_save.gridy = 2;
		contentPane.add(btn_save, gbc_btn_save);
		
		JButton btn_load = new JButton("load");
		btn_load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deserialize_word();
				deserialize_grammar();
				textArea_grammar_list.setText(grammar_printer());
				textArea_word_list.setText(word_printer());
			}
		});
		btn_load.setFont(new Font("Monospaced", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_load = new GridBagConstraints();
		gbc_btn_load.insets = new Insets(0, 0, 5, 0);
		gbc_btn_load.gridx = 11;
		gbc_btn_load.gridy = 3;
		contentPane.add(btn_load, gbc_btn_load);
		btn_add_word.setFont(new Font("Monospaced", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_add_word = new GridBagConstraints();
		gbc_btn_add_word.insets = new Insets(0, 0, 5, 0);
		gbc_btn_add_word.gridx = 11;
		gbc_btn_add_word.gridy = 4;
		contentPane.add(btn_add_word, gbc_btn_add_word);
		btn_add_gramar.setFont(new Font("Monospaced", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_add_gramar = new GridBagConstraints();
		gbc_btn_add_gramar.insets = new Insets(0, 0, 5, 0);
		gbc_btn_add_gramar.gridx = 11;
		gbc_btn_add_gramar.gridy = 5;
		contentPane.add(btn_add_gramar, gbc_btn_add_gramar);
		btn_remove_word.setFont(new Font("Monospaced", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_remove_word = new GridBagConstraints();
		gbc_btn_remove_word.insets = new Insets(0, 0, 5, 0);
		gbc_btn_remove_word.gridx = 11;
		gbc_btn_remove_word.gridy = 6;
		contentPane.add(btn_remove_word, gbc_btn_remove_word);
		btn_remove_grammar.setFont(new Font("Monospaced", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_remove_grammar = new GridBagConstraints();
		gbc_btn_remove_grammar.gridx = 11;
		gbc_btn_remove_grammar.gridy = 7;
		contentPane.add(btn_remove_grammar, gbc_btn_remove_grammar);
	}

	private JTextArea textarea_word_list() {
		JTextArea textArea_word_list = new JTextArea();
		textArea_word_list.setText(word_printer());
		textArea_word_list.setFont(new Font("Monospaced", Font.PLAIN, 13));
		return textArea_word_list;
	}

	private JTextArea textArea_grammar_list() {
		JTextArea textArea_grammar_list = new JTextArea();
		textArea_grammar_list.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea_grammar_list.setText(grammar_printer());
		return textArea_grammar_list;
	}

	private String word_printer() {
		if (words.isEmpty()) {
			return null;
		}
		String final_string = "";
		for (Word current_word : words) {
			final_string = final_string + current_word.toString() + "\n";
		}
		return final_string;
	}
	
	private String grammar_printer() {
		if (grammar_points.isEmpty()) {
			return null;
		}
		String final_string = "";
		for (Grammar current_grammar : grammar_points) {
			final_string = final_string + current_grammar.toString() + "\n";
		}
		return final_string;
	}
	
	private Word_types typefromnum(Integer wtypenum) {
		switch (wtypenum) {
			case 1: {
				return Word_types.NOUN;
			}
			case 2: {
				return Word_types.VERB;
			}
			case 3: {
				return Word_types.ADJECTIVE;
			}
			case 4: {
				return Word_types.ADVERB;
			}
			case 5: {
				return Word_types.PRONOUN;
			}
			case 6: {
				return Word_types.PREPOSITION;
			}
			case 7: {
				return Word_types.CONJUNCTION;
			}
			case 8: {
				return Word_types.DETERMINER;
			}
			default: {
				throw new IllegalArgumentException("Unexpected value: " + wtypenum);	
			}
		}
	}
	
	private void serialize_word() {
		try(ObjectOutputStream filestream = new ObjectOutputStream(new FileOutputStream("words.ser"))) {
    		filestream.writeObject(words);
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void serialize_grammar() {
		try(ObjectOutputStream filestream = new ObjectOutputStream(new FileOutputStream("grammar_points.ser"))) {
    		filestream.writeObject(grammar_points);
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deserialize_word() {
    	try (ObjectInputStream objectstream = new ObjectInputStream(new FileInputStream("words.ser"))) {
    		words = (ArrayList<Word>) objectstream.readObject();
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

	}
	
	private void deserialize_grammar() {
    	try (ObjectInputStream objectstream = new ObjectInputStream(new FileInputStream("grammar_points.ser"))) {
    		grammar_points = (ArrayList<Grammar>) objectstream.readObject();
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
