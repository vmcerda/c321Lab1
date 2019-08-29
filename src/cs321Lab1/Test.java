package cs321Lab1;

public class Test {

	public static void main(String[] args) {
		
		//Checks arg length for correct length for single or double cache//
		if(args.length < 4 || args.length > 5 ) {
			System.out.println(usage());
		}else {
			System.out.println("It worked");
		}
		
		Cache newCache = new Cache();

	}

	private static char[] usage() {
		System.out.println(
				"Usage Examples:\n"
				+ "Single Cache - java Test 1 <cache size> <input textfile name>\n"
				+ "Double Cache - java Test 2 <1st-level cache size> <2nd-level cache size> <input textfile name>"
				);
		System.exit(0);
		return null;
	}

}
