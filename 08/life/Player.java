package life;

/**
 * @author g2124040 藤本陽人
 * 
 */
import java.util.*;

public class Player {
	protected Random rnd;
	protected World world;
	protected char id;
	protected String name;
	protected int birthFlag;
	
	/**
	 * ------------------------
	 * コンストラクタその１
	 * @param world 外部で生成されたグリッドワールドを参照する
	 * @param id マップ上で自分の生物を表わす記号（char)
	 * @param name 自分の種の名前
	 * ------------------------
	 */
	public Player(World world, char id, String name) {
		this.world = world;
		this.id = id;
		this.name = name;
		this.rnd = new Random(); // Random(20231204);
		this.birthFlag = 0;
		
		System.out.println("個体 " + id + " (" + name + "ムシ）　が，世界に参加します");
	}
	
	/**
	 * ------------------------
	 * コンストラクタその２
	 * 名前と記号を自動的に設定する場合はこちらを使う
	 * @param world
	 * ------------------------
	 */
	// TODO 以下のXXXXを授業の説明に従って作る
	public Player(World world) {
		// TODO コンストラクタその2をつかって，idを'ジ'，nameを"ジャワ目ブナン"と設定する
		this.id = 'ジ';
		this.name = "ジャワ目ブナン";
		this.world = world;
		this.rnd = new Random();
		this.birthFlag = 0;
	}
	
	/**
	 * ------------------------
	 * 自分の生物の記号を得る
	 * @return 記号(char)
	 * ------------------------
	 */
	public char getId() {
		return id;
	}
	
	/**
	 * ------------------------
	 * 自分の生物種の名前を得る
	 * @return　名前
	 * ------------------------
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * ------------------------
	 * 遅延発生を設定する
	 * これが呼ばれた後delay世代後に生物を発生させることができる
	 * @param delay 遅延世代数
	 * ------------------------
	 */
	public void setBirthCountdown(int delay) {
		if (birthFlag == 0) birthFlag = delay;
	}
	
	/**
	 * ------------------------
	 * 遅延発生のタイミングが来たかを知る
	 * 遅延発生が設定されて入ればカウントダウンも同時に行う
	 * @return 遅延発生のタイミングであるときは true
	 * ------------------------
	 */
	public boolean isTimeToBirth() {
		// flag=0 means there is no count down
		if (birthFlag == 0) return false;
		
		// time has come when birthFlag changes to 0
		--birthFlag;
		if (birthFlag == 0) {
			System.out.println("個体 " + id + " (" + name + "ムシ） が発生");
			return true;
		}
		return false;
	}
	
	/**
	 * ----------------------------
  	 * サンプルの種配置メソッド
	 * 個体を空いている場所にランダムに１つ置く処理
	 * 
	 * まずmap上の１か所を探索出発点としてランダムに選ぶ
	 * そこから左から右(右端を過ぎたら左端から），上から下（下端を過ぎたら上端から），という順番にmapを見て
	 * もし空いている場所（'.'）があれば，そこに個体を置いて，trueで終了
	 * まったく空き場所がなければfalseで終了（小さな世界でなければ，それはおきない）
	 * @return 個体が置けたら true
	 * ----------------------------
	 */
	// TODO 以下のXXXXの部分を授業の解説にしたがって書き換える
	public boolean seeding() {
		int x = rnd.nextInt(world.getXSize());// TODO worldのxサイズ
		int y = rnd.nextInt(world.getYSize()); // TODO worldのyサイズ
		
		// x方向，y方向という順番にmapを見て，
		for (int iy = 0; iy < world.getYSize(); ++iy) // TODO worldのyサイズ
			for (int ix = 0; ix < world.getXSize(); ++ix) { // TODO worldのxサイズ
				
				// 探す場所をxNow, yNowとおいて（はじを過ぎたら0から戻るように剰余をとって）
				int xNow = (x + ix) % world.getXSize(); // TODO 場所xからixだけ増やした場所，ただしworldのxのサイズを過ぎたら0に戻るように剰余を使う
				int yNow = (y + iy) % world.getYSize(); // TODO 場所yからiyだけ増やした場所，ただしworldのyのサイズを過ぎたら0に戻るように剰余を使う
				
				// その位置のmapの占有状態を見て
				if (world.getMap(xNow, yNow) == '.') {
					// 空地なら種を置いて
					world.putLifeNow(xNow, yNow, id);
					// 成功としてreturn
					return true;
				}
				// そうでなければ次の位置を調べる
			}
		// ここに到達するということは，すべての場所を調べても種を置くことができなかったので，失敗としてreturn
		return false;
	}
}
