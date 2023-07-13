package in.asif.persistence;

import in.asif.dto.Student;

public interface IstudentDao {
	
	// operation 
	public String addStudent(Student student);
	
	public Student searchStudent(Integer id);
	
	public String updateStudent(Student student);
	
	public String deleteStudent(Integer id);
	

}
