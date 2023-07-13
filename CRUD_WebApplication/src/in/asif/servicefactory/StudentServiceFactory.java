package in.asif.servicefactory;

import in.asif.service.*;

//Abstraction logic of implementation
public class StudentServiceFactory {

	//make constuctor private to avoid object creation
	private StudentServiceFactory()
	{}
	private static IstudentService studentService=null;
	
	public static IstudentService getStudentServive()
	{    
		//singleton pattern code
		if (studentService == null) 
	     {
		 studentService = new StudentServiceImpl();
		 }
		 return studentService;
	}
	
	
	
}
