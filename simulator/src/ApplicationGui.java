
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class ApplicationGui {
	private JFrame frame;
	private final Action action = new SwingAction();
	private static JTable table;
	private static JTable table_1;
	public fileReader fileread;
	private final Action action_1 = new SwingAction_1();
	private static JTable table_2;
	private static JTable table_3;
	private JRadioButton DEBUGradio;
	private static JCheckBox chckbxNewCheckBox_B0;
	private static JCheckBox chckbxNewCheckBox_B1;
	private static JCheckBox chckbxNewCheckBox_B2;
	private static JCheckBox chckbxNewCheckBox_B3;
	private static JCheckBox chckbxNewCheckBox_B4;
	private static JCheckBox chckbxNewCheckBox_B5;
	private static JCheckBox chckbxNewCheckBox_B6;
	private static JCheckBox chckbxNewCheckBox_B7;
	private static JCheckBox chckbxNewCheckBox_A0;
	private static JCheckBox chckbxNewCheckBox_A1;
	private static JCheckBox chckbxNewCheckBox_A2;
	private static JCheckBox chckbxNewCheckBox_A3;
	private static JCheckBox chckbxNewCheckBox_A4;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JComboBox frequen;
	private JCheckBox Zero_Flag;
	private JCheckBox C_Flag;
	private JCheckBox DC_Flag;
	private JCheckBox PD_Flag;
	private JCheckBox TO_Flag;
	
	
	

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
				btnNewButton_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						startbutton();
					}
				});
				panel.add(btnNewButton_1);
				
				JButton btnNewButton_2 = new JButton("Pause");
				btnNewButton_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						pausebutton();
					}
				});
				panel.add(btnNewButton_2);
				
				JButton btnNewButton_3 = new JButton("Reset Pic");
				btnNewButton_3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						resetbutton();
					}
				});
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
									{"FF", null, null, null, null, null, null, null, null},
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
								
								JLabel lblNewLabel_2_1 = new JLabel("Pin Port B");
								
								JPanel panel_1_1 = new JPanel();
								
								DEBUGradio = new JRadioButton("Debug");
								DEBUGradio.setSelected(true);
								DEBUGradio.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										debugRadio();
									}
								});
								
								JPanel panel_2 = new JPanel();
								
								JPanel panel_3 = new JPanel();
								
								JPanel panel_4 = new JPanel();
								GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
								groupLayout.setHorizontalGroup(
									groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(17)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
															.addGap(8)
															.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addGroup(groupLayout.createSequentialGroup()
																	.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
																		.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(panel_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
																.addGroup(groupLayout.createSequentialGroup()
																	.addComponent(lblNewLabel_2_1, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
																	.addGap(553))
																.addGroup(groupLayout.createSequentialGroup()
																	.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addPreferredGap(ComponentPlacement.UNRELATED)
																	.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
																.addGroup(groupLayout.createSequentialGroup()
																	.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
																	.addGap(553))))
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addGap(4)
															.addComponent(btnNewButton)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(DEBUGradio, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))))
												.addGroup(groupLayout.createSequentialGroup()
													.addContainerGap()
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel)
														.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel_1)
														.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE))))
											.addGap(256))
								);
								groupLayout.setVerticalGroup(
									groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(7)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(5)
													.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnNewButton)
														.addComponent(DEBUGradio))))
											.addGap(4)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(scrollPane_3, 0, 0, Short.MAX_VALUE)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblNewLabel_2)
													.addGap(3)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(lblNewLabel_2_1)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))))
												.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel)
												.addComponent(lblNewLabel_1))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE))
											.addContainerGap(296, Short.MAX_VALUE))
								);
								
								TO_Flag = new JCheckBox("TO");
								TO_Flag.setEnabled(false);
								panel_4.add(TO_Flag);
								
								PD_Flag = new JCheckBox("PD");
								PD_Flag.setEnabled(false);
								panel_4.add(PD_Flag);
								
								Zero_Flag = new JCheckBox("Z");
								Zero_Flag.setEnabled(false);
								panel_4.add(Zero_Flag);
								
								DC_Flag = new JCheckBox("DC");
								DC_Flag.setEnabled(false);
								panel_4.add(DC_Flag);
								
								C_Flag = new JCheckBox("C");
								C_Flag.setEnabled(false);
								panel_4.add(C_Flag);
								
								frequen = new JComboBox();
								frequen.setModel(new DefaultComboBoxModel(new String[] {"32 khz", "100 khz", "500 khz", "1 Mhz", "2  Mhz", "4 Mhz", "8 Mhz", "12 Mhz", "16 Mhz", "20 Mhz"}));
								panel_3.add(frequen);
								
								JButton freqbutton = new JButton("Apply freq");
								freqbutton.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										setfreq();
									}
								});
								panel_3.add(freqbutton);
								
								JLabel lblNewLabel_3 = new JLabel("RamPosInt:");
								panel_2.add(lblNewLabel_3);
								
								textField_1 = new JTextField();
								panel_2.add(textField_1);
								textField_1.setColumns(5);
								
								JLabel lblNewLabel_4 = new JLabel("NewHexVal:");
								panel_2.add(lblNewLabel_4);
								
								textField = new JTextField();
								panel_2.add(textField);
								textField.setColumns(8);
								
								JButton btnNewButton_4 = new JButton("Send to RAM");
								btnNewButton_4.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										overwriteRAM();
									}
								});
								panel_2.add(btnNewButton_4);
								
								chckbxNewCheckBox_B7 = new JCheckBox("7");
								chckbxNewCheckBox_B7.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										
										if(chckbxNewCheckBox_B7.isSelected()) {
											RAM.setRB7(1);
										}else {
											RAM.setRB7(0);
										}
										sendDATA();
										refresh();
									}
								});
								panel_1_1.add(chckbxNewCheckBox_B7);
								
								chckbxNewCheckBox_B0 = new JCheckBox("0");
								chckbxNewCheckBox_B0.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										if(chckbxNewCheckBox_B0.isSelected()) {
											RAM.setRB0(1);
										}else {
											RAM.setRB0(0);
										}
										sendDATA();
										refresh();
									}
								});
								
								chckbxNewCheckBox_B1 = new JCheckBox("1");
								chckbxNewCheckBox_B1.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										
										if(chckbxNewCheckBox_B1.isSelected()) {
											RAM.setRB1(1);
										}else {
											RAM.setRB1(0);
										}
										sendDATA();
										refresh();
									}
								});
								
								chckbxNewCheckBox_B2 = new JCheckBox("2");
								chckbxNewCheckBox_B2.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										
										if(chckbxNewCheckBox_B2.isSelected()) {
											RAM.setRB2(1);
										}else {
											RAM.setRB2(0);
										}
										sendDATA();
										refresh();
									}
								});
								
								chckbxNewCheckBox_B3 = new JCheckBox("3");
								chckbxNewCheckBox_B3.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										
										if(chckbxNewCheckBox_B3.isSelected()) {
											RAM.setRB3(1);
										}else {
											RAM.setRB3(0);
										}
										sendDATA();
										refresh();
									}
								});
								
								chckbxNewCheckBox_B4 = new JCheckBox("4");
								chckbxNewCheckBox_B4.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										
										if(chckbxNewCheckBox_B4.isSelected()) {
											RAM.setRB4(1);
										}else {
											RAM.setRB4(0);
										}
										sendDATA();
										refresh();
									}
								});
								
								chckbxNewCheckBox_B5 = new JCheckBox("5");
								chckbxNewCheckBox_B5.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										
										if(chckbxNewCheckBox_B5.isSelected()) {
											RAM.setRB5(1);
										}else {
											RAM.setRB5(0);
										}
										sendDATA();
										refresh();
									}
								});
								
								chckbxNewCheckBox_B6 = new JCheckBox("6");
								chckbxNewCheckBox_B6.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										
										if(chckbxNewCheckBox_B6.isSelected()) {
											RAM.setRB6(1);
										}else {
											RAM.setRB6(0);
										}
										sendDATA();
										refresh();
									}
								});
								panel_1_1.add(chckbxNewCheckBox_B6);
								panel_1_1.add(chckbxNewCheckBox_B5);
								panel_1_1.add(chckbxNewCheckBox_B4);
								panel_1_1.add(chckbxNewCheckBox_B3);
								panel_1_1.add(chckbxNewCheckBox_B2);
								panel_1_1.add(chckbxNewCheckBox_B1);
								panel_1_1.add(chckbxNewCheckBox_B0);
								
								chckbxNewCheckBox_A4 = new JCheckBox("4");
								chckbxNewCheckBox_A4.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										if(chckbxNewCheckBox_A4.isSelected()) {
											RAM.setRA4(1);
										}else {
											RAM.setRA4(0);
										}
										sendDATA();
										refresh();
									}
								});
								panel_1.add(chckbxNewCheckBox_A4);
								
								chckbxNewCheckBox_A0 = new JCheckBox("0");
								chckbxNewCheckBox_A0.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										if(chckbxNewCheckBox_A0.isSelected()) {
											RAM.setRA0(1);
										}else {
											RAM.setRA0(0);
										}
										sendDATA();
										refresh();
									}
								});
								
								chckbxNewCheckBox_A1 = new JCheckBox("1");
								chckbxNewCheckBox_A1.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										if(chckbxNewCheckBox_A1.isSelected()) {
											RAM.setRA1(1);
										}else {
											RAM.setRA1(0);
										}
										sendDATA();
										refresh();
									}
								});
								
								chckbxNewCheckBox_A2 = new JCheckBox("2");
								chckbxNewCheckBox_A2.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										if(chckbxNewCheckBox_A2.isSelected()) {
											RAM.setRA2(1);
										}else {
											RAM.setRA2(0);
										}
										sendDATA();
										refresh();
									}
								});
								
								chckbxNewCheckBox_A3 = new JCheckBox("3");
								chckbxNewCheckBox_A3.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										if(chckbxNewCheckBox_A3.isSelected()) {
											RAM.setRA3(1);
										}else {
											RAM.setRA3(0);
										}
										sendDATA();
										refresh();
									}
								});
								panel_1.add(chckbxNewCheckBox_A3);
								panel_1.add(chckbxNewCheckBox_A2);
								panel_1.add(chckbxNewCheckBox_A1);
								panel_1.add(chckbxNewCheckBox_A0);
								
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
								RAM.resetRAM();
								//RAM ramgui=new RAM();
								setfreq();
								debugRadio();
								refresh();
	}
	
	public void debugRadio() {
		globalthings.debugMode=DEBUGradio.isSelected();
		System.out.println(globalthings.debugMode);
		
	}

	public static void setfreq() {
		switch (frequen.getSelectedIndex()) {
		case 0:
			globalthings.freqInt=32*1000;
			break;
		case 1:
			globalthings.freqInt=100*1000;
			break;
		case 2:
			globalthings.freqInt=500*1000;
			break;
		case 3:
			globalthings.freqInt=1*1000000;
			break;
		case 4:
			globalthings.freqInt=2*1000000;
			break;
		case 5:
			globalthings.freqInt=4*1000000;
			break;
		case 6:
			globalthings.freqInt=8*1000000;
			break;
		case 7:
			globalthings.freqInt=12*1000000;
			break;
		case 8:
			globalthings.freqInt=16*1000000;
			break;
		case 9:
			globalthings.freqInt=20*1000000;
			break;
		}
		if(globalthings.debugMode) {
			//System.out.println(frequen.getSelectedIndex());
			System.out.println(globalthings.freqInt);
		}
	}
	public void overwriteRAM() {
		if (!textField_1.getText().equals("")&&!textField.getText().equals("")){
			String rampos="0x"+textField_1.getText();
			int ramposI=Integer.decode(rampos);
			String ramval="0x"+textField.getText();
			int ramvalI=Integer.decode(ramval);
			if(ramvalI>255||ramposI>255||ramvalI<0||ramposI<0) {
				if(globalthings.debugMode) {
					System.out.println("Wrong input for ram");
				}
			}else {
				RAM.bank[ramposI]=ramvalI;
				textField_1.setText("");
				textField.setText("");
				refresh();
			}
		}
	}
	
	public void startbutton() {
		
	}
	
	public void pausebutton() {
		
	}
	
	public void resetbutton() {
		RAM.resetRAM();
		
	}
	
	
	
	public void refresh() {
		//long start = new Date().getTime();
		//while(new Date().getTime() - start < 100L){}
		
//		for (int i = 0; i < stack8.size(); i++) {
//			((DefaultTableModel) table_2.getModel()).setValueAt(0, i, 1);
//		}
		//stackanzeige
		for (int i = 0; i <globalthings.stack8.size(); i++) {
			((DefaultTableModel) table_2.getModel()).setValueAt(Integer.toHexString(globalthings.stack8.elementAt(i)), i, 1);
		}
		//RAManzeige
		int pos=0;
		for (int i = 0; i < 32; i++) {
			for (int j = 1; j < 9; j++) {
				String temp=Integer.toHexString(RAM.getRegisterContent(pos));
				table.getModel().setValueAt(temp, i, j);
				pos++;
				//System.out.println(pos);
			}
		}
		
		//w + sfr
		{
			table_3.getModel().setValueAt("0x"+Integer.toHexString(RAM.w), 0, 1);
			table_3.getModel().setValueAt("0x"+String.format("%4s", Integer.toHexString(RAM.bank[RAM.PCL])).replace(' ', '0'), 1, 1);
			table_3.getModel().setValueAt("0x"+Integer.toHexString(RAM.bank[RAM.PCLATH]), 2, 1);
			//table_3.getModel().setValueAt("0x"+Integer.toHexString(RAM.bank[RAM.PCintern], 3, 1);
			table_3.getModel().setValueAt("0b"+String.format("%8s", Integer.toBinaryString(RAM.bank[RAM.STATUS])).replace(' ', '0'), 4, 1);
			table_3.getModel().setValueAt("0x"+Integer.toHexString(RAM.bank[RAM.FSR]), 5, 1);
		//status
			C_Flag.setSelected(RAM.getC()>0);
			DC_Flag.setSelected(RAM.getDC()>0);
			Zero_Flag.setSelected(RAM.getZ()>0);
			PD_Flag.setSelected(RAM.getPD()>0);
			TO_Flag.setSelected(RAM.getTO()>0);
		}
		//PIN A0-4
		chckbxNewCheckBox_A0.setSelected(RAM.getRA0()>0);
		chckbxNewCheckBox_A1.setSelected(RAM.getRA1()>0);
		chckbxNewCheckBox_A2.setSelected(RAM.getRA2()>0);
		chckbxNewCheckBox_A3.setSelected(RAM.getRA3()>0);
		chckbxNewCheckBox_A4.setSelected(RAM.getRA4()>0);
		//PIN B0-7

		chckbxNewCheckBox_B0.setSelected(RAM.getRB0()>0);
		chckbxNewCheckBox_B1.setSelected(RAM.getRB1()>0);
		chckbxNewCheckBox_B2.setSelected(RAM.getRB2()>0);
		chckbxNewCheckBox_B3.setSelected(RAM.getRB3()>0);
		chckbxNewCheckBox_B4.setSelected(RAM.getRB4()>0);
		chckbxNewCheckBox_B5.setSelected(RAM.getRB5()>0);
		chckbxNewCheckBox_B6.setSelected(RAM.getRB6()>0);
		chckbxNewCheckBox_B7.setSelected(RAM.getRB7()>0);
		
		
	}
	
	public void sendDATA() {
		if(globalthings.debugMode) {
			System.out.println("sendDATA");
		}
		//RA0-4	
		
		
		
		
		
		//RB0-7
		
		
		if (globalthings.debugMode) {

			System.out.println(RAM.getPORTA());

			System.out.println(RAM.getPORTB());
		}
		refresh();
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
			putValue(NAME, "Refresh/testing");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			//changeselectedRow(i++);
			//globalthings.stack8.push(i);
			refresh();
			//System.out.println("hewwo"+globalthings.stack8.size());
		}
	}
}
