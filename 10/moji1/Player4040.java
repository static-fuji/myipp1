package moji1;

import java.util.*;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class Player4040 extends Player {

    // TODO 自分でつけた任意のチーム名，都道府県に変更してください．
    private static String name = "ああああああああああああ";
    private static List<String> targets = Arrays.asList(
        "富山県", "福岡県"
    );

    // TODO コンストラクタもクラス名に直すのをわすれずに
    public Player4040() {
        super(name, targets);
    }

    // TODO ここを自分のアルゴリズムに書き換えてください
    public Tango followTango(String word) {
        // 単語を自分の辞書から取る部分のサンプル
        for(final Tango t: super.dicword) {
            if(word.charAt(word.length()-1) == t.getKana().charAt(0)) {
                return t;
            }
        }
        // 文字が見つからないときには必ずnullを返すこと
        return null;
    }

    // TODO 文字決定後に出力されるメッセージです．自由に書いてください．文字決定の結果を反映させると良いです．
    public String say() {
        String[] messages = {
            "勉強が足りないようですネ\n",
            "修行が足りないのでは？\n",
            "まだまだ負けませんよ\n"
        };
        return messages[super.rnd.nextInt(messages.length)];
    }

    // テスト用メイン．五十音の1文字に対して続く語がきちんと選択されているかをチェック
    public static void main(String[] args) {
        Player4040 player = new Player4040();
        player.studyCities();
        String test =
        "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよわ";
        for(int i = 0; i < test.length(); ++i)
            System.out.println(test.charAt(i) + "　→　" +
                    player.followTango(test.substring(i, i+1)));
    }
}
