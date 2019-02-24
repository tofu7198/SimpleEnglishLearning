package EnglishDict;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestWin extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestWin frame = new TestWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	File folder = new File("Sources");
	DirectoriesControl session = new DirectoriesControl();
	ArrayList<Word> working= new ArrayList<>();
	
	public void importFirst() {
		try {
			session.loadDirectories(folder.listFiles());
		}
		catch (Exception e) { 
			System.out.println("Not found!");
		}
		
		working = session.getAllWords();
	}
	
	public static int answer;
	/**
	 * Create the frame.
	 */
	public TestWin() {
		importFirst();
		
		answer = -1;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonGroup btnGroup = new ButtonGroup();
		
		JLabel lblQuestionHere = new JLabel("Question here");
		lblQuestionHere.setBounds(35, 11, 337, 88);
		contentPane.add(lblQuestionHere);
		
		JRadioButton rdbtnAnswerA = new JRadioButton("Answer A");
		rdbtnAnswerA.setBounds(35, 107, 109, 23);
		contentPane.add(rdbtnAnswerA);
		
		JRadioButton rdbtnAnswerB = new JRadioButton("Answer B");
		rdbtnAnswerB.setBounds(263, 107, 109, 23);
		contentPane.add(rdbtnAnswerB);
		
		JRadioButton rdbtnAnswerC = new JRadioButton("Answer C");
		rdbtnAnswerC.setBounds(35, 133, 109, 23);
		contentPane.add(rdbtnAnswerC);
		
		JRadioButton rdbtnAnswerD = new JRadioButton("Answer D");
		rdbtnAnswerD.setBounds(263, 133, 109, 23);
		contentPane.add(rdbtnAnswerD);
		
		btnGroup.add(rdbtnAnswerA);
		btnGroup.add(rdbtnAnswerB);
		btnGroup.add(rdbtnAnswerC);
		btnGroup.add(rdbtnAnswerD);
		
		Collections.shuffle(working);
		Random rd = new Random();
		answer = rd.nextInt(4);
		
		int questionType = rd.nextInt(2);
		if (questionType == 0) {
			lblQuestionHere.setText("What is " + working.get(answer).getName() + "?");
		
			rdbtnAnswerA.setText(working.get(0).getDescription());
			rdbtnAnswerB.setText(working.get(1).getDescription());
			rdbtnAnswerC.setText(working.get(2).getDescription());
			rdbtnAnswerD.setText(working.get(3).getDescription()); }
		
		else {
			
			lblQuestionHere.setText("What is " + working.get(answer).getDescription() + "?");
			
			rdbtnAnswerA.setText(working.get(0).getName());
			rdbtnAnswerB.setText(working.get(1).getName());
			rdbtnAnswerC.setText(working.get(2).getName());
			rdbtnAnswerD.setText(working.get(3).getName());
		}
			
		JLabel lblCorrect = new JLabel("");
		lblCorrect.setBounds(114, 162, 165, 23);
		contentPane.add(lblCorrect);
		
		JButton btnAnswer = new JButton("Answer!");
		btnAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int theirAns = 0;
				
				if (rdbtnAnswerA.isSelected()) theirAns = 0;
				else if (rdbtnAnswerB.isSelected()) theirAns = 1;
				else if (rdbtnAnswerC.isSelected()) theirAns = 2;
				else if (rdbtnAnswerD.isSelected()) theirAns = 3;
				
				if (theirAns == answer)	{
					lblCorrect.setText("CORRECT!");
					try {
						 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Sources/data/stFile.dat"), "UTF8"));
						 int corr = Integer.valueOf(br.readLine());
						 int wrong = Integer.valueOf(br.readLine());
						 corr++;
						 PrintWriter writer = new PrintWriter("Sources/data/stFile.dat", "UTF-8");
						 writer.println(corr);
						 writer.print(wrong);
						 writer.close();
					}
					catch (Exception exc) {
						System.out.println("Can't write");
					}
				}
				else {
					lblCorrect.setText("FALSE!");
					try {
						 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Sources/data/stFile.dat"), "UTF8"));
						 int corr = Integer.valueOf(br.readLine());
						 int wrong = Integer.valueOf(br.readLine());
						 wrong++;
						 PrintWriter writer = new PrintWriter("Sources/data/stFile.dat", "UTF-8");
						 writer.println(corr);
						 writer.print(wrong);
						 writer.close();
					}
					catch (Exception exc) {
						System.out.println("Can't write");
					}
				}
			}
		});
		btnAnswer.setBounds(135, 187, 123, 48);
		contentPane.add(btnAnswer);
		
		JButton button = new JButton("< Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		button.setBounds(10, 11, 89, 23);
		contentPane.add(button);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new TestWin().setVisible(true);
			}
		});
		btnNext.setBounds(272, 187, 63, 48);
		contentPane.add(btnNext);
		
		
	}
}
