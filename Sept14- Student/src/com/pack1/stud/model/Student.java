package com.pack1.stud.model;

import java.util.Objects;

public class Student {
	private String name;
	private int id;
	private int age;
	private int marks;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	public Student(String name, int id, int age, int marks) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
		this.marks = marks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", age=" + age + ", marks=" + marks + "]";
	}

	@Override
	public int hashCode() {
		return name.length()+8;
	}

	@Override
	public boolean equals(Object obj) {
		Student s = (Student) obj;
		
		boolean b1 = (this.id == s.getId());
		boolean b2 = (this.name == s.getName());
		boolean b3 = (this.age == s.getAge());
		boolean b4 = (this.marks == s.getMarks());
		
		return b1&& b2&& b3 && b4;
	}
	
}
