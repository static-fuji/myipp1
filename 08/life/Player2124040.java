package life;

/**
 * @author g2124040 藤本陽人
 * 
 */
public class Player2124040 extends Player {

	/**
	 * ------------------
	 * Playerを継承したサンプルのコンストラクタ
	 * 記号と名前を指定して継承元のコンストラクタを呼んでいるだけ
	 * @param world
	 * ------------------
	 */
	public Player2124040(World world) {
		super(world, '朕', "ジャワ目アンド");
	}

	public boolean seeding() {
		int x = 0;// TODO worldのxサイズ
		int y = 0; // TODO worldのyサイズ
		for (int iy=0; iy < world.getYSize(); ++iy){
			for (int ix=0; ix < world.getXSize(); ++ix){
			}
		}
	}

	public int serchAround(int x, int y) {
		int count = 0;
		for (int iy = -1; iy < 2; ++iy)
			for (int ix = -1; ix < 2; ++ix){
				if(world.getMap(x+ix, y+iy) == '.'){
					count++;
				}
			}
		}
	}	
}

