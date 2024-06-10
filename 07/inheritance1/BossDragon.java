package inheritance1;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class BossDragon extends Monster {
    //コンストラクタ
    public BossDragon(int id) {
        super(id);
        this.name = "ボスドラゴン";
        this.maxHitPoint = 500;
        this.exp = 700;
        this.attackPower = 120;
        this.hitPoint = this.maxHitPoint;
    }

    public void receiveAttack(int damage) {
        System.out.print("マジックシールドによってダメージが半減！");
        damage /= 2;
        super.receiveAttack(damage);
    }
}