package meal3;

import java.util.ArrayList;
import java.util.Collections;
/**
 * @author g2124040 藤本陽人
 * 
 */
public class Menu {
    private ArrayList<Meal> meals;
     
    public Menu() {
        // 作業1: ここで変数mealsを初期化する
        meals = new ArrayList<>();
    }
 
    public void sort() {
        Collections.sort(meals);
    }
 
    public void add(Meal m) {
        meals.add(m);
    }
 
    public static void main(String[] args) {
        Menu mn = new Menu();
        mn.add(new Meal("Kake Udon", 310, 270));
        mn.add(new Meal("Kake Soba", 300, 250));
        mn.add(new Meal("Ramen", 500, 500));
        mn.add(new Meal("Curry Rice", 270, 350));
        mn.add(new Meal("Pasta Lunch", 320, 300));
 
        mn.sort();
 
        System.out.println("--- before ---");
        System.out.print(mn);
 
        mn.getMealByName("Kake Udon").soldOut();
        mn.getMealByName("Ramen").soldOut();
 
        System.out.println("--- after ---");
        System.out.print(mn);
 
        System.out.println("--- max-calorie menu <= 300kCal ---");
        System.out.println("Meal: " + mn.getMaxCalorieMeal(300));
        System.out.println("--- max-calorie menu <= 290kCal ---");
        System.out.println("Meal: " + mn.getMaxCalorieMeal(290));
    }

    public String toString() {
        String s = "Menu::\n";
        for (Meal i:meals){
            if (i.isSoldOut() == false){
                 s = s + "Meal: " + i.toString() + "\n";
            }
        }
        return s;
    }

    public Meal getMealByName (String name){
        for (Meal i:meals){
            if (name ==i.getName()){
                return i;
            }
        }
        return null;
    }

    public Meal getMaxCalorieMeal(int calories){
        Meal maxCalo = new Meal(null,0,0);
        boolean initFlag = true;
        for (Meal i:meals){
            if (initFlag == true && i.getCalories() <= calories){
                if (i.isSoldOut() == false){
                    maxCalo = i;
                    initFlag = false;
                }
            }else if (maxCalo.getCalories()< i.getCalories() && i.getCalories() <= calories){
                if (i.isSoldOut() == false){
                    maxCalo = i;
                }
            }
        }
        if (initFlag == true){
            return null;
        }
        return maxCalo;
    }
}