package meal1;

import java.util.Arrays;
/**
 * @author g2124040 藤本陽人
 * 
 */
public class Menu {
    private Meal[] meals;
    private int next;
     
    public Menu(int max_meals) {
        meals = new Meal[max_meals];
        next = 0;
    }
 
    public void sort() {
        Arrays.sort(this.meals,0,next);
    }
 
    public void add(Meal m) {
        meals[next++] = m;
    }
 
    public static void main(String[] args) {
        Menu mn = new Menu(10);
        mn.add(new Meal("Kake Udon", 310, 270));
        mn.add(new Meal("Kake Soba", 300, 250));
        mn.add(new Meal("Ramen", 500, 500));
        mn.add(new Meal("Curry Rice", 270, 350));
        mn.add(new Meal("Pasta Lunch", 320, 300));
 
        mn.sort();
 
        for (int i = 0; i < mn.meals.length; i++) {
            if (mn.meals[i] != null) {
                System.out.println(mn.meals[i]);
            }
        }
    }
}