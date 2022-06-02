
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.Font;

import org.apache.commons.lang3.time.StopWatch;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class ApplicationGui {
	private JFrame frame;
	private final Action action = new SwingAction();
	private static JTable table;
	private static JTable table_1;
	public fileReader fileread;
	private final Action action_1 = new SwingAction_1();
	private static JTable table_2;
	private JTable table_3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationGui window = new ApplicationGui();
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
	public ApplicationGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		globalthings.stopwatch=StopWatch.create();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1207, 1070);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("select file");
		mntmNewMenuItem.setAction(action);
		mnNewMenu.add(mntmNewMenuItem);
				
				JPanel panel = new JPanel();
				
				JButton btnNewButton_1 = new JButton("Start");
				panel.add(btnNewButton_1);
				
				JButton btnNewButton_2 = new JButton("Pause");
				panel.add(btnNewButton_2);
				
				JButton btnNewButton_3 = new JButton("Reset Pic");
				panel.add(btnNewButton_3);
						
								JButton btnNewButton = new JButton("select row test");
								btnNewButton.setAction(action_1);
						
						JScrollPane scrollPane_2 = new JScrollPane();
						
						table_2 = new JTable();
						table_2.setModel(new DefaultTableModel(
							new Object[][] {
								{new Integer(0), null},
								{new Integer(1), null},
								{new Integer(2), null},
								{new Integer(3), null},
								{new Integer(4), null},
								{new Integer(5), null},
								{new Integer(6), null},
								{new Integer(7), null},
							},
							new String[] {
								"Nr", "Stack"
							}
						) {
							Class[] columnTypes = new Class[] {
								Integer.class, Object.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
						table_2.getColumnModel().getColumn(0).setResizable(false);
						table_2.getColumnModel().getColumn(0).setPreferredWidth(65);
						table_2.getColumnModel().getColumn(0).setMaxWidth(65);
						table_2.getColumnModel().getColumn(1).setResizable(false);
						table_2.getColumnModel().getColumn(1).setMaxWidth(120);
						scrollPane_2.setViewportView(table_2);
				
						JLabel lblNewLabel = new JLabel("RAM");
				
						JLabel lblNewLabel_1 = new JLabel("Datei");
		
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
				
						table = new JTable();
						table.setFillsViewportHeight(true);
						table.setRowSelectionAllowed(false);
						table.setFont(new Font("Tahoma", Font.PLAIN, 11));
						table.setToolTipText("RAM");
						scrollPane_1.setViewportView(table);
						table.setBorder(null);
						table.setCellSelectionEnabled(true);
						table.setModel(new DefaultTableModel(
								new Object[][] {
									{"08", null, null, null, null, null, null, null, null},
									{"10", null, null, null, null, null, null, null, null},
									{"18", null, null, null, null, null, null, null, null},
									{"20", null, null, null, null, null, null, null, null},
									{"28", null, null, null, null, null, null, null, null},
									{"30", null, null, null, null, null, null, null, null},
									{"38", null, null, null, null, null, null, null, null},
									{"40", null, null, null, null, null, null, null, null},
									{"48", null, null, null, null, null, null, null, null},
									{"50", null, null, null, null, null, null, null, null},
									{"58", null, null, null, null, null, null, null, null},
									{"60", null, null, null, null, null, null, null, null},
									{"68", null, null, null, null, null, null, null, null},
									{"70", null, null, null, null, null, null, null, null},
									{"78", null, null, null, null, null, null, null, null},
									{"80", null, null, null, null, null, null, null, null},
									{"88", null, null, null, null, null, null, null, null},
									{"90", null, null, null, null, null, null, null, null},
									{"98", null, null, null, null, null, null, null, null},
									{"A0", null, null, null, null, null, null, null, null},
									{"A8", null, null, null, null, null, null, null, null},
									{"B0", null, null, null, null, null, null, null, null},
									{"B8", null, null, null, null, null, null, null, null},
									{"C0", null, null, null, null, null, null, null, null},
									{"C8", null, null, null, null, null, null, null, null},
									{"D0", null, null, null, null, null, null, null, null},
									{"D8", null, null, null, null, null, null, null, null},
									{"E0", null, null, null, null, null, null, null, null},
									{"E8", null, null, null, null, null, null, null, null},
									{"F0", null, null, null, null, null, null, null, null},
									{"F8", null, null, null, null, null, null, null, null},
								},
								new String[] {
										"0x", "00", "01", "02", "03", "04", "05", "06", "07"
								}
								));
		for(int b=0;b<9;b++) {
			table.getColumnModel().getColumn(b).setResizable(false);
		}
		//table.getColumnModel().getColumn(0).setResizable(false);
		//		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		//		table.getColumnModel().getColumn(0).setMaxWidth(40);
		
				JScrollPane scrollPane = new JScrollPane();
				
				
				
						table_1 = new JTable(new DefaultTableModel(
								new Object[][] {},new String[] {"Breakpoint","X", "Zeile"
								}) 
						{@Override public Class<?> getColumnClass(int column) {
							return getValueAt(0, column).getClass();
						}
						@Override public boolean isCellEditable(int row,int col) {
							if(col==0) {
								return true;
							}else {
								return false;
							}
						}
						});
						table_1.setFillsViewportHeight(true);
						table_1.getColumnModel().getColumn(0).setMaxWidth(75);
						table_1.getColumnModel().getColumn(1).setMaxWidth(60);
						
								scrollPane.setViewportView(table_1);
								
								JScrollPane scrollPane_3 = new JScrollPane();
								
								JPanel panel_1 = new JPanel();
								
								JLabel lblNewLabel_2 = new JLabel("Pin Port A");
								
								JLabel lblNewLabel_2_1 = new JLabel("Pin Port A");
								
								JPanel panel_1_1 = new JPanel();
								
								JCheckBox chckbxNewCheckBox_2_1 = new JCheckBox("0");
								panel_1_1.add(chckbxNewCheckBox_2_1);
								
								JCheckBox chckbxNewCheckBox_5 = new JCheckBox("1");
								panel_1_1.add(chckbxNewCheckBox_5);
								
								JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("2");
								panel_1_1.add(chckbxNewCheckBox_1_1);
								
								JCheckBox chckbxNewCheckBox_3_1 = new JCheckBox("3");
								panel_1_1.add(chckbxNewCheckBox_3_1);
								
								JCheckBox chckbxNewCheckBox_4_1 = new JCheckBox("4");
								panel_1_1.add(chckbxNewCheckBox_4_1);
								GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
								groupLayout.setHorizontalGroup(
									groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(10)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(7)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
															.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
															.addGap(18)
															.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
																.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
															.addGap(61))
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addGap(4)
															.addComponent(btnNewButton)))
													.addGap(248))
												.addGroup(groupLayout.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel)
														.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel_1))))
											.addGap(289))
								);
								groupLayout.setVerticalGroup(
									groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(7)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(5)
													.addComponent(btnNewButton)))
											.addGap(4)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblNewLabel_2)
													.addGap(3)
													.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addGap(4)
													.addComponent(lblNewLabel_2_1)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
													.addComponent(scrollPane_3, 0, 0, Short.MAX_VALUE)
													.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel)
												.addComponent(lblNewLabel_1))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
											.addGap(242))
								);
								
								JCheckBox chckbxNewCheckBox_6 = new JCheckBox("5");
								panel_1_1.add(chckbxNewCheckBox_6);
								
								JCheckBox chckbxNewCheckBox_7 = new JCheckBox("6");
								panel_1_1.add(chckbxNewCheckBox_7);
								
								JCheckBox chckbxNewCheckBox_8 = new JCheckBox("7");
								panel_1_1.add(chckbxNewCheckBox_8);
								
								JCheckBox chckbxNewCheckBox_0 = new JCheckBox("0");
								chckbxNewCheckBox_0.addChangeListener(new ChangeListener() {
									public void stateChanged(ChangeEvent e) {
										
										refresh();
									}
								});
								panel_1.add(chckbxNewCheckBox_0);
								
								JCheckBox chckbxNewCheckBox = new JCheckBox("1");
								panel_1.add(chckbxNewCheckBox);
								
								JCheckBox chckbxNewCheckBox_1 = new JCheckBox("2");
								panel_1.add(chckbxNewCheckBox_1);
								
								JCheckBox chckbxNewCheckBox_3 = new JCheckBox("3");
								panel_1.add(chckbxNewCheckBox_3);
								
								JCheckBox chckbxNewCheckBox_4 = new JCheckBox("4");
								panel_1.add(chckbxNewCheckBox_4);
								
								table_3 = new JTable();
								table_3.setModel(new DefaultTableModel(
									new Object[][] {
										{"W-Rerister", null},
										{"PCL", null},
										{"PCLATH", null},
										{"PC-Intern", null},
										{"STATUS", null},
										{"FSR", null},
									},
									new String[] {
										"SFR+W", ""
									}
								) {
									boolean[] columnEditables = new boolean[] {
										false, false
									};
									public boolean isCellEditable(int row, int column) {
										return columnEditables[column];
									}
								});
								table_3.getColumnModel().getColumn(0).setResizable(false);
								table_3.getColumnModel().getColumn(1).setResizable(false);
								scrollPane_3.setViewportView(table_3);
								frame.getContentPane().setLayout(groupLayout);

								refresh();
	}
	
	public static void refresh() {
//		for (int i = 0; i < stack8.size(); i++) {
//			((DefaultTableModel) table_2.getModel()).setValueAt(0, i, 1);
//		}
		for (int i = 0; i <globalthings.stack8.size(); i++) {
			((DefaultTableModel) table_2.getModel()).setValueAt(globalthings.stack8.elementAt(i), i, 1);
		}
	}
	
	public static void sendDATA() {
		
	}

	public static void changeselectedRow(int row) {
		for (int i = 0; i < ((DefaultTableModel) table_1.getModel()).getRowCount()-1; i++) {
			((DefaultTableModel) table_1.getModel()).setValueAt(false, i, 1);
		}
		if((((DefaultTableModel) table_1.getModel()).getRowCount()-1)>=row&&row>=0) {
			
				((DefaultTableModel) table_1.getModel()).setValueAt(true, row, 1);
			
		}
	}

	@SuppressWarnings("serial")
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Load File");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileread=new fileReader();
			int result = fileChooser.showOpenDialog(frame);

			if (result == JFileChooser.APPROVE_OPTION) {
				for (int i = ((DefaultTableModel) table_1.getModel()).getRowCount()-1; i >=0 ; i--) {
					((DefaultTableModel) table_1.getModel()).removeRow(i);
				}

				File selectedFile = fileChooser.getSelectedFile();
				System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				try {
					fileread.readFileLines(selectedFile.getAbsolutePath());

					for (int i = 0; i < fileReader.linesCodeLineswithcodeCodestring[4].size(); i++) {
						Object[]data= {false,false,fileReader.linesCodeLineswithcodeCodestring[4].get(i)};
						((DefaultTableModel) table_1.getModel()).addRow(data);
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}



			}
		}
	}
	@SuppressWarnings("serial")
	private class SwingAction_1 extends AbstractAction {
		int i=0;
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			changeselectedRow(i++);
			globalthings.stack8.push(i);
			refresh();
			System.out.println("hewwo"+globalthings.stack8.size());
		}
	}
	
	
}
