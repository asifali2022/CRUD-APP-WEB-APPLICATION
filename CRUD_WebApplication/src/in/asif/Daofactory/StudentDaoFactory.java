package in.asif.Daofactory;

import in.asif.persistence.*;

public class StudentDaoFactory {

	private StudentDaoFactory() {}
	
	public static IstudentDao StudentDao =null;
	
	public static IstudentDao getStudentDao()
	{
		if (StudentDao == null) {
			StudentDao=new StudentDaoImpl();
		}
		return StudentDao;
	}
}