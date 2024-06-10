package inheritance1;
import java.util.ArrayList;
/**
 * @author g2124040 藤本陽人
 * 
 */
public class GameTest {
	public static void main(String[] args) {
		int playerAttackPower = 250;
		Monster monster1 = new Slime(1);
		Monster monster2 = new Monster(2);
		Monster monster3 = new BossDragon(3);
		Monster monster4 = new LuckySlime(4);
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		monsterList.add(monster1);
		monsterList.add(monster2);
		monsterList.add(monster3);
		monsterList.add(monster4);
		int numMonster = monsterList.size();
		for(int i = 0; i < 10;i++) {
			System.out.println("--------Turn " + (i+1) + "--------");
			int targetIndex = i % numMonster;
			System.out.print("プレイヤーの攻撃！");
			Monster targetMonster = monsterList.get(targetIndex);
			if(targetMonster.isAlive()) {
				targetMonster.receiveAttack(playerAttackPower);
			}else {
				System.out.println("ミス！モンスターは既にいなかった！");
			}
			for(int j = 0;j < numMonster;j++) {
				Monster mon = monsterList.get(j);
				if(mon.isAlive()) {
					mon.act();
				}
			}
		}
	}
}