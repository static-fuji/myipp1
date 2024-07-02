package moji1;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author g2124040 藤本陽人
 * 
 */
public class Player {
    private static final int MAX_TANGO = 200;  // 最大単語数
    private static final String URL_BASE = "https://pman0214.github.io/japanese-addresses-kana/api/ja-obj.json";

    private String urlBase;
    private List<String> targetPrefs;
    protected String name;
    protected int score;
    protected Prefectures prefectures;
    protected Random rnd;
    // TODO ここにTangoクラスのオブジェクトを格納するTreeSetを，dicwordという名前でprotectedで宣言
    protected TreeSet<Tango> dicword = new TreeSet<>();
    

    public Player(String name, List<String> targetPrefs) {
        this.targetPrefs = targetPrefs;
        this.name = name;
        this.score = 0;
        this.rnd = new Random();
        this.dicword = new TreeSet<>();
        this.urlBase = URL_BASE;
    }

    public Player(String name, List<String> targetPrefs, String urlBase) {
        this(name, targetPrefs);
        this.urlBase = urlBase;
    }

    public void studyCities() throws IllegalArgumentException{
        System.out.println(name + "が地図を勉強中... ");
        this.prefectures = parseCityInfo();

        for (final String pref: this.targetPrefs) {
            System.out.print("  " + pref + "の地図を勉強中... ");
            System.out.println(getCityInfo(pref) + "市区町村を学習完了");
        }

        if (this.dicword.size() > this.MAX_TANGO) {
            throw new IllegalArgumentException("単語数が上限を超えています");
        }
    }

    private Prefectures parseCityInfo() {
        String buf= "";
        try {
            BufferedReader br = null;
            URL url = new URL(urlBase);
            InputStream is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));

            buf = br.readLine();
            br.close();
            if (buf == null) {
                System.out.println("地図の読み込みに失敗");
                return null;
            }
        }
        catch (IOException e) {
            System.out.println("URL read error");
        }

        return new Prefectures(buf);
    }

    protected int getCityInfo(String pref) {
        if (this.prefectures == null) {
            return 0;
        }
        ArrayList<City> cities = this.prefectures.getCities(pref);

        for (final City city: cities) {
            String cityName = city.getName();
            String cityKana = city.getNameKana();

            // カタカナをひらがなに変換する
            cityKana = katakanaToHiragana(cityKana);
            // ふりがなが付いている場合のみ単語リストに格納する
            if (cityKana.length() > 0) {
                // TODO cityName, cityKanaからTangoインスタンスを生成し、dicwordに追加
                this.dicword.add(new Tango(cityName,cityKana));
            }
        }
        return cities.size();
    }

    private String katakanaToHiragana(String kana) {
        StringBuffer sb = new StringBuffer(kana);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if ( (c >= 'ア') && (c <= 'ン') ) {
                sb.setCharAt(i, (char)(c - 'ア' + 'あ'));
            }
            else if (c == 'ヵ') {
                sb.setCharAt(i, 'か');
            }
            else if (c == 'ヶ') {
                sb.setCharAt(i, 'け');
            }
            else if (c == 'ヴ') {
                sb.setCharAt(i, 'う');
                i++;
            }
        }
        return sb.toString();
    }

    public String say() {
        return "";
    }

    public String getName() { return this.name; }
    public int getScore() { return this.score; }

    public void addScore(int score) {
        this.score = this.score + score;
    }

    public Tango followTango(String word) {
        return null;
    }

    // テスト用のメイン
    public static void main(String[] args) {
        int count = 0;
        Player rdr = new Player("テストユーザ", Arrays.asList(
            "福岡県", "香川県", "沖縄県"
        ));
        rdr.studyCities();

        for(final Tango t: rdr.dicword) {
            System.out.println(t);
            ++count;
        }
        System.out.println("トータル単語数：" + count);
    }
}
