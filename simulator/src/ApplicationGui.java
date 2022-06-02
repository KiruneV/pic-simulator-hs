
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


public class ApplicationGui {
	public static StopWatch watch;
	public static Stack8<Integer> stack8=new Stack8<Integer>(8);
	private JFrame frame;
	private final Action action = new SwingAction();
	private static JTable table;
	private static JTable table_1;
	public fileReader fileread;
	private final Action action_1 = new SwingAction_1();
	private static JTable table_2;
	

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
		watch=StopWatch.create();
		
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
								{0, null},
								{1, null},
								{2, null},
								{3, null},
								{4, null},
								{5, null},
								{6, null},
								{7, null},
							},
							new String[] {
								"Nr", "Ringbuffer"
							}
						) {
							@SuppressWarnings("rawtypes")
							Class[] columnTypes = new Class[] {
								Integer.class, Object.class
							};
							@SuppressWarnings({ "unchecked", "rawtypes" })
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
							boolean[] columnEditables = new boolean[] {
								false, false
							};
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						});
						table_2.getColumnModel().getColumn(0).setResizable(false);
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
								GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
								groupLayout.setHorizontalGroup(
									groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(7)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addGap(4)
															.addComponent(btnNewButton))))
												.addGroup(groupLayout.createSequentialGroup()
													.addContainerGap()
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel)
														.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel_1))))
											.addContainerGap(860, Short.MAX_VALUE))
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
											.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
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
								frame.getContentPane().setLayout(groupLayout);

								refresh();
	}
	
	public static void refresh() {
//		for (int i = 0; i < stack8.size(); i++) {
//			((DefaultTableModel) table_2.getModel()).setValueAt(0, i, 1);
//		}
		for (int i = 0; i <stack8.size(); i++) {
			((DefaultTableModel) table_2.getModel()).setValueAt(stack8.elementAt(i), i, 1);
		}
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
			stack8.push(i);
			refresh();
			System.out.println("hewwo"+stack8.size());
		}
	}
}
