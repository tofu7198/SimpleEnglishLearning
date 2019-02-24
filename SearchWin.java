package EnglishDict;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;

public class SearchWin extends JFrame {

	private JPanel contentPane;
	ReviewDirectory rd = new ReviewDirectory();
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchWin frame = new SearchWin();
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
	/**
	 * Create the frame.
	 */
	public SearchWin() {
		try {
			rd.setListFromInput();
		}
		catch (Exception e) {
			System.out.println("Can't load!");
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setText("Search...");
		txtSearch.setBounds(28, 71, 261, 44);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblWords = new JLabel("Words");
		lblWords.setBounds(28, 126, 112, 44);
		contentPane.add(lblWords);
		
		JLabel lblDescriptions = new JLabel("Descriptions");
		lblDescriptions.setBounds(164, 126, 224, 44);
		contentPane.add(lblDescriptions);
		
		JLabel lblPictures = new JLabel("");
		lblPictures.setBounds(257, 121, 140, 117);
		contentPane.add(lblPictures);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tmp = txtSearch.getText();
				boolean founded = false;
				for (int i = 0; i < rd.getDatesList().size(); i++) {
					if (tmp.equals(rd.getDatesList().get(i).getName()) /*&& !founded*/) {
						lblWords.setText(rd.getDatesList().get(i).getName());
						lblDescriptions.setText(rd.getDatesList().get(i).getDescription());
						getImage(rd.getDatesList().get(i), lblPictures);
						founded = true;
					}
				}
				if (founded == false) JOptionPane.showMessageDialog(null, "This word hasn't learned yet!");
			}
		});
		btnSearch.setBounds(299, 82, 89, 23);
		contentPane.add(btnSearch);
		
		JButton button = new JButton("< Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		button.setBounds(28, 23, 89, 23);
		contentPane.add(button);
		
		
	
		
	}
}
