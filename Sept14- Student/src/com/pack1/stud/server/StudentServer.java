package com.pack1.stud.server;

import java.util.List;

import com.pack1.stud.db.StudentDB;
import com.pack1.stud.model.Student;

public class StudentServer {
	
	private StudentDB db;

	public StudentServer() {
		db = new StudentDB();
	}
	
	public String insertStudent(Student s) {
		int fromDBId = db.insertStudent(s);
		if (fromDBId != -1) {
			return "New Student ID "+fromDBId;
		}
		else {
			return "Error !!!";
		}
	}
	
	public Student getStudentId(int searchId) {
		return db.getStudentId(searchId);
	}
	
	public List<Student> getAllStudents(){
		return db.getAllStudents();
	}
	
	public List<Student> getStudentByName(String searchName){
		return db.getStudentByName(searchName);
	}
	
	public List<Student> getResult(int marks){
		return db.getResult(marks);
	}
}
