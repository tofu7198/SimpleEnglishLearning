package EnglishDict;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class StatisticWin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticWin frame = new StatisticWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	protected File stFilePath;
	
	protected ReviewDirectory rd = new ReviewDirectory();
	
	protected int corrAns, wrongAns;

	public void readSt() throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(stFilePath), "UTF8"));
	        corrAns = Integer.valueOf(br.readLine());
	        wrongAns = Integer.valueOf(br.readLine());
	        br.close();
	}
	
	/**
	 * Create the frame.
	 */
	public StatisticWin() {
		stFilePath = new File("Sources/data/stFile.dat");
		try {
			readSt();
			rd.setListFromInput();
		}
		catch (Exception e) {
			
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		
		
		btnOk.setBounds(166, 195, 89, 23);
		contentPane.add(btnOk);
		
		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatistics.setBounds(20, 11, 133, 14);
		contentPane.add(lblStatistics);
		
		JLabel lblNumberOfLearned = new JLabel("Number of Learned words: " + rd.getDatesList().size());
		lblNumberOfLearned.setBounds(20, 36, 181, 49);
		contentPane.add(lblNumberOfLearned);
		
		JLabel lblNumberOfCorrect = new JLabel("Number of Correct answer: " + corrAns);
		lblNumberOfCorrect.setBounds(20, 90, 181, 60);
		contentPane.add(lblNumberOfCorrect);
		
		float ratio;
		if (wrongAns != 0 || corrAns != 0) {
			ratio = (float) corrAns / (wrongAns + corrAns);
		}
		else  {
			ratio = 0;
		}
		
		JLabel lblCorrectRatio = new JLabel("Correct ratio: " + ratio);
		lblCorrectRatio.setBounds(211, 96, 181, 49);
		contentPane.add(lblCorrectRatio);
		
		JLabel lblNumber = new JLabel("Number of Wrong answer: " + wrongAns);
		lblNumber.setBounds(211, 30, 181, 60);
		contentPane.add(lblNumber);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 28, 414, 122);
		contentPane.add(panel);
	}
}
