package EnglishDict;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class EditWin extends JFrame {

	private JPanel contentPane;

	protected static Directory selected;
	private JTable table;
	private JTextField txtNewName;
	
	public void setSelected(Directory d) {
		this.selected = d;
	}
	
	
	/**
	 * Create the frame.
	 */
	public EditWin(Directory selected) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ArrayList<Word> words = selected.getWordsList();
		String[][] wordList = new String[words.size()][2];
		
		for (int i = 0; i < words.size(); i++) {
			wordList[i][0] = words.get(i).getName();
			wordList[i][1] = words.get(i).getDescription();
		}
		
		DefaultTableModel model = new DefaultTableModel(
				wordList 
				,
				new String[] {
					"Word", "Description"
				}
			);
		
		table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		
		
		
		
		JButton btnAddRownew = new JButton("Add row (new word)...");
		btnAddRownew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addRow(new Object[] {});
			}
		});
		
		JButton btnDeleteRowword = new JButton("Delete row (word)...");
		btnDeleteRowword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeRow(table.getSelectedRow());
			}
		});
		/*
		 * Ham luu file = nut Save...
		 */
		JButton btnSave = new JButton("Save...");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected.words.clear();
				
				for (int i = 0; i < table.getRowCount(); i++) {
					Word temp = new Word();
					temp.setName((String) model.getValueAt(i, 0));
					temp.setDescription((String) model.getValueAt(i, 1));
					selected.addNewWord(temp);
				}
	
				try {
					selected.updateFile();
					JOptionPane.showMessageDialog(null, "Saved", "", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "Can't save.", "404", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		JButton button = new JButton("< Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		txtNewName = new JTextField();
		txtNewName.setText("New name... ");
		txtNewName.setColumns(10);
		
		JButton btnRename = new JButton("Rename");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File(selected.getPath());
				File change = new File("Sources/" + txtNewName.getText() + ".dat");
				if (!change.exists()) {
					file.renameTo(change);
					JOptionPane.showMessageDialog(null, "Name changed. Please press Refresh on main menu to see it. ");
				}
				else {
					JOptionPane.showMessageDialog(null, "This name is existed.");
				}
			}
		});
		
		JLabel lblWord = new JLabel("Word");
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtNewName, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRename, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblWord, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
											.addGap(1)
											.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
										.addComponent(table, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnAddRownew, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnDeleteRowword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNewName, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRename, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWord, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAddRownew)
							.addGap(12)
							.addComponent(btnDeleteRowword, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnSave)))
					.addGap(122))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
