package com.pack1.stud.db;

import java.util.ArrayList;
import java.util.List;

import com.pack1.stud.model.Student;

public class StudentDB {
	
	List<Student> allStudents = new ArrayList<>();
	
	public int insertStudent(Student s) {
		boolean status = allStudents.add(s);
		return status ? s.getId() : -1;
	}
	
	public Student getStudentId(int searchId) {
		for (Student student : allStudents) {
			if(student.getId() == searchId)
			{
				return student;
			}
		}
		return null;
	}
	
	public List<Student> getStudentByName(String searchName){
		List<Student> tempList = new ArrayList<>();
		for (Student student : allStudents) {
			if(student.getName().equals(searchName))
			{
				tempList.add(student);
			}
		}
		return tempList;
	}
	
	public List<Student> getResult(int markR){
		List<Student> tempList = new ArrayList<>();
		for (Student student : allStudents) {
			if(student.getMarks() > 60)
			{
				tempList.add(student);
			}
		}
		return tempList;
	}

	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return allStudents;
	}
}
