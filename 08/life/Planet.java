package life;

import java.util.*;

public class Planet {
	
	private World world;
	private ArrayList<Player> players;
	private Random rnd;
	private int delayToBirth;
	private int minPopulation;
	
	/**
	 * ----------------
	 * コンストラクタ
	 * @param xSize マップのxサイズ
	 * @param ySize マップのyサイズ
	 * @param minPopulation 種の個体数がこの数を下回ったら遅延発生
	 * @param delayToBirth 遅延発生の遅延世代
	 * @param seed 乱数のシード
	 * ----------------
	 */
	public Planet(int xSize, int ySize, int minPopulation, int delayToBirth, long seed) {
		this.world = new World(xSize, ySize);
		this.minPopulation = minPopulation;
		this.delayToBirth = delayToBirth;
		this.rnd = new Random(seed);
	}
	
	/**
	 * ----------------
	 * プレイヤーのリストを導入する
	 * @param players
	 * ----------------
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	/**
	 * ----------------
	 * シミュレーションのメイン
	 * ----------------
	 */
	public void simulate () {
		Scanner scanner = new Scanner(System.in);
		
		// 世界は，すべてのプレイヤーが順番に種をn個置いたところから始まる
		for (int j = 0; j < world.getXSize() + world.getYSize(); ++j)
			for (Player p: players)
				p.seeding();
		
		// 初期配置のマップを表示
		world.showMap();
		
		// m世代をシミュレートする
		for (int i = 0; i < 100; ++i) {
			// 画面をクリアする（ターミナルでjavaコマンドで実行したときだけ有効)
			System.out.println("\u001b[2J");

			// 世代更新
			world.update();

			// 種ごとの個体数を表示
			for (Player p: players) {
				char c = p.getId();
				int n = world.getPopulation(c);
				System.out.println ("個体 "+c+" ("+p.getName()+"ムシ) は，" + n + "匹");
			
				// 遅延発生のタイミングか確認
				if (p.isTimeToBirth())
					p.seeding();
				
				// もし規程数minより少ない種があれば，遅延発生カウントダウンフラグを設定
				if (n < minPopulation) {
					p.setBirthCountdown(delayToBirth);
				}
			}
			
			// マップを表示
			world.showMap();

			// 500msだけ待つ
			try {
				Thread.sleep(500);
			} catch (Exception e) {};
				
			
			// ユーザ入力を待つ
//			System.out.print(">");
//			scanner.next();
		}
		// ユーザー入力を終了
		scanner.close();
	}

	/**
	 * ----------------
	 * メインメソッド
	 * @param args
	 * ----------------
	 */
	public static void main(String[] args) {
		// 惑星を生成
		Planet earth = new Planet(20, 20, 12, 3, 20231204); // 20x20, minimum population = 12, delay to birth=3 
		
		// プレイヤーを生成
		ArrayList<Player> players = new ArrayList<>();
		players.add(new Player(earth.world));
		players.add(new Player2124040(earth.world));
		
		// プレイヤーを惑星に登録
		earth.setPlayers(players);
		
		// シミュレーション開始
		earth.simulate();
	}
}
