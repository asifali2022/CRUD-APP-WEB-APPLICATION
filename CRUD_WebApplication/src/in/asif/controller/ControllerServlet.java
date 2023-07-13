package in.asif.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.asif.dto.Student;
import in.asif.service.IstudentService;
import in.asif.servicefactory.StudentServiceFactory;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doProcess(request,response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    IstudentService stdServive = StudentServiceFactory.getStudentServive();
		System.out.println("Request URI::"+request.getRequestURI());
		System.out.println("Request Path::"+request.getPathInfo());
		if (request.getRequestURI().endsWith("addform")) {
			String age = request.getParameter("sage");
			String name = request.getParameter("sname");
			String address = request.getParameter("saddr");
			Student student = new Student();
			student.setName(name);
			student.setAge(Integer.parseInt(age));
			student.setAddress(address);
			String status = stdServive.addStudent(student);
			PrintWriter out = response.getWriter();
			if (status.equals("SUCCESS")) {
			
				out.println("<h1 style='color:green;text-align:center;'>REGISTRATION SUCCESSFULL</h1>");
			}
			else
			{
				out.println("<h1 style='color:green;text-align:center;'>REGISTRATION FAILED</h1>");
			}
	
		}
		if (request.getRequestURI().endsWith("searchform")) {
			String id = request.getParameter("sid");
//			
			Student searchStudent = stdServive.searchStudent(Integer.parseInt(id));
			PrintWriter out = response.getWriter();
			if (searchStudent!=null) {
			
				out.println("<h1 style='color:green;text-align:center;'>MATCH FOUND</h1>");
				out.println("<center>");
				out.println("<table border='2'>");
				out.println("<caption>Student Details</caption>");
				out.println("<tr><th>Student Name</th><td>"+searchStudent.getName() +"</td></tr>");
				//out.println("<tr><td>"+searchStudent.getName() +"</td></tr>");
				out.println("<tr><th>Student Age</th><td>"+searchStudent.getAge() +"</td></tr>");
				//out.println("<tr><td>"+searchStudent.getAge() +"</td></tr>");
				out.println("<tr><th>Student Address</th><td>"+searchStudent.getAddress() +"</td></tr>");
				//out.println("<tr><td>"+searchStudent.getAddress() +"</td></tr>");
				out.println("</center>");
				out.println("</table>");
			}
			else
			{
				out.println("<h1 style='color:green;text-align:center;'>MATCH NOT FOUND</h1>");
			}	
			
	}
		if (request.getRequestURI().endsWith("deleteform")) {
			String id = request.getParameter("sid");
		    String deleteStudent = stdServive.deleteStudent(Integer.parseInt(id));
			
			PrintWriter out = response.getWriter();
			if (deleteStudent=="SUCCESS") {
			
				out.println("<h1 style='color:green;text-align:center;'>STUDENT ID "+id+" FOUND AND DELETED</h1>");
				
			}
			else if (deleteStudent.equalsIgnoreCase("FAILURE")) {
				
				out.println("<h1 style='color:green;text-align:center;'>STUDENT ID "+id+" DELETION FAILED</h1>");
				
			}
			else
			{
				out.println("<h1 style='color:green;text-align:center;'>STUDENT ID "+id+" DOES NOT EXIST </h1>");
			}	
			
	}	
		if (request.getRequestURI().endsWith("editform")) {
			String id = request.getParameter("sid");
		    Student student = stdServive.searchStudent(Integer.parseInt(id));	
		    PrintWriter out = response.getWriter();
			if (student!=null) {
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./controller/updateRecord'>");
				out.println("<table>");
				out.println("<caption style='font-size: 25px'> UPDATE STUDENT DETAILS</caption>");
				out.println("<tr><th>ID</th><td>"+student.getId() +"</td></tr>");
				out.println("<input type='hidden' type='text'name='sid' value='"+student.getId()+"'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='"+student.getName()+"' /></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='"+student.getAge()+"' /></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='"+student.getAddress()+"' /></td></tr>");
				out.println("<tr><td><input type='submit' value='update' /></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
			} else {
			out.println("<h1 style='color:green;text-align:center;'>STUDENT ID "+id+" DOES NOT EXIST </h1>");
			}
	}
		if (request.getRequestURI().endsWith("updateRecord")) {
			
			String id = request.getParameter("sid");
			String name = request.getParameter("sname");
			String age = request.getParameter("sage");
			String address = request.getParameter("saddr");
						
			Student student = new Student();
			
			student.setId(Integer.parseInt(id));
			student.setName(name);
			student.setAge(Integer.parseInt(age));
			student.setAddress(address);
			String status = stdServive.updateStudent(student);
			PrintWriter out = response.getWriter();
			if (status.equals("SUCCESS")) {
				
				out.println("<h1 style='color:green;text-align:center;'>UPDATE SUCCESSFULL</h1>");
			}
			else
			{
				out.println("<h1 style='color:green;text-align:center;'>UPDATE FAILED</h1>");
			}
			
		}
	}
}		