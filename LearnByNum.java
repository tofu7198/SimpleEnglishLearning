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
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LearnByNum extends JFrame {

	private JPanel contentPane;
	protected int numOfWords;
	protected ArrayList<Word> selected = new ArrayList<>();
	
	public void setNumOfWords(int num) {
		this.numOfWords = num;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LearnByNum frame = new LearnByNum();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	public static int count;
	
	/**
	 * Create the frame.
	 */
	DirectoriesControl dc = new DirectoriesControl();
	
	public void setSelected() {
		
		try {
			dc.loadDirectories(new File("Sources/").listFiles());
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Can't load!");
		}
		for (int i = 0; i < dc.getAllWords().size(); i++) {
				selected.add(dc.getAllWords().get(i));
				//System.out.println("Added " + dc.getAllWords().get(i).getName());
	}
		Collections.shuffle(selected);
	}
	
	public void writeToReviewFile() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
			try {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("Sources/data/review.dat"), true), StandardCharsets.UTF_8));
				selected.get(count).setLearned(true);
				bw.write(selected.get(count).display() + " - " + df.format(date).toString() + System.lineSeparator());
				bw.close();
			}
			catch (Exception exc) {
				System.out.println("cant write");
			}
	}
	
	public LearnByNum() {
		count = -1;
		setSelected();
		System.out.println(selected.size() + " "+dc.getAllWords().size());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblWords = new JLabel("Words");
		lblWords.setBounds(32, 48, 169, 53);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(32, 107, 195, 61);
		contentPane.setLayout(null);
		
		JLabel lblPicture = new JLabel();
		lblPicture.setBounds(148, 67, 103, 99);
		contentPane.add(lblPicture);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				count++;
				if (count >= numOfWords)
				{
					JOptionPane.showConfirmDialog(btnNext, "This is the end!", "END OF FILE", JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.PLAIN_MESSAGE);
					count--;
				}
				else {
					
					lblWords.setText(selected.get(count).getName());
					lblDescription.setText(selected.get(count).getDescription());
					getImage(selected.get(count), lblPicture);
					writeToReviewFile();
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
					
					lblWords.setText(selected.get(count).getName());
					lblDescription.setText(selected.get(count).getDescription());
					getImage(selected.get(count), lblPicture);
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
		button.setBounds(10, 11, 89, 23);
		contentPane.add(button);
		
	}

}
