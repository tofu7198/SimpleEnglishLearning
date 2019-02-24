package EnglishDict;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MenuGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGUI window = new MenuGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnLearnWords = new JButton("Learn words....");
		btnLearnWords.setBounds(41, 22, 338, 43);
		
		btnLearnWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LearnSelection().setVisible(true);
			}
		});
		
		JButton btnEditDatabases = new JButton("Edit databases...");
		btnEditDatabases.setBounds(41, 76, 335, 43);
		btnEditDatabases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditSelection().setVisible(true);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnEditDatabases);
		frame.getContentPane().add(btnLearnWords);
		
		JButton btnSearchLearnedWords = new JButton("Search learned words...");
		btnSearchLearnedWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchWin().setVisible(true);
			}
		});
		btnSearchLearnedWords.setBounds(41, 130, 335, 43);
		frame.getContentPane().add(btnSearchLearnedWords);
		
		JButton btnStatistics = new JButton("Statistics...");
		btnStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new StatisticWin().setVisible(true);
			}
		});
		btnStatistics.setBounds(41, 182, 338, 43);
		frame.getContentPane().add(btnStatistics);
	}
}
