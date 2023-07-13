package in.asif.persistence;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import in.asif.dto.Student;
import in.asif.util.*;

//Persistence logic using jdbc api
public class StudentDaoImpl implements IstudentDao {
    
	@Override
	public String addStudent(Student student) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		Scanner sc=null;
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				String sql="INSERT INTO student(`name`,`age`,`address`) VALUES(?,?,?)";
				 pstmt = connection.prepareStatement(sql);
			}
		    if(pstmt!=null)
		    {
		    	pstmt.setString(1,student.getName());
		    	pstmt.setInt(2, student.getAge());
		    	pstmt.setString(3,student.getAddress());
		    	
		    	int rowUpdated = pstmt.executeUpdate();
		    	if (rowUpdated==1) {
		    		
		    		return "SUCCESS";
					
				} 
		    }
				
			}
		
		 catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "FAILURE";
	}

	@Override
	public Student searchStudent(Integer id) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;
		Student student=null;
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				String sql="SELECT `id`,`name`,`age`,`address` FROM student WHERE `id`=? ";
				 pstmt = connection.prepareStatement(sql);
			}
		    if(pstmt!=null)
		    {
		    	pstmt.setInt(1, id);
		    	resultSet = pstmt.executeQuery();
		    	if (resultSet != null) {
		    		
		    		if(resultSet.next()) {
		    			student = new Student();
		    			student.setId(resultSet.getInt(1));
		    			student.setName(resultSet.getString(2));
		    			student.setAge(resultSet.getInt(3));
		    			student.setAddress(resultSet.getString(4));
		    			return student;
						
					}
				}
		    	
		    	
		    }
				
			}
		
		 catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
		
	}

	@Override
	public String updateStudent(Student student) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		Scanner sc=null;
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				String sqlUpdateQuery="Update student set name=?,age=?,address=? where id=? ";
				 pstmt = connection.prepareStatement(sqlUpdateQuery);
			}
		    if(pstmt!=null)
		    {
		    	pstmt.setString(1,student.getName());
		    	pstmt.setInt(2, student.getAge());
		    	pstmt.setString(3, student.getAddress());
		    	pstmt.setInt(4,student.getId());
		    	
		    	int rowUpdated = pstmt.executeUpdate();
		    	if (rowUpdated==1) {
		    		
		    		return "SUCCESS";
					
				} 
		    }
				
			}
		
		 catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "FAILURE";
	}

	@Override
	public String deleteStudent(Integer id) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		Scanner sc=null;
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				String sql="DELETE FROM `student` WHERE `id`=?";
				 pstmt = connection.prepareStatement(sql);
			}
		    if(pstmt!=null)
		    {
		    	
		    	pstmt.setInt(1, id);
		    	
		    	
		    	int rowUpdated = pstmt.executeUpdate();
		    	if (rowUpdated==1) {
		    		
		    		return "SUCCESS";
					
				}
		    	else {return "Failure";}
		    }
				
			}
		
		 catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "FAILURE";

}
}