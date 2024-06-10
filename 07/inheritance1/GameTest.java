package inheritance1;
import java.util.ArrayList;
/**
 * @author g2124040 藤本陽人
 * 
 */
public class GameTest {
	public static void main(String[] args) {

		GameTest gt = new GameTest();
		int playerAttackPower = 200;
		int numTurn = 28;
		Monster monster1 = new Slime(1);
		Monster monster2 = new Slime(2);
		Monster monster3 = new LuckySlime(3);
		Monster monster4 = new BossDragon(4);
		Monster monster5 = new BossDragon(5);
		Monster monster6 = new LuckySlime(6);
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		monsterList.add(monster1);
		monsterList.add(monster2);
		monsterList.add(monster3);
		monsterList.add(monster4);
		monsterList.add(monster5);
		monsterList.add(monster6);
		int numMonster = monsterList.size();
		for(int i = 0; i < numTurn;i++) {
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
		System.out.println("総攻撃量: " + gt.getTotalAttackAmount(monsterList));
		System.out.println("総経験値量: " + gt.getTotalExp(monsterList));
		gt.showDefeatedMonster(monsterList);
	}


	public int getTotalAttackAmount(ArrayList<Monster> monsters){
		int totalAttackAmount = 0;
		for(Monster m : monsters) {
			totalAttackAmount += m.getAttackAmount();
		}
		return totalAttackAmount;
	}

	public int getTotalExp(ArrayList<Monster> monsters){
		int totalExp = 0;
		for(Monster m : monsters) {
			if (m.isAlive() == false){
				totalExp += m.getExp();
			}
		}
		return totalExp;
	}

	public void showDefeatedMonster(ArrayList<Monster> monsters){
		int totalSlime = 0;
		int totalLuckySlime = 0;
		int totalBossDragon = 0;

		for(Monster m : monsters) {
			if (m.isAlive() == false){
				switch(m.getName()) {
				case "スライム":
					totalSlime += 1;
					break;
				case "ボスドラゴン":
					totalBossDragon += 1;
					break;
				case "ラッキースライム":
					totalLuckySlime += 1;
					break;
				default:
					break;
				}
			}
		}

		System.out.println("<倒されたモンスター> " + "スライム:" + totalSlime + ", " + "ラッキースライム:" + totalLuckySlime +", " + "ボスドラゴン:" + totalBossDragon);
	}
}