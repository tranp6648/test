package Form;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


import model.categorymodel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Formcategory extends JPanel {
	private JTextField textField;
	private JTable table;
	private boolean dataload=false;
	DefaultTableModel model=new DefaultTableModel();
	private JTextField textFieldid;
	private JTextField textFieldsearch;

	/**
	 * Create the panel.
	 */
	public Formcategory() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 83, 45);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(114, 21, 153, 30);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name=textField.getText();
				if(!Name.isEmpty()) {
					
					model.categorymodel cat=new model.categorymodel();
					cat.setName(Name);
					Dao.categoryDao.insertCategory(cat);
				}else  {
					JOptionPane.showMessageDialog(btnNewButton, "Failed saved");
				}
			
			}
		});
		btnNewButton.setBounds(34, 97, 101, 30);
		add(btnNewButton);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				
			}
		});
		scrollPane.setBounds(34, 151, 347, 185);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int selectedcout=table.getSelectedRow();
					if(selectedcout!=-1) {
						Object idvalue=table.getValueAt(selectedcout, 0);
						if(idvalue!=null) {
							int id=Integer.parseInt(idvalue.toString());
							Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/phong", "root", "Anhthang61@");
						
							Statement smt=conn.createStatement();
							ResultSet rs=smt.executeQuery("Select * from cate where ID="+id);
							while(rs.next()) {
								textField.setText(rs.getString("Name"));
								textFieldid.setText(rs.getString("ID"));
							}
							rs.close();
							conn.close();
							smt.close();
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		model.addColumn("ID");
		model.addColumn("Name");
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnShowProduct = new JButton("Show product");
		btnShowProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!dataload) {
						model.setRowCount(0);
						Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/phong", "root", "Anhthang61@");
						String query="Select * from cate";
						PreparedStatement smt=conn.prepareStatement(query);
						ResultSet rs=smt.executeQuery();
						while(rs.next()) {
						int id=rs.getInt("ID");
						String Name=rs.getString("Name");
							model.addRow(new Object[] {id,Name});
						}
						rs.close();
						smt.close();
						conn.close();
						dataload=true;
					}
				
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			
			}
		});
		btnShowProduct.setBounds(44, 346, 101, 30);
		add(btnShowProduct);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid=textFieldid.getText().toString();
				try {
					String Name, id;
					Name=textField.getText();
					id=textFieldid.getText();
					int priceint=Integer.parseInt(id);
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/phong", "root", "Anhthang61@");
					PreparedStatement pst=conn.prepareStatement("update cate set Name=?,ID=? where ID=?");
					pst.setString(1, Name);
					pst.setString(2,id);
				
					pst.setString(3, pid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(btnNewButton, "Update saved");
					textField.setText("");
					textFieldid.setText("");
					
					
					textFieldid.requestFocus();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(169, 351, 101, 30);
		add(btnUpdate);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblId.setBounds(20, 52, 83, 45);
		add(lblId);
		
		textFieldid = new JTextField();
		textFieldid.setColumns(10);
		textFieldid.setBounds(113, 61, 153, 30);
		add(textFieldid);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid=textFieldid.getText().toString();
				try {
					String Name, id;
					Name=textField.getText();
					id=textFieldid.getText();
					int priceint=Integer.parseInt(id);
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/phong", "root", "Anhthang61@");
					PreparedStatement pst=conn.prepareStatement("Delete from cate where id=?");
					pst.setString(1, pid);
			
				
				
					pst.executeUpdate();
					JOptionPane.showMessageDialog(btnNewButton, "Update saved");
					textField.setText("");
					textFieldid.setText("");
					
					
					textFieldid.requestFocus();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(280, 346, 101, 30);
		add(btnDelete);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				refreshcol();
				textField.setText("");
				textFieldid.setText("");
				dataload=true;
			}
		});
		btnRefresh.setBounds(34, 386, 101, 30);
		add(btnRefresh);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid=textFieldsearch.getText();
				try {
					model.setRowCount(0);
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/phong", "root", "Anhthang61@");
					PreparedStatement pst=conn.prepareStatement("Select * From cate where Name=?");
					pst.setString(1, pid);
					ResultSet rs=pst.executeQuery();
					if(rs.next()==true) {
						int id=rs.getInt("ID");
						String Name=rs.getString("Name");
						model.addRow(new Object[] {id,Name});
					}
					rs.close();
					pst.close();
					conn.close();
					dataload=false;
				} catch (SQLException e2) {
					// TODO: handle exception
					 Logger.getLogger(Formcategory.class.getName()).log(Level.SEVERE, null, e2);
				}
			}
		});
		btnSearch.setBounds(430, 20, 101, 30);
		add(btnSearch);
		
		textFieldsearch = new JTextField();
		textFieldsearch.setColumns(10);
		textFieldsearch.setBounds(378, 68, 153, 30);
		add(textFieldsearch);
	
		

	}
	private void refreshcol() {
		 try {
			 model.setRowCount(0);
			 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/phong","root","Anhthang61@");
			 String query="SELECT * FROM cate";
			 PreparedStatement preparedStament=conn.prepareStatement(query);
			 ResultSet resultSet=preparedStament.executeQuery();
			 while (resultSet.next()) {
				int id=resultSet.getInt("ID");
				String name=resultSet.getString("Name");
				
				model.addRow(new Object[] {id,name});
				
			}
			 resultSet.close();
			 preparedStament.close();
			 conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
