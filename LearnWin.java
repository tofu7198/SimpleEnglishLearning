package EnglishDict;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class LearnWin extends JFrame {

	private JPanel contentPane;
	protected Directory selected;
	
	public Directory getSelected() {
		return this.selected;
	}
	
	public void setSelected(Directory d) {
		this.selected = d;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LearnWin frame = new LearnWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ReviewDirectory rd = new ReviewDirectory();
	
	/**
	 * Create the frame.
	 */

	public void getImage(Word word, JLabel label) {
		File img = new File("Sources/image/" + word.getName() + ".jpg");
		try {
			BufferedImage pic = ImageIO.read(img);
			Image dimg = pic.getScaledInstance(label.getWidth(), label.getHeight(),
			        Image.SCALE_SMOOTH);
			label.setIcon(new ImageIcon(dimg));
		}
		catch (Exception e) {
			label.setIcon(null);
		}
	}
	
	
	public static int count = -1;
	
	public LearnWin() {
		count = -1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPicture = new JLabel("");
		lblPicture.setBounds(157, 64, 132, 135);
		contentPane.add(lblPicture);

		JLabel lblWords = new JLabel("Words");
		lblWords.setBounds(15, 73, 251, 53);
		
		JLabel lblDescription = new JLabel("Descriptions");
		lblDescription.setBounds(15, 126, 258, 73);
		
		JCheckBox chckbxLearned = new JCheckBox("Learned?");
		chckbxLearned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected.getWordsList().get(count).setLearned(chckbxLearned.isSelected());
				try {
					selected.updateFile();
					
				}
				catch (Exception exc) {
					
				}
				if (chckbxLearned.isSelected()) {
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date date = new Date();				
					try {
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("Sources/data/review.dat"), true), StandardCharsets.UTF_8));
						
						bw.write(selected.getWordsList().get(count).display() + " - " + df.format(date).toString() + System.lineSeparator());
						bw.close();
					}
					catch (Exception exc) {
						System.out.println("cant write");
					}
				}
				
			}
		});
		chckbxLearned.setBounds(15, 190, 97, 23);
		contentPane.add(chckbxLearned);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(322, 88, 73, 23);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
				if (count >= selected.getWordsList().size())
				{
					JOptionPane.showConfirmDialog(btnNext, "This is the end!", "END OF FILE", JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.PLAIN_MESSAGE);
					count--;
				
				}
				else {
					
					lblWords.setText(selected.getWordsList().get(count).getName());
					lblDescription.setText(selected.getWordsList().get(count).getDescription());
					getImage(selected.getWordsList().get(count), lblPicture);
					if (selected.getWordsList().get(count).isLearned()) chckbxLearned.setSelected(true);
					else chckbxLearned.setSelected(false);
				}
				
			}
		});
		
		JButton btnNewButton = new JButton("Previous");
		btnNewButton.setBounds(322, 126, 73, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				count--;
				if (count < 0) {
					JOptionPane.showConfirmDialog(btnNext, "This is the first! Plz keep going towards.", "FIRST OF FILE", JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.PLAIN_MESSAGE);
					count++;
				}
				else {
					
					lblWords.setText(selected.getWordsList().get(count).getName());
					lblDescription.setText(selected.getWordsList().get(count).getDescription());
					getImage(selected.getWordsList().get(count), lblPicture);
					if (selected.getWordsList().get(count).isLearned()) chckbxLearned.setSelected(true);
					else chckbxLearned.setSelected(false);
				}
				
			}
		});
		
		JButton button = new JButton("< Back");
		button.setBounds(5, 5, 65, 29);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lblWords);
		contentPane.add(lblDescription);
		contentPane.add(btnNext);
		contentPane.add(btnNewButton);
		contentPane.add(button);
		
		
		
		
	}
}
