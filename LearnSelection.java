package EnglishDict;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class LearnSelection extends JFrame {

	private JPanel contentPane;
	protected DirectoriesControl session = new DirectoriesControl();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LearnSelection frame = new LearnSelection();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	File folder = new File("Sources");
	private JTextField txtNumberOfNew;
	private JTextField txtNumberOfReview;
	public void importFirst() {
		try {
			session.loadDirectories(folder.listFiles());
		}
		catch (Exception e) { 
			System.out.println("Not found!");
		}
	}
	
	/**
	 * Create the frame.
	 */
	public LearnSelection() {
		importFirst();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnTest = new JButton("Test...");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TestWin().setVisible(true);
			}
		});
		btnTest.setBounds(28, 137, 236, 33);
		
		JButton btnReview = new JButton("Review...");
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReviewWin reviewW = new ReviewWin();
				reviewW.setWordsNum(Integer.valueOf(txtNumberOfReview.getText()));
				reviewW.setVisible(true);
			}
		});
		btnReview.setBounds(28, 181, 236, 33);
		
		ArrayList<String> allDirectoryNames = new ArrayList<String>();
		
		for (int i = 0; i < session.getDirectories().size(); i++) {
			allDirectoryNames.add(session.getDirectories().get(i).getName());
		}
		
		JComboBox comboBox = new JComboBox(allDirectoryNames.toArray());
		comboBox.setBounds(270, 105, 150, 21);
		
		JButton btnLearnFromDirectory = new JButton("Learn from directory...");
		btnLearnFromDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LearnWin learnWin = new LearnWin();
				learnWin.setSelected(session.getDirectories().get(comboBox.getSelectedIndex()));
				learnWin.setVisible(true);
			}
		});
		btnLearnFromDirectory.setBounds(28, 93, 236, 33);
		
		JLabel lblSelectADirectory = new JLabel("Select a directory...");
		lblSelectADirectory.setBounds(270, 90, 150, 14);
		contentPane.setLayout(null);
		contentPane.add(btnTest);
		contentPane.add(btnLearnFromDirectory);
		contentPane.add(comboBox);
		contentPane.add(lblSelectADirectory);
		contentPane.add(btnReview);
		
		JButton btnLearnNewWords = new JButton("Learn new words...");
		btnLearnNewWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LearnByNum learnW = new LearnByNum();
				learnW.setNumOfWords(Integer.valueOf(txtNumberOfNew.getText()));
				learnW.setVisible(true);
			}		
		});
		btnLearnNewWords.setBounds(29, 49, 235, 33);
		contentPane.add(btnLearnNewWords);
		
		txtNumberOfNew = new JTextField();
		txtNumberOfNew.setText("Number of new words...");
		txtNumberOfNew.setBounds(270, 46, 148, 33);
		contentPane.add(txtNumberOfNew);
		txtNumberOfNew.setColumns(10);
		
		txtNumberOfReview = new JTextField();
		txtNumberOfReview.setText("Number of review words..");
		txtNumberOfReview.setBounds(270, 181, 148, 33);
		contentPane.add(txtNumberOfReview);
		txtNumberOfReview.setColumns(10);
		
		JButton button = new JButton("< Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		button.setBounds(28, 11, 89, 23);
		contentPane.add(button);
	}
}
