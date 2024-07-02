package moji1;

import java.io.*;
import java.util.*;

/**
 * @author g2124040 藤本陽人
 * 
 */
class Prefecture {
    private String name;
    private ArrayList<City> cities;

    public Prefecture(String name) {
        this.name = name;
        this.cities = new ArrayList<>();
    }
    public String getName() { return this.name; }
    public ArrayList<City> getCities() { return this.cities; }
    public void addCity(City city) { this.cities.add(city); }
    public String toString() {
        return this.name + ":" + this.cities.size();
    }
}

/**
 * 全都道府県の市区町村情報を扱うクラス
 */
public class Prefectures {
    private String raw_str;
    private ArrayList<Prefecture> prefectures;

    public Prefectures(String str) {
        this.raw_str = str;
        this.prefectures = new ArrayList<>();
        parsePrefectures();
    }

    public void parsePrefectures() {
        String str = this.raw_str;

        // TODO 先頭の「{」と最後の「}」を削除
        str = str.replaceAll("^\\{", "").replaceAll("}$", "");
        // 「"都道府県":[...]」の塊を1行に変換して「[」「]」を削除、都道府県名の後は「@@」を入れる
        str = str.replaceAll("\"([^\"]+)\":\\[", "$1@@").replaceAll("],?", "\n");
        // 行で区切る
        String[] prefs_str = str.split("\n");
        // 1行は1つの都道府県なので、それぞれを処理
        for (final String pref_str: prefs_str) {
            // 「@@」より前の都道府県名を取り出してPrefectureインスタンスを作成
            Prefecture pref = new Prefecture(pref_str.split("@@")[0]);
            // 「@@」より後ろの市区町村を取り出してCityインスタンス群を追加していく
            String p = pref_str.split("@@")[1];
            // TODO 「{...},」の「{」を削除、「}」または「},」を改行に変換
            p = p.replaceAll("\\{", "").replaceAll("},?", "\n");
            // それぞれの行を処理
            String[] cities_str = p.split("\n");
            for (final String city_str: cities_str) {
                // TODO 「"」を削除
                String c = city_str.replaceAll("\"", "");
                // TODO 「,」で区切る
                String[] city_infos = c.split(",");
                // TODO 先頭から「:」までを削除
                city_infos[0] = city_infos[0].replaceAll("^[^:]+:", "");
                city_infos[1] = city_infos[1].replaceAll("^[^:]+:", "");
                // TODO city_infosを使ってcityという名前のCityインスタンスを作成
                City city = new City(city_infos[0], city_infos[1]);
                // Prefectureインスタンスに追加
                pref.addCity(city);
            }
            this.prefectures.add(pref);
        }
    }

    public ArrayList<City> getCities(String prefectureName) {
        ArrayList<City> ret = null;
        for (final Prefecture pref: this.prefectures) {
            if (pref.getName().equals(prefectureName)) {
                ret = pref.getCities();
                break;
            }
        }
        return ret;
    }

    public String toString() {
        String str = "";
        for (final Prefecture pref: this.prefectures) {
            str += pref.toString() + "\n";
        }
        return str;
    }

    // テスト用
    public static void main(String[] args) {
        String buf= "";
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader("src/ja-obj.json"));
                buf = br.readLine();
            br.close();
            if (buf == null) {
                System.out.println("地図の読み込みに失敗");
                return;
            }
        }
        catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }

        Prefectures c = new Prefectures(buf);
        System.out.println(c);
        ArrayList<City> cities = c.getCities("青森県");
        for (final City city: cities) {
            System.out.println(city);
        }
    }
}
