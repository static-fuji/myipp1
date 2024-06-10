package inheritance1;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class Monster {
    //インスタンス変数
    private int monsterID;
    protected int currentTurn;
    protected String name;
    protected int attackAmount;
    protected int hitPoint;
    protected int maxHitPoint;
    protected int exp;
    protected int attackPower;

    //コンストラクタ
	public Monster(int id) {
		this.monsterID = id;
        this.name = "Default";
        this.attackAmount = 0;
        this.maxHitPoint = 100;
        this.hitPoint = this.maxHitPoint;
        this.exp = 100;
        this.attackPower = 10;
        this.currentTurn = 0;
	}
	public void receiveAttack(int damage) {
        this.hitPoint = this.hitPoint - damage;
        if (this.hitPoint < 0) {
            this.hitPoint = 0;
        }
        showDamageMessage(damage);
	}
	public void act() {
		this.attackAmount += this.attackPower;
        this.currentTurn ++;
        showAttackMessage(this.attackPower);
	}
	public boolean isAlive() {
		switch (this.hitPoint) {
            case 0:
                return false;
            default:
                return true;
        }
	}
	public void showDamageMessage(int damage) {
		if(damage > 0) {
			System.out.println(this.getName() + this.getMonsterID() + "に" + damage + "のダメージ！HP:" + this.getHitPoint() + "/" + this.getMaxHitPoint());
			if(this.getHitPoint() == 0) {
				System.out.println(this.getName() + this.getMonsterID() + "を倒した！");
			}
		}
	}
	public void showAttackMessage(int attack) {
		if(attack > 0) {
			System.out.println(this.getName() + this.getMonsterID() + "は" + attack + "の攻撃！");
		}
	}
	public void showHealMessage(int heal) {
		if(heal > 0) {
			System.out.println(this.getName()+ this.getMonsterID() + "は" + heal + "回復した！HP:" + this.getHitPoint() + "/" + this.getMaxHitPoint());	
		}		
	}

    public int getMonsterID() {
        return this.monsterID;
    }

    public int getHitPoint() {
        return this.hitPoint;
    }

    public int getMaxHitPoint() {
        return this.maxHitPoint;
    }

    public String getName() {
        return this.name;
    }

    public int getExp() {
        return this.exp;
    }

    public int getAttackPower() {
        return this.attackPower;
    }

    public int getCurrentTurn() {
        return this.currentTurn;
    }

    public int getAttackAmount() {
        return this.attackAmount;
    }
}