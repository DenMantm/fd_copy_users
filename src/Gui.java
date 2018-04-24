import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;


public class Gui extends JFrame {

	private JPanel contentPane;
	private JTextField userId;
	ArrayList<Database> dbList;
	User u;
	private JTextField user_id_2;
	Connection_db_get c;
	ArrayList <JCheckBox> CheckBoxList_update;
	ArrayList <JCheckBox> CheckBoxList_insert;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Gui() {
		
		
		//Initializing connection
		
		c = new Connection_db_get();
		
		// This is the test to add list of db's
		  final ArrayList<Database> dbList = new ArrayList<Database>();
		  
		  //Populating db list from the database
		  c.databaseList(dbList);
		
		
		
		setTitle("User_copyower_tool V0.6Beta");
		//this.dbList = dbList;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 718);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setBounds(10, 24, 313, 621);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		userId = new JTextField();
		userId.setBounds(99, 34, 86, 20);
		panel.add(userId);
		userId.setColumns(10);
		
		JLabel lblUserid = new JLabel("User_ID:");
		lblUserid.setBounds(24, 37, 53, 14);
		panel.add(lblUserid);	
		
		final Label label_1 = new Label("Please press Fetch DATA to verify");
		label_1.setBounds(24, 125, 275, 22);
		panel.add(label_1);
		
		Label label = new Label("From DB:");
		label.setBounds(18, 60, 59, 22);
		panel.add(label);
		
		final Choice choice = new Choice();
		choice.setBounds(99, 60, 95, 20);
		panel.add(choice);
		
		Button button_1 = new Button("Fetch DATA");
		button_1.setBounds(214, 60, 70, 22);
		panel.add(button_1);
		
		Label label_3 = new Label("Inserting User");
		label_3.setBounds(184, 0, 115, 22);
		panel.add(label_3);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 217, 293, 2);
		panel.add(separator_2);
		
		JLabel label_9 = new JLabel("Test Systems to apply to:");
		label_9.setBounds(74, 198, 149, 14);
		panel.add(label_9);
		
	
		
		Button button = new Button("Copy Ower");
		button.setBounds(229, 589, 70, 22);
		panel.add(button);
		
		JPanel insert_db_list = new JPanel();
		insert_db_list.setBounds(24, 227, 281, 356);
		panel.add(insert_db_list);
		
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//need to get list of checked boxes now, checking which check boxes are selected and adding to list
				//skipping check box with test system from which we copied user
			
			ArrayList<Database> chb_list = new ArrayList<Database>();
			
			for (int i = 0; i<CheckBoxList_insert.size(); i++){
				if(CheckBoxList_insert.get(i).isSelected()&&choice.getSelectedItem()!=CheckBoxList_insert.get(i).getText()){
					chb_list.add(getElementFromDbList(dbList,CheckBoxList_insert.get(i).getText()));
				}
			}
			
			
				
				//prompting Yes / No
				int dialogResult = JOptionPane.showConfirmDialog (contentPane, "Are you sure you want to do copyower of the user?");
				if(dialogResult == JOptionPane.YES_OPTION){
				
					//getting data from the list and iterating trough by applying insert to each selected db
				
				//getting insert statement from user object
				String insert = u.returnInsert();
				
				for (int i = 0; i < chb_list.size();i++){
					
					Database ins = chb_list.get(i);
					Connect_db_set insert_db = new Connect_db_set();
					insert_db.connect(contentPane,ins, insert);

				}
				JOptionPane.showMessageDialog(contentPane, "Done!");
				}

			}
		});
		
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 24, 313, 621);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		final JCheckBox chckbxInstitutionList = new JCheckBox("Institution List");
		chckbxInstitutionList.setBounds(20, 124, 109, 23);
		panel_1.add(chckbxInstitutionList);
		
		final JCheckBox chckbxDefaultInstitution = new JCheckBox("Default Institution");
		chckbxDefaultInstitution.setBounds(159, 124, 136, 23);
		panel_1.add(chckbxDefaultInstitution);
		
		final JCheckBox chckbxAccessGroups = new JCheckBox("Access groups");
		chckbxAccessGroups.setBounds(20, 145, 119, 23);
		panel_1.add(chckbxAccessGroups);
		
		final JCheckBox chckbxModuleAccess = new JCheckBox("Module Access");
		chckbxModuleAccess.setBounds(159, 145, 119, 23);
		panel_1.add(chckbxModuleAccess);
		
		final JCheckBox chckbxUpdateAllDetails = new JCheckBox("Update all details");
		chckbxUpdateAllDetails.setBounds(79, 171, 128, 23);
		panel_1.add(chckbxUpdateAllDetails);
		
		JPanel update_db_list = new JPanel();
		update_db_list.setBounds(10, 217, 293, 366);
		panel_1.add(update_db_list);
		update_db_list.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		final Choice choice_1 = new Choice();
		choice_1.setBounds(71, 54, 95, 20);
		panel_1.add(choice_1);
		 
		  
		  CheckBoxList_update = new ArrayList();
		  CheckBoxList_insert = new ArrayList();
		  String dbName = null;
		  for (int i = 0; i< dbList.size();i++){
			  dbName = dbList.get(i).sid;
			  JCheckBox tmp_upd = new JCheckBox(dbName);
			  CheckBoxList_update.add(tmp_upd);
			  //adding to the gui
			  update_db_list.add(tmp_upd);
			  
			  
			  JCheckBox tmp_ins = new JCheckBox(dbName);
			  CheckBoxList_insert.add(tmp_ins);
			  //adding to the gui
			  insert_db_list.add(tmp_ins);

			  choice_1.add(dbName);
			  choice.add(dbName);
		  }
		  
		
		user_id_2 = new JTextField();
		user_id_2.setColumns(10);
		user_id_2.setBounds(71, 28, 86, 20);
		panel_1.add(user_id_2);
		
		Button button_2 = new Button("Update User");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//need to get list of checked boxes now, checking which check boxes are selected and adding to list
				//skipping check box with test system from which we copied user
				ArrayList<Database> chb_list = new ArrayList<Database>();
				
				for (int i = 0; i<CheckBoxList_update.size(); i++){
					if(CheckBoxList_update.get(i).isSelected()&&choice_1.getSelectedItem()!=CheckBoxList_update.get(i).getText()){
						chb_list.add(getElementFromDbList(dbList,CheckBoxList_update.get(i).getText()));
					}
				}
				
				
				//prompting Yes / No
				int dialogResult = JOptionPane.showConfirmDialog (contentPane, "Are you sure you want to do copyower of the user?");
				if(dialogResult == JOptionPane.YES_OPTION){
				
					//getting data from the list and iterating trough by applying insert to each selected db
				for (int i = 0; i < chb_list.size();i++){
					Database ins = chb_list.get(i);
					Connect_db_set insert_db = new Connect_db_set();
					
					
					if(chckbxUpdateAllDetails.isSelected()){
						
						insert_db.connect(contentPane,ins, u.updateEverything());
						System.out.println(ins.sid+ " Update Everything: "+u.updateEverything());

					}
					else{
						if(chckbxInstitutionList.isSelected()){ 
							insert_db.connect(contentPane,ins, u.updateInstitutionList());
							System.out.println(ins.sid+ " Update Institution List: "+ u.updateInstitutionList());
							}
						
						if(chckbxAccessGroups.isSelected()){ 
							
							insert_db.connect(contentPane,ins, u.updateAccessGroups());
							System.out.println(ins.sid+ " Update Institution List: "+ u.updateAccessGroups());
							}
						
						if(chckbxDefaultInstitution.isSelected()) {
							insert_db.connect(contentPane,ins, u. updateDefaultInstitution());
							System.out.println(ins.sid+ " Update Default inst: "+ u. updateDefaultInstitution());
							}
						
						if(chckbxModuleAccess.isSelected()) {
							insert_db.connect(contentPane,ins, u.updateModuleAccess());
							System.out.println(ins.sid+ " Update module access: "+ u.updateModuleAccess());
							}
						
						
					}
					//inserting to database
					//insert_db.connect(contentPane,ins, u.returnInsert());

					//update institutions list
					//insert_db.connect(contentPane,ins, u.updateInstitutionList());
					
					//access groups
					//insert_db.connect(contentPane,ins, u.updateAccessGroups());
					//default institution
					//insert_db.connect(contentPane,ins, u. updateDefaultInstitution());
					//module access
					//insert_db.connect(contentPane,ins, u.updateModuleAccess());
					


				}
				
				
				
				
				JOptionPane.showMessageDialog(contentPane, "Done!");
				
				}
			}
		});
		button_2.setBounds(216, 589, 87, 22);
		panel_1.add(button_2);
		

		
		JLabel label_4 = new JLabel("User_ID:");
		label_4.setBounds(13, 31, 59, 14);
		panel_1.add(label_4);
		
		Label label_5 = new Label("From DB:");
		label_5.setBounds(10, 51, 59, 22);
		panel_1.add(label_5);
		
		
		
		final Label label_7 = new Label("Please press Fetch DATA to verify");
		label_7.setBounds(20, 79, 275, 22);
		panel_1.add(label_7);
		
		Button button_3 = new Button("Fetch DATA");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userid;
				//get DATA from database
				//Connection_db_get c = new Connection_db_get();
				
				if(user_id_2.getText()==null||user_id_2.getText().length()!=6){
					JOptionPane.showMessageDialog(contentPane, "UserId has to be 6 symbols, please try again!");
					return;
				}
				try{
				userid = Integer.parseInt(user_id_2.getText());
				}
				catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(contentPane, "UserId should consist only from numbers!");
					return;
				}
				
				//discovering which database should be used
				  //generate list of servers
//				  ArrayList<Database> dbList = new ArrayList<Database>();
//				  dbList.add(new Database("BW3_ALFA","172.21.64.72"));
//				  dbList.add(new Database("BW3_BETA","172.21.64.72"));
//				  dbList.add(new Database("BW3_BASE","172.21.64.72"));
//				  dbList.add(new Database("BW3_IOTA","172.21.64.73"));
//				  dbList.add(new Database("BW3_OPS","172.21.64.72"));
//				  dbList.add(new Database("BW3_QA","172.21.64.73"));
//				  dbList.add(new Database("BW3_GAMA","172.21.64.72"));
//				  dbList.add(new Database("BW3_REG","172.21.64.72"));
//				  dbList.add(new Database("BW3_TEST","172.21.64.73"));
//				  dbList.add(new Database("PAR_BW3","172.21.64.72"));
//				  dbList.add(new Database("SYSIMP","172.21.64.72"));
//				  dbList.add(new Database("SYSIMP_D","172.21.64.72"));
				
				  Database db = null;
				  System.out.println(choice_1.getSelectedItem());
				  //looping trough and selecting necesarry db object from the list
				for (int i =0; i < dbList.size();i++){
					
					if (dbList.get(i).sid == choice_1.getSelectedItem()){
						db = dbList.get(i);
						
					}
					
				}
				
				
				//connecting, this will return at the end user object
				//passing content panel to be able to put message dialog in centre of the window
				u = c.connect(userid,contentPane,db);
				//changing content of the field for suer to see fetched row
				label_7.setText("User fetched: "+ u.USERNAME);
				System.out.println(u.returnInsert());
				
				
			}
		});
		button_3.setBounds(186, 51, 70, 22);
		panel_1.add(button_3);
		
		Label label_6 = new Label("Update User Profile");
		label_6.setBounds(159, 0, 130, 22);
		panel_1.add(label_6);
		

		
		Label label_8 = new Label("Details to copy:");
		label_8.setBounds(90, 98, 86, 20);
		panel_1.add(label_8);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 217, 293, 2);
		panel_1.add(separator);
		
		JLabel lblTestSystemsTo = new JLabel("Test Systems to apply to:");
		lblTestSystemsTo.setBounds(74, 198, 149, 14);
		panel_1.add(lblTestSystemsTo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 118, 293, 2);
		panel_1.add(separator_1);
		

		
		
		
		
		
		Label label_2 = new Label("Support: Deniss.Strods@firstdata.com");
		label_2.setBounds(60, 647, 228, 22);
		contentPane.add(label_2);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 329, 21);
		contentPane.add(menuBar);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmInsertNewUser = new JMenuItem("Insert New User");
		mnOptions.add(mntmInsertNewUser);
		
		mntmInsertNewUser.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	panel.setVisible(true);
		    	panel_1.setVisible(false);
		    	
		    }
		});
		
		
		JMenuItem mntmUpdateExistingUser = new JMenuItem("Update Existing User");
		mnOptions.add(mntmUpdateExistingUser);
		mntmUpdateExistingUser.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	panel_1.setVisible(true);
		    	panel.setVisible(false);
		        
		    }
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userid;
				//get DATA from database
				Connection_db_get c = new Connection_db_get();
				
				if(userId.getText()==null||userId.getText().length()!=6){
					JOptionPane.showMessageDialog(contentPane, "UserId has to be 8 symbols, please try again!");
					return;
				}
				try{
				userid = Integer.parseInt(userId.getText());
				}
				catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(contentPane, "UserId should consist only from numbers!");
					return;
				}
				
				
				  Database db = null;
				  System.out.println(choice.getSelectedItem());
				  //looping trough and selecting necesarry db object from the list
				for (int i =0; i < dbList.size();i++){
					
					if (dbList.get(i).sid == choice.getSelectedItem()){
						db = dbList.get(i);
						
					}
					
				}
				
				
				//connecting, this will return at the end user object
				//passing content panel to be able to put message dialog in centre of the window
				u = c.connect(userid,contentPane,db);
				//changing content of the field for suer to see fetched row
				label_1.setText("User fetched: "+ u.USERNAME);
				System.out.println(u.returnInsert());
				
			}
		});
		//adding manually
//		choice.add("BW3_ALFA");
//		choice.add("BW3_BETA");
//		choice.add("BW3_BASE");
//		choice.add("BW3_IOTA");
//		choice.add("BW3_OPS");
//		choice.add("BW3_QA");
//		choice.add("BW3_REG");
//		choice.add("BW3_CERT");
//		choice.add("BW3_TEST");
//		choice.add("BW3_GAMA");
//		choice.add("PAR_BW3");
//		choice.add("SYSIMP");
//		choice.add("SYSIMP_D");
		
    	panel.setVisible(true);
    	panel_1.setVisible(false);
		
	}
	
	//Small Helpper function here to find and return required element from the list
	public  Database getElementFromDbList(ArrayList<Database> dbList,String searchFor){
		Database d = null;
		
		System.out.println(dbList.size());
		
		for (Database item : dbList) {
			
			if(item.sid==searchFor){
				d = item;
			}
		}
		return d;
	}
	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
