package exercise1;

/**
 * @author g2124040 藤本陽人
 */
public class CubicRoot {
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(calcCBRoot(0));
    }

    public static double calcCBRoot(int m) {
        double a=0;
        double b=1;
        double epsilon = 1e-10;

        if (m==0||m<0){
            a=0;
        }else{
            while (true){
                a = (2*b+(m/(b*b)))/3;
                if (Math.abs(a-b) >= epsilon){
                    b = a;
                }else{
                    break;
                }          
            }
        }
        return a;
    }
}