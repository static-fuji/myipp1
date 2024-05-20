package meal3;
 
import java.util.Arrays;
 
/**
 * @author g2124040 藤本陽人
 * 
 */
public class Meal implements Comparable {
    private static final int SELL_OUT = 0;
    private static final int IN_STORE = 1;
 
    private String name;   // 食事名
    private int price;     // 金額
    private int calories;  // カロリー
    private int status;    // 状態
 
    public Meal(String name, int price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
 
        status = IN_STORE;
    }
 
    public String toString() {
        String s = name + ", " + price + "yen, " + calories + "kCal";
        return s;
    }
 
    public static void main(String[] args) {
        Meal [] meals = new Meal[5];
        meals[0] = new Meal("Kake Udon", 310, 270);
        meals[1] = new Meal("Kake Soba", 300, 250);
        meals[2] = new Meal("Ramen", 500, 500);
        meals[3] = new Meal("Curry Rice", 270, 350);
        meals[4] = new Meal("Pasta Lunch", 320, 300);
 
        Arrays.sort(meals);
        for (int i = 0; i < meals.length; i++) {
            System.out.println("Meal Info.:: " + meals[i].toString());
        }
    }

    public int compareTo(Object o) {
        Meal m = (Meal)o;
        // 作業3: 以下のreturn文を書き換えてカロリーの少ない順に並べ替える
        return this.calories - m.calories;
    }

    public void soldOut(){
        status = SELL_OUT;
    }

    public String getName(){
        return this.name;
    }

    public int getCalories(){
        return this.calories;
    }

    public boolean isSoldOut(){
        if (status == 1){
            return false;
        }
        return true;
    }
}