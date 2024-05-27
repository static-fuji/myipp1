package recursive;

/**
 * @author g2124040 藤本陽人
 * 
 */
public class FactorialFor {
    public static int fact(int n) {
		if (n == 0) {
            return 1;
        }else{
            return n * fact(n - 1);
        }
	}
	
	public static void main(String[] args) {
		int value = 5;
		System.out.println(value + "! = " + fact(value));
	}
}