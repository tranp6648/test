package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class categoryDao {
	
public static int insertCategory(model.categorymodel cate) {
	String query="insert into cate(Name) values(?)";
	try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/phong", "root", "Anhthang61@");
			PreparedStatement stmt=conn.prepareStatement(query);){
		stmt.setString(1, cate.getName());
		int i=stmt.executeUpdate();
		return i;
		
	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return -1;
}

	

private static List<model.categorymodel> showall(){
	List<model.categorymodel> cat=new ArrayList<model.categorymodel>();
	String query="Select * from cate";
	try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/phong", "root", "Anhthang61@");
			PreparedStatement stmt=conn.prepareStatement(query);){
		stmt.execute();
		ResultSet rs=stmt.getResultSet();
		while(rs.next()) {
			model.categorymodel item=new model.categorymodel();
			item.setId(rs.getInt("ID"));
			item.setName(rs.getString("Name"));
			cat.add(item);
		}
	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return cat;
}
}
