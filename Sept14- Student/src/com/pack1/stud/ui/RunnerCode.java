package com.pack1.stud.ui;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.pack1.stud.model.Student;
import com.pack1.stud.server.StudentServer;

public class RunnerCode {
	
	Scanner sc = new Scanner(System.in);
	
	StudentServer server;

	public RunnerCode() {
		server = new StudentServer();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunnerCode rc = new RunnerCode();
		
		while(true) {
			System.out.println("----* Select Option *----");
			System.out.println("1. Add Student Details");
			System.out.println("2. Display all details of Student");
			System.out.println("3. Search Student By ID");
			System.out.println("4. Search Student By Name");
			System.out.println("5. Search Student By Marks");
			System.out.println("0. Exit\n");
			
			System.out.println("Enter Option : ");
			switch (new Scanner(System.in).nextInt()) {
			case 1:
				rc.fillStudentForm();
				break;
			case 2 :
				rc.getAllStudents();
				break;
			case 3 : 
				rc.getStudentByID();
				break;
			case 4 : 
				rc.getStudentByName();
				break;
			case 5 : 
				rc.getResult();
				break;
			case 0 :
				System.exit(0);
			}
			System.out.println("\n");
		}
	}
	
	public void fillStudentForm() {
		System.out.println("Enter Student Name : ");
		String name = sc.next();
		
		System.out.println("Enter Student Age : ");
		int age = sc.nextInt();
		
		System.out.println("Enter Marks : ");
		int marks = sc.nextInt();
		
		int id = new Random().nextInt(1000);
		
		Student s = new Student(name,id,age,marks);
		
		String notification = server.insertStudent(s);
		System.out.println("Notification : "+notification);
	}
	
	public void displayStudents(Student s) {
		System.out.println(s.getName()+" "+s.getId()+" "+s.getAge()+" "+s.getMarks());
	}
	
	public void getStudentByID() {
		System.out.println("Enter ID to Searched : ");
		int searchId = Integer.parseInt(sc.nextLine());
		
		Student s = server.getStudentId(searchId);
		
		if(s != null)
			displayStudents(s);
		else System.out.println("Employee Not Found For ID : "+searchId);
	}
	
	public void getStudentByName() {
		System.out.println("Enter Student Name to be Searched : ");
		String searchName = sc.next();
		
		List<Student> nameListDB = server.getStudentByName(searchName);
		for (Student student : nameListDB) {
			displayStudents(student);
		}
	}
	
	public void getResult() {
		System.out.println("Enter Student Marks : ");
		int markR = Integer.parseInt(sc.nextLine());
		
		List<Student> result = server.getResult(markR);
		for (Student student : result) {
			displayStudents(student);
		}
	}
	
	public void getAllStudents() {
		List<Student> allSavedStudent = server.getAllStudents();
		
		for (Student student : allSavedStudent) {
			displayStudents(student);
		}
	}

}
