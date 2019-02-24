package EnglishDict;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CreateWin extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnSaveDirectory;
	private JButton btnDeleteSelectedRow;
	private JTextField txtTypeNameHere;
	private Directory created;
	
	public void setCreated(Directory d) {
		this.created = d;
	}
	
	public Directory getCreated() {
		return created;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateWin frame = new CreateWin();
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
	public CreateWin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
			
		/*
		 * Tao model cho bang
		 */
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Word", "Description"
				}
			);
		
		table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		/*
		 * Them dong cho bang
		 */
		JButton btnAddRow = new JButton("Add row...");
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.addRow(new Object[] {});
			}
		});
		
		/*
		 * Xoa dong
		 */
		btnDeleteSelectedRow = new JButton("Delete selected row...");
		btnDeleteSelectedRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeRow(table.getSelectedRow());
			}
		});
		
		/*
		 * Ten cho file
		 */
		txtTypeNameHere = new JTextField();
		txtTypeNameHere.setText("Type file name here...");
		txtTypeNameHere.setColumns(10);
		
		/*
		 * Chuc nang luu
		 */
		btnSaveDirectory = new JButton("Save directory...");
		btnSaveDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Directory saving = new Directory();
				File path = new File("Sources/" + txtTypeNameHere.getText() + ".dat");
				if (path.exists()) {
					JOptionPane.showMessageDialog(null, "This filename is existed. Choose something diff.", "Existed", JOptionPane.ERROR_MESSAGE);
				}
				else {
					for (int i = 0; i < table.getRowCount(); i++) {
						Word temp = new Word();
						temp.setName((String) model.getValueAt(i, 0));
						temp.setDescription((String) model.getValueAt(i, 1));
						saving.addNewWord(temp);
					}
					saving.setPath(path.toString());
					try {
						saving.updateFile();
						JOptionPane.showMessageDialog(null, "Saved", "", JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception exc) {
						JOptionPane.showMessageDialog(null, "Can't save.", "404", JOptionPane.ERROR_MESSAGE);
					}
					setCreated(saving);
				}
			}
		});
		/*
		 * nut back
		 */
		JButton button = new JButton("< Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
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
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblWord, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSaveDirectory)
								.addComponent(btnDeleteSelectedRow)
								.addComponent(btnAddRow)))
						.addComponent(txtTypeNameHere, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtTypeNameHere, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddRow)
						.addComponent(lblWord, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnDeleteSelectedRow)
							.addGap(2)
							.addComponent(btnSaveDirectory))
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
					.addGap(40))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
