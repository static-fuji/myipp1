package moji1;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class Tango implements Comparable<Tango> {

	private String kanji;
	private String kana;
	
	public Tango(String kanji, String kana) {
		// - 小さい文字を大きい文字に変換
		// - 濁音・半濁音を普通の文字に変換
		String replace_target = "ぁぃぅぇぉっゃゅょがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ";
		String replace_with   = "あいうえおつやゆよかきくけこさしすせそたちつてとはひふへほはひふへほ";
		for (int i = 0; i < replace_target.length(); ++i)
			kana = kana.replace(replace_target.charAt(i), replace_with.charAt(i));
		// ひらがな以外の文字は削除
		kana = kana.replaceAll("[^あ-ん]", "");
		// 末尾の「市・区・町・村」は削除
		if ("市区町村".indexOf(kanji.charAt(kanji.length() - 1)) >= 0) {
			if ( (kana.endsWith("し")) || (kana.endsWith("く")) ) {
				kana = kana.substring(0, kana.length() - 1);
			}
			else if (kana.endsWith("ちよう")) {
				kana = kana.substring(0, kana.length() - 3);
			}
			else if ( (kana.endsWith("そん")) || (kana.endsWith("むら")) || (kana.endsWith("まち")) ) {
				kana = kana.substring(0, kana.length() - 2);
			}
		}

		this.kanji = kanji;
		this.kana = kana;
	}
	
	public String getKanji() { return this.kanji; }
	public String getKana() { return kana; }

	@Override
	public int compareTo(Tango o) {
		// TODO Auto-generated method stub
		return this.kana.compareTo(o.getKana());
	}
	
	public String toString() {
		return "「" + this.kana + "（" + this.kanji + "）」";
	}
}