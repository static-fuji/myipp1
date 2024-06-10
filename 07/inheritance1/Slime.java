package inheritance1;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class Slime extends Monster {
    public Slime(int id) {
        super(id);
        this.name = "スライム";
        this.maxHitPoint = 130;
        this.exp = 200;
        this.hitPoint = this.maxHitPoint;
    }
}