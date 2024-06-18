package inheritance1;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class LuckySlime extends Slime {
    public LuckySlime(int id) {
        super(id);
        this.name = "ラッキースライム";
        this.exp = 8000;
    }

    public void action(){
        super.act();
        if(this.currentTurn % 3 == 0) {
            this.hitPoint += 100;
            if (this.hitPoint > this.maxHitPoint) {
                this.hitPoint = this.maxHitPoint;
            }
            showHealMessage(100);
        }
    }
}