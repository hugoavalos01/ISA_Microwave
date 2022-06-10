package microwave;

public class beepListener {
	
	private static int beeps = 0;
	
	public static void listen (int b) {
		beeps = b;
	}
	
	public static boolean times(int t) {
		int b = beeps;
		beeps = 0;
		return b == t;
	}
}
