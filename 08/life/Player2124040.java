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
		super(world, '⬜', "ジャワ目アンド");
	}

	public boolean seeding() {
		int x = 0;// TODO worldのxサイズ
		int y = 0; // TODO worldのyサイズ
		int serchLine = 0;
		boolean doubleEmptyFlag = false;
		int turn = 0;
		boolean emptyFlag = true;
		
		// x方向，y方向という順番にmapを見て，
		for (int iy = 0; iy < world.getYSize(); ++iy) {// TODO worldのyサイズ
			for (int ix = 0; ix < world.getXSize(); ++ix) { // TODO worldのxサイズ
				emptyFlag = true;
				if (world.getMap(ix, iy) != '.'){
					emptyFlag = false;
				}
			}
			if (emptyFlag == true){
				doubleEmptyFlag = true;
			}else{
				doubleEmptyFlag = false;
			}

			if (doubleEmptyFlag == true && emptyFlag == true && turn >=1){
				for (int i=0;i<world.getXSize();i++){
					if (i%2 == 0 && world.getMap(i, iy) == '.'){
						world.putLifeNow(i, iy, id);
					}else if (world.getMap(i, iy) == '.'){
						world.putLifeNow(i, iy-1, id);
					}
				}
				return true;
			}
			turn ++;
		}
		// ここに到達するということは，すべての場所を調べても種を置くことができなかったので，失敗としてreturn
		return false;
	}

}
