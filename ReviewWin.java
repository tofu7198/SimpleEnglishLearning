package EnglishDict;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ReviewWin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewWin frame = new ReviewWin();
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
	
	protected ReviewDirectory rd = new ReviewDirectory();
	
	public static int count;
	
	protected static int numberOfWords;
	
	public void setWordsNum(int num) {
		this.numberOfWords = num;
	}
	
	
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
	
	public void setFirstWordsToTheEnd(int numberOfWords) {
		if (rd.getDatesList().size() > numberOfWords) {
			for (int i = 0; i < numberOfWords; i++) {
				rd.getDatesList().get(i).setDate(new Date());
				System.out.println(rd.getDatesList().get(i).getDate());
			}
		}
		else {
			for (int i = 0; i < rd.getDatesList().size(); i++) {
				rd.getDatesList().get(i).setDate(new Date());
				System.out.println(rd.getDatesList().get(i).getDate());
			}
		}
        rd.sort();
	}
	
	public ReviewWin() {
		try {
			rd.setListFromInput();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Can't load the review file!");
		}
		
		count = -1;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblWords = new JLabel("Words");
		lblWords.setBounds(32, 48, 169, 53);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(32, 107, 195, 61);
		
		JLabel lblPicture = new JLabel();
		lblPicture.setBounds(148, 67, 103, 99);
		contentPane.add(lblPicture);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				count++;
				if (count >= numberOfWords || count >= rd.getDatesList().size())
				{
					JOptionPane.showConfirmDialog(btnNext, "This is the end!", "END OF FILE", JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.PLAIN_MESSAGE);
					count--;
				}
				else {
					rd.getDatesList().get(count).setDate(new Date());
					try {
						rd.updateFile();
					}
					catch (Exception e) {
						System.out.println("cant do that1");
					}
					lblWords.setText(rd.getDatesList().get(count).getName());
					lblDescription.setText(rd.getDatesList().get(count).getDescription());
					getImage(rd.getDatesList().get(count), lblPicture);
				}
				
			}
		});
		btnNext.setBounds(261, 63, 55, 23);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count--;
				if (count < 0) {
					JOptionPane.showConfirmDialog(btnNext, "This is the first! Plz keep going towards.", "FIRST OF FILE", JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.PLAIN_MESSAGE);
					count++;
				}
				else {
					
					lblWords.setText(rd.getDatesList().get(count).getName());
					lblDescription.setText(rd.getDatesList().get(count).getDescription());
					getImage(rd.getDatesList().get(count), lblPicture);
				}
			}
		});
		
		btnPrevious.setBounds(261, 107, 73, 23);
		contentPane.setLayout(null);
		contentPane.add(lblWords);
		contentPane.add(lblDescription);
		contentPane.add(btnNext);
		contentPane.add(btnPrevious);
		
		JButton button = new JButton("< Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		button.setBounds(32, 11, 89, 23);
		contentPane.add(button);
		
		
	}
}
