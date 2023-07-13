package in.asif.service;

import in.asif.Daofactory.StudentDaoFactory;
import in.asif.dto.Student;
import in.asif.persistence.IstudentDao;
import in.asif.servicefactory.StudentServiceFactory;

// service layer logic 
public class StudentServiceImpl implements IstudentService {
	
	private IstudentDao stdDao ;
	@Override
	public String addStudent(Student student) {
		stdDao = StudentDaoFactory.getStudentDao();
		if (stdDao!=null) {
			return stdDao.addStudent(student);
		} else {
         return "FAILURE";		
         }
		
	}

	@Override
	public Student searchStudent(Integer id) {
		stdDao = StudentDaoFactory.getStudentDao();
		
		return stdDao.searchStudent(id);
	}

	@Override
	public String updateStudent(Student student) {
        stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.updateStudent(student);
	}

	@Override
	public String deleteStudent(Integer id) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.deleteStudent(id);
	}

}
