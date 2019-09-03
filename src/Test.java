import java.io.*;
import java.util.Scanner;


public class Test {
	
	public static Cache cache1,cache2;
	public static String fileName;
	public static int cache1Size,cache2Size;
	public static int NH,NH1,NH2,NR,NR1,NR2;
	public static float HR,HR1,HR2;
	public static Object hold;
	
	public static void main(String[] args) throws IOException {
		//Check arguments//
		usage(args);

		cache1 = new Cache();	//Create cache tree size(1 or 2)//
		cache1.setCachSize(Integer.parseInt(args[2])) ;	//sets 1st cache size
		System.out.println("First level cache with " + cache1.length() + " entries has been created");
		if(args[1].equals("2")) {	//if cache size is 2 add a second cache and file is in index 4//
			cache2 = new Cache();	
			cache2.setCachSize(Integer.parseInt(args[3]));
			fileName = args[4];
			System.out.println("Second level cache with " + cache2.length() + " entries has been created");
		}else {
			fileName = args[3];		//If a single cache, file is in index 3
		}
		
		Scanner input = new Scanner(new FileReader(fileName));  
		while(input.hasNext()) {
		   Object word = input.next();
//		   System.out.println(word);
		   NR1++; 
		   if(cache1.getObject(word) == null) { // if NULL, missed cache1
			   NR2++;
//			   System.out.println("Cache1 - " + cache1.toString());
			   if(cache2 != null) {
				   NR2++;
				   if(cache2.getObject(word) == null) { // if NULL, missed cache2
					   cache2.addObject(word);
					   cache1.addObject(word);
//					   System.out.println("Cache2 - " + cache2.toString());
//					   System.out.println("Cache1 - " + cache1.toString() + "\n");
				   }else {
//					   System.out.println("Removing c2 " + word);
					   NH2++;
					   hold = cache2.getObject(word);
					   cache2.removeObject(hold);
					   cache2.addObject(hold);
//					   System.out.println("Cache2 - " + cache1.toString());
				   }
			   }
			   else {
				   cache1.addObject(word);
			   }
		   }else { // if HIT, cache1
			   NH1++;
//			   System.out.println("Removing c1 " + word + "\n");
			   hold = cache1.getObject(word);
			   cache1.removeObject(hold);
			   cache1.addObject(hold);
//			   System.out.println("Cache1 - " + cache1.toString());
			   if(cache2 != null) { // if HIT, cache2
				   cache2.removeObject(hold);
				   cache2.addObject(hold);
			   }
		   }
		}
		
		//formulas go here
		NR = NR1;
		NH = NH1 + NH2;
		HR = ((float)NH/(float)NR);
		HR1 = ((float)NH1/(float)NR);
		HR2 = ((float)NH2/(float)NR2);
		
		System.out.println("The number of global references: " + NR);
		System.out.println("The number of global cache hits: " + NH);
		System.out.println("The global hit ration: \t\t " + String.format("%.10f", HR) + "\n");
		
		System.out.println("The number of 1st-level references: " + NR1);
		System.out.println("The number of 1st-level cache hits: " + NH1);
		System.out.println("The 1st-level cache hit ration: \t " + String.format("%.10f", HR1) + "\n");
		
		if(cache2 != null) {
			System.out.println("The number of 2nd-level references: " + NR2);
			System.out.println("The number of 2nd-level cache hits: " + NH2);
			System.out.println("The 1st-level cache hit ration: \t " + String.format("%.10f", HR2) + "\n");
		}
		
		cache1.clearCache();
		cache2.clearCache();
		
	}

	private static void usage(String[] args){;
		String statement = "Usage Examples:\n"
				+ "Single Cache - java Test 1 <cache size> <input textfile name>\n"
				+ "Double Cache - java Test 2 <1st-level cache size(smaller)> <2nd-level cache size(larger)> <input textfile name>";
		if(args.length == 0) {
			System.out.println(statement);
			System.exit(0);
		}
		
		int arg1Value = Integer.parseInt(args[1]);
		
		if(args.length < 4 || args.length > 5) {
			System.out.println(statement);
			System.exit(0);
		}
		if(arg1Value == 2 && (Integer.parseInt(args[2]) > Integer.parseInt(args[3]))) {
			System.out.println(statement);
			System.exit(0);
		}
		if(arg1Value < 1 || arg1Value > 2) {
			System.out.println(statement);
			System.exit(0);
		}
		if(arg1Value == 1 && args.length != 4) {
			System.out.println(statement);
			System.exit(0);
		}
		if(arg1Value == 2 && args.length != 5) {
			System.out.println(statement);
			System.exit(0);
		}
		return;
	}
}
