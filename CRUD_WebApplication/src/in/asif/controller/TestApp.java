package in.asif.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import in.asif.dto.Student;
import in.asif.service.IstudentService;
import in.asif.servicefactory.StudentServiceFactory;

// controller logic
public class TestApp {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. EXIT");
			System.out.print("ENTER UR CHOICE PRESS[1/2/3/4/5]::");
			String option = br.readLine();
			switch(option) {
			case "1":insertOperation();
				
				break;
            case "2":searchOperation();
				
				break;
            case "3":updateOperation();
				
				break;
            case "4":deleteOperation();
				
				break;
            case "5":System.out.println("**********Thanks For Using The Application***********");
                     System.exit(0);
				
				break;	
			default:
				System.out.println("Invalid Option Plz Try With Correct Option");
				break;
			}
		}
		
	}




	private static void updateOperation() throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Student Id to be Updated::");
		String sid=sc.readLine();
		IstudentService studentServive = StudentServiceFactory.getStudentServive();
		Student student = studentServive.searchStudent(Integer.parseInt(sid));
		Student newStudent = new Student();
		if (student != null) {
			System.out.println("Student Id Is::"+student.getId());
			System.out.print("Studentn Old Name is::"+student.getName()+"  Enter New Name::");
			String newName = sc.readLine();
			newStudent.setId(Integer.parseInt(sid));
			if (newName.equals("")||newName=="") 
			{
				newStudent.setName(student.getName());
			} 
			else 
			{
                newStudent.setName(newName);
			}
			System.out.print("Studentn Old Age is::"+student.getAge()+"  Enter New Age::");
			String newAge = sc.readLine();
			if (newAge.equals("")||newAge=="") 
			{
				newStudent.setAge(student.getAge());
			} 
			else 
			{
                newStudent.setAge(Integer.parseInt(newAge));
			}
			System.out.print("Studentn Old Address is::"+student.getAddress()+"  Enter New Address::");
			String newAddress = sc.readLine();
			if (newAddress.equals("")||newAddress=="") 
			{
				newStudent.setAddress(student.getAddress());
			} 
			else 
			{
                newStudent.setAddress(newAddress);
			}
			System.out.println("Old Data "+student);
			System.out.println("Updated Data "+newStudent);
			String status = studentServive.updateStudent(newStudent);
			if (status.equalsIgnoreCase("success")) {
				System.out.println("Data Updated Successfully");
				
			} else   {
				System.out.println("Record Updation Failed");
			}
		}
		else
		{
			System.out.println("Student Record Not Available for Given id::"+sid+"  Given For Updation	");
		}
	}




	private static void deleteOperation() {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter Student Id::");
		Integer id=sc.nextInt();
		IstudentService studentServive = StudentServiceFactory.getStudentServive();
		String status = studentServive.deleteStudent(id);
		if (status.equalsIgnoreCase("success")) {
			System.out.println("Data Deleted Successfully");
			
		} else if (status.equalsIgnoreCase("not found")) {
			System.out.println("Id Not Found ");
		}
		else
		{
			System.out.println("Data Not Deleted !");
		}
		
	}


	private static void searchOperation() {
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter Student Id::");
		Integer id=sc.nextInt();
		IstudentService studentServive = StudentServiceFactory.getStudentServive();
		Student std = studentServive.searchStudent(id);
		if (std!=null) {
			//System.out.println(std);
			System.out.println("ID\tNAME\tAGE\tADDRESS");
			System.out.println(std.getId()+"\t"+std.getName()+"\t"+std.getAge()+"\t"+std.getAddress());
			
		} else {
          System.out.println("Student with id no "+id+" Not Found !");
		}
		
	}
	

	private static void insertOperation() {
		IstudentService studentServive = StudentServiceFactory.getStudentServive();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter Student Name::");
		String name=sc.next();
		System.out.print("Enter Student Age::");
		Integer age=sc.nextInt();
		System.out.print("Enter Student Address::");
		String address=sc.next();
		Student newStudent = new Student();
		newStudent.setName(name);
		newStudent.setAge(age);
		newStudent.setAddress(address);
		
		String msg = studentServive.addStudent(newStudent);
		if (msg.equalsIgnoreCase("success")) {
			System.out.println("Data Inserted Successfully");
			
		} else {
			System.out.println("Data not Inserted ");
		}
		
	}

}
