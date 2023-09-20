package pack1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorInterfaceAssign {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(506);
		list.add(53);
		list.add(51);
		list.add(87);
		list.add(1015);
		
		Comparator<Integer> com = (a,b)-> {
			return a.compareTo(b);
		};
		
		list.sort(com);
		
		System.out.println(list);
	}
}
