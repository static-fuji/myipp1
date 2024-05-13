package medals2;
import java.util.Arrays;

/**
 * @author g2124040 藤本陽人
 * 
 */
public class Ranking {
    /* フィールド(メンバ変数) */
    final int NUM_OF_COUNTRIES = 1000; // 国・地域の数の最大値(定数)
    int next; // ↓で説明
    Country[] countries; 

    /* コンストラクタ */
    public Ranking() {
        countries = new Country[NUM_OF_COUNTRIES];
        next = 0;
    }

    /**
    * 指定された国が配列の何番に登録されているか探し、その添え字を返す
    * @param name 国(3文字のIOCコード)
    * @return nameがcountryNameの何番に格納されているか、なければ-1  
    */
    public int indexOfCountry(String name) {
        for (int i = next - 1; i >= 0; i--) {
            // i番目の国名がnameと同じ文字列ならば i を返す
            if (this.countries[i].getName().equals(name)) return i;
        }
        return -1; // 見つからなければ -1 を返す
    }

    /**
    * IOCコードで指定された国に指定された色のメダルを1つ追加
    * @param name 国(3文字のIOCコード)
    * @param color メダルの色
    */
    public void addMedal(Color color, String name) {
        // indexOfCountryを用いて、nameが配列の何番目に格納されているか探す
        int index = indexOfCountry(name);
        if (index < 0) { // 見つからなかった場合は新たに格納し、nextを増やす 
            countries[next] = new Country(name); // 名前を格納
            index = next; // 格納した場所をindexに
            next ++; // 次の国のためにnextを増やす
        }
        // 色に対応するメダルの数を増やす
        if (color == Color.Gold) {
            countries[index].add(Color.Gold);
        } else if (color == Color.Silver) {
            countries[index].add(Color.Silver);
        } else if (color == Color.Bronze) {
            countries[index].add(Color.Bronze);
        }
        //System.out.println(countries[0]);
    }

    public void sortResults() {
        // TODO 並べ替えを実装したらどうなる?!
        int dataValue = 0;
        for(int i = 0; i < countries.length && countries[i]!= null; i++){
            dataValue ++;
        }
        Arrays.sort(countries,0,dataValue);
    }

    public void printResults() {
        // 集計結果を表示
        for (int i = 0; i < countries.length && countries[i]!= null; i++) {  
            System.out.println(countries[i].toString());
        }
     
    }
}