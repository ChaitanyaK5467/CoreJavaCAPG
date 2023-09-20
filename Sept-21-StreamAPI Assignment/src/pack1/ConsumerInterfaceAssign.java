package pack1;

import java.util.function.Consumer;

public class ConsumerInterfaceAssign {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Consumer<String> con2 = (t)->{
			System.out.println(t + " add on");
		};
		
		String str[] = {"Development", "Testing", "IT-IS", "HR"};
		for (String string : str) {
			con2.accept(string);
		}
	}

}
