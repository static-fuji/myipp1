package exercise1;

/**
 * @author g2124040 藤本陽人
 */
public class CubicRoot {
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(calcCBRoot(80));
    }

    public static double calcCBRoot(int m) {
        double a=0;
        double b=1;

        for (int i = 0;i < 10; i++){
            a = (2*b+(m/(b*b)))/3.0;
            b = a;
        }
        return a;
    }
}