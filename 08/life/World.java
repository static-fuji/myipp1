package life;

import java.util.*;

public class World {
	
	private char[][] map;
	private char[][] newMap;
	private int xSize, ySize;

/** =================
 * コンストラクタ
 * @param xSize	mapの横サイズ
 * @param ySize mapの縦サイズ
 * ==================
 */
	public World(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		map = new char[xSize][ySize];
		newMap = new char[xSize][ySize];
		for (int iy = 0; iy < ySize; ++iy)
			for (int ix = 0; ix < xSize; ++ix) {
				map[ix][iy] = '.';
				newMap[ix][iy] = '.';
			}
	}
	
	/** =================
	 * 「今現在」のmapに生物を置く
	 * @param x  生物を置くx位置
	 * @param y  生物を置くy位置
	 * @param id 生物種id（１文字）
	 * @return 置けたらtrue
	 * ==================
	 */
	public boolean putLifeNow(int x, int y, char id) {
		if (x < 0 || xSize < x || y <0 || ySize < y) {
			System.out.println("はみだし");
			return false;
		}
		if (map[x][y] != '.') {
			System.out.println("おけない");
			return false;
		}
		map[x][y] = id;
		return true;
	}
	
	/** =================
	 * 記号idの個体をx,yの位置に仮置きする
	 * @param x  生物を置くx位置
	 * @param y  生物を置くy位置
	 * @param id 生物種id（１文字）
	 * @return 置けたらtrue
	 * ==================
	 */
	public boolean putLife(int x, int y, char id) {
		if (x < 0 || xSize < x || y <0 || ySize < y) {
			System.out.println("おけない");
			return false;
		}
		if (map[x][y] != '.') {
			System.out.println("おけない");
			return false;
		}
		newMap[x][y] = id;
		return true;
	}
	
	/** =================
	 * x,yの位置の個体を殺す（記号'.'にする）
	 * @param x  生物を殺すx位置
	 * @param y  生物を殺すy位置
	 * @return 殺せたらtrue
	 * ==================
	 */
	public boolean killLife(int x, int y) {
		if (x < 0 || xSize < x || y <0 || ySize < y) {
			System.out.println("はみ出し");
			return false;
		}
		if (map[x][y] == '.') {
			System.out.println("もう死んでいる");
			return false;
		}
		newMap[x][y] = '.';
		return true;
	}
	
	/** 
	 * =================
	 * 新世代用のnewMapの内容を現世代のmapにコピーする
	 * 一世代の更新が終わった後に利用する．
	 * ==================
	 */
	public void copyNewMapToMap () {
		for (int y = 0; y < ySize; ++y)
			for (int x = 0; x < xSize; ++x)
				map[x][y] = newMap[x][y];
	}
	
	/** =================
	 * 現在のmap上で生きているすべての種をリストアップする
	 * @return map上にあるすべての種のid(char)をつなげた文字列
	 * ==================
	 */
	public String getSpecies() {
		String species = "";
		for (int y = 0; y < ySize; ++y) 
			for (int x = 0; x < xSize; ++x)
				if (map[x][y] != '.' 
				&&	species.indexOf(map[x][y]) == -1)
					species += map[x][y];
		return species;
	}
	
	/** =================
	 * １世代進める
	 * mapのすべての位置で，生物の生存，死滅，維持を更新する
	 * ==================
	 */
	public void update() {
		// 1. count up number of species
		String species = getSpecies();
		
		// debug
		System.out.println("species:"+species);
		
		// 2. exit if there is no life
		if (species.length()==0) return;
		
		// 3. prepare count box and clear all zero
		int[] counter = new int[species.length()];
		
		// 4. scan all locations on map
		for (int y = 0; y < ySize; ++y) {
			for (int x = 0; x < xSize; ++x) {
				
				// get number of lives for each species
				for (int i = 0; i < counter.length; ++i)
					counter[i] = getLives(x, y, species.charAt(i));

				// get number of all lives
				int allLives = getAllLives(x,y);
				
				// get cell status
				char c = map[x][y];
				
				// 1. spawn?
				if (c == '.' 
				&&	allLives == 3) {
					// condition 1 met
					for (int i = 0; i < counter.length; ++i)
						if (2 <= counter[i]) 
							// condition 2 met and spawn
							putLife(x, y, species.charAt(i));
					continue;
				}
				
				// 2. keep?
				if (c != '.' 
				&&	(allLives == 2 
					|| allLives == 3))
					// condition met and keep
					continue;
				
				// 3. die by sparsity?
				if (c != '.' 
				&&	allLives < 2) {
					// condition met and die
					killLife(x, y);
					continue;
				}
				
				// 4. die by density?
				if (c != '.' 
				&&	4 <= allLives) {
					// condition met and die
					killLife(x, y);
					continue;
				}	
			}
		}
		
		// after all cells are update on newMap, newMap will be copied to map
		copyNewMapToMap();
	}
	
	/** =================
	 * x,yの位置の周囲８近傍に記号idの生物が何個体いるかを返す
	 * @param x
	 * @param y
	 * @param id
	 * @return 個体数
	 * ==================
	 */
	public int getLives(int x, int y, char id) {
		int sum = 0;
		for (int iy = -1; iy <= 1; ++iy) 
			for (int ix = -1; ix <= 1; ++ix) {
				if (ix == 0 && iy == 0) continue; 
				if (map[((x+ix+xSize) % xSize)][(y+iy+ySize) % ySize] == id) 
					++sum;
			}
		return sum;
	}
	
	/** =================
	 * x,yの位置の周囲8近傍に生物が何個体いるかを返す
	 * @param x
	 * @param y
	 * @return 個体数
	 * ==================
	 */
	public int getAllLives(int x, int y) {
		int sum = 0;
		for (int iy = -1; iy <= 1; ++iy) 
			for (int ix = -1; ix <= 1; ++ix) {
				if (ix == 0 && iy == 0) continue;
				if (map[((x+ix+xSize) % xSize)][(y+iy+ySize) % ySize] != '.') 
					++sum;
			}
		return sum;
	}
	
	/** =================
	 * マップ上の生物の分布を表示する
	 * ==================
	 */
	public void showMap() {
		char c;
		for(int iy = 0; iy < ySize; ++iy) {
			for (int ix = 0; ix < xSize; ++ix) {
				c = map[ix][iy];
//				if (c == '.') c = '＿';
				if ((int)c < 256) c = (char)((int)c + 65248);
				System.out.print(c);
			}
			System.out.println();
		}
	}

	/** =================
	 * マップのx方向の大きさを返す
	 * @return
	 * ==================
	 */
	public int getXSize() {
		return xSize;
	}
	
	/** =================
	 * マップのy方向の大きさを返す
	 * @return
	 * ==================
	 */
	public int getYSize() {
		return ySize;
	}
	
	/** =================
	 * マップ全体を返す
	 * @return
	 * ==================
	 */
	public char getMap(int x, int y) {
		if (x < 0 || xSize < x || y <0 || ySize < y) {
			System.out.println("はみだし");
			return '!';
		}
		return map[x][y];
	}
	
	
	/** =================
	 * idの種のmap全体での個体数を返す
	 * @param id
	 * @return　個体数
	 * ==================
	 */
	public int getPopulation (char id) {
		int sum = 0;
		for (int y = 0; y < ySize; ++y) 
			for (int x = 0; x < xSize; ++x)
				if (map[x][y] == id)
					sum += 1;
		return sum;
	}
	
	/** =================
	 * テスト用メイン
	 * ランダムにn種類の種を盤面に配置し，その変化をm秒ごとに表示する
	 * 配置密度は1/xSize+1/ySize程度
	 * @param args
	 * ==================
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random rnd = new Random(20231204);
		
		World w = new World(10, 10);
		for (int i = 0; i < w.getXSize()+w.getYSize(); ++i)
			w.putLife(
					(int)(rnd.nextDouble()*w.getXSize()),
					(int)(rnd.nextDouble()*w.getYSize()),
					(char)((int)'1'+rnd.nextInt(3)));
		w.copyNewMapToMap();
		
		for (int i = 0; i < 10; ++i) {
			// 画面をクリアする（ターミナルでjavaコマンドで実行したときだけ有効)
			System.out.println("\u001b[2J");
			
			// マップを表示
			w.showMap();
			
			// 世代更新
			w.update();
			
			// 500msだけ待つ
			try {
				Thread.sleep(500);
			} catch (Exception e) {};
			
			// 種ごとの個体数を表示
			String species = w.getSpecies();
			for (int j = 0; j < species.length(); ++j) {
				char c = species.charAt(j);
				System.out.println ("個体"+c+"は"+w.getPopulation(c)+"匹");
			}

			// ユーザ入力待ち
//			System.out.print(">");
//			scanner.next();
		}
		// ユーザー入力を終了
		scanner.close();
	}

}
