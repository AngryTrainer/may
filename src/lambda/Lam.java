package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lam {

	public static void main(String[] args) {
		Runnable run=new Runnable(){
			@Override
			public void run() {
				System.out.println("Hello");
				
			}};
			System.out.println(Thread.activeCount());
			//new Thread(run).start();
			System.out.println(Thread.activeCount());
			ArrayList<Integer> al=new ArrayList<Integer>();
			al.add(1);
			al.add(2);
			al.add(3);
			al.add(4);
			al.add(5);
	Integer sumOdd = al.stream().filter(e -> e % 2 != 0).
	reduce((s1, s2) -> s1 + s2).orElse(0);
	List<String> mList = Arrays.asList("aa5","cc2","cc1", "aa2","bb1");
	 
	mList
	    .stream()
	    .filter(s -> s.startsWith("a"))
	    .map(String::toUpperCase)
	    .sorted()
	    .forEach(System.out::println);

			System.out.println(sumOdd);


	}

}
