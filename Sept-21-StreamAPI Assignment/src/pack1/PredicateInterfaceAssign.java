package pack1;

import java.util.function.Predicate;

public class PredicateInterfaceAssign {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Predicate<String> stringFruit = (t)->{
			System.out.println(t);
			return true;
		};
		
		
		String fruit[] = {"Apple", "Banana", "WaterMelon", "MuskMelon"};
		for(String i : fruit) {
			stringFruit.test(i);
		}
	}

}
