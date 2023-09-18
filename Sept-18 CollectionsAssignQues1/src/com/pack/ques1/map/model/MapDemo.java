package com.pack.ques1.map.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class MapDemo {
	Map<Integer, String> map = new HashMap<>();

	public static void main(String[] args) {
	
		MapDemo obj = new MapDemo();
		for(int i = 0; i <4; i++) {
			obj.insertMap();
		}
		obj.display();

		obj.getValuesByKey();
	}
	
	public void insertMap() {
		System.out.println("Enter the key : ");
		int key = new Scanner(System.in).nextInt();
		
		System.out.println("Enter the value : ");
		String value = new Scanner(System.in).nextLine();
		
		map.put(key, value);
		
	}
	public void display() {
		for(Map.Entry<Integer, String> map2 : map.entrySet()) {
			System.out.println(map2.getKey()+" "+map2.getValue());
		}
	}
	
	public void getValuesByKey() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter the key to be search");
		int x = sc.nextInt();
		if(map.containsKey(x)) {
			String value = map.get(x);
			System.out.println("Value for key "+x+" : "+value);
		}
		else {
			System.out.println("Key "+x+" not found in map");
		}
	}
}
