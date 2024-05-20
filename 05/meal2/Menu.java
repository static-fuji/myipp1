package meal2;

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
 
        System.out.print(mn);
    }

    public String toString() {
        String s = "Menu::\n";
        for (Meal i:meals){
            s = s + "Meal: " + i.toString() + "\n";
        }
        return s;
    }
}