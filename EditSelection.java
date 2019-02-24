package EnglishDict;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class EditSelection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditSelection frame = new EditSelection();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	File folder = new File("Sources");
	DirectoriesControl session = new DirectoriesControl();
	ArrayList<Word> working = new ArrayList<>();
	/*
	 * Load toan bo tu vao
	 */
	public void importFirst() {
		try {
			session.loadDirectories(folder.listFiles());
		}
		catch (Exception e) { 
			System.out.println("Not found!");
		}
		
		working = session.getAllWords();
	}
	
	/**
	 * Create the frame.
	 */
	public EditSelection() {
		importFirst();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		/*
		 * nut back
		 */
		JButton button = new JButton("< Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		
		
		/*
		Tao comboBox voi ten cac bo tu dien co san
		*/
		
		ArrayList<String> allDirectoryNames = new ArrayList<String>();
		
		for (int i = 0; i < session.getDirectories().size(); i++) {
			allDirectoryNames.add(session.getDirectories().get(i).getName());
		}
		
		DefaultComboBoxModel boxModel = new DefaultComboBoxModel(allDirectoryNames.toArray());
		JComboBox<String> comboBox = new JComboBox(boxModel);
		
		DefaultComboBoxModel boxClone = new DefaultComboBoxModel(allDirectoryNames.toArray());;
		JComboBox comboBox_1 = new JComboBox(boxClone);
		
		/*
		 Chinh sua bo tu dien duoc chon o comboBox
		 */
		JButton btnEditExistDirectory = new JButton("Edit selected directory...");
		btnEditExistDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditWin window = new EditWin(session.getDirectories().get(comboBox.getSelectedIndex()));
				window.setVisible(true);
			}
		});
		
		JLabel lblSelectADirectory = new JLabel("Select a directory");
		/*
		 * Xoa bo tu dien duoc chon
		 */
		JButton btnDeleteSelectedDirectory = new JButton("Delete selected directory...");
		btnDeleteSelectedDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int Val = JOptionPane.showConfirmDialog(null, "Do you want to delete directory " + session.getDirectories().get(comboBox.getSelectedIndex()).getName()
				, "Confirm", JOptionPane.OK_CANCEL_OPTION);
				if (Val == JOptionPane.OK_OPTION) {
					
					File file = new File(session.getDirectories().get(comboBox.getSelectedIndex()).getPath());
					JFileChooser fc = new JFileChooser(file);
					try {
						int count = comboBox.getSelectedIndex();
						java.nio.file.Files.delete(file.toPath());
						JOptionPane.showMessageDialog(null, "Deleted.");
						boxModel.removeElementAt(count);
						boxClone.removeElementAt(count);
					}
					catch (Exception e) {}	
				}
			}
		});
		
		/*
		 * Tao 1 bo tu dien moi
		 */
		JButton btnCreateNewDirectory = new JButton("Create new directory...");
		btnCreateNewDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateWin win = new CreateWin();
				win.setVisible(true);
				win.addComponentListener(new ComponentAdapter() {
					public void componentHidden(ComponentEvent e) 
					{
					    if (win.getCreated() != null) 
					    	boxModel.addElement(win.getCreated().getName());
					    	session.getDirectories().add(win.getCreated());
					}
				});
			}
		});
		
		/*
		 * Load lai
		 */
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				setVisible(false);
				new EditSelection().setVisible(true);
			}
		});
		
		/*
		 * Nut merge
		 */
		
		JButton btnMergeWithAnother = new JButton("Merge with another directory...");
		btnMergeWithAnother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				session.mergeTwo(session.getDirectories().get(comboBox.getSelectedIndex()), 
						session.getDirectories().get(comboBox_1.getSelectedIndex()), 
						session.getDirectories().get(comboBox.getSelectedIndex()).getName());
				JOptionPane.showMessageDialog(null, "Merge complete. Press Refresh to refresh the file.");
				
				}
				catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "Can't merge!");
				}
			}
		});
		
		
		JLabel lblSelectThend = new JLabel("Select the 2nd directory for the merge");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEditExistDirectory, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSelectADirectory, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnDeleteSelectedDirectory, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblSelectThend, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox_1, 0, 191, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCreateNewDirectory, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnMergeWithAnother, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnRefresh))
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEditExistDirectory, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSelectADirectory, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnDeleteSelectedDirectory, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(6))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSelectThend, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCreateNewDirectory, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(btnMergeWithAnother, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
