package medals0;

/**
 * @author g2124040 藤本陽人
 * 
 */
public class Ranking {
    /* フィールド(メンバ変数) */
    final int NUM_OF_COUNTRIES = 1000; // 国・地域の数の最大値(定数)
    String[] countryName; // 国名を入れる配列
    int[] gold, silver, bronze; // 各色の獲得メダル数を入れる配列宣言 
    int next; // ↓で説明

    /* コンストラクタ */
    public Ranking() {
        countryName = new String[NUM_OF_COUNTRIES]; // 領域確保  
        gold = new int[countryName.length]; // 領域確保
        silver = new int[countryName.length]; // 領域確保
        bronze = new int[countryName.length]; // 領域確保
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
            if (countryName[i].equals(name)) return i;
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
            countryName[next] = name; // 名前を格納
            index = next; // 格納した場所をindexに
            next ++; // 次の国のためにnextを増やす
        }
        // 色に対応するメダルの数を増やす
        if (color == Color.Gold) {
            gold[index]++;
        } else if (color == Color.Silver) {
            silver[index]++;
        } else if (color == Color.Bronze) {
            bronze[index]++;
        }
    }

    public void sortResults() {
        // TODO 並べ替えを実装したらどうなる?!
        for (int i = 1; i < countryName.length && countryName[i] != null; i++) {
            int j = i - 1;
            // i番目のデータを待避しておく
            String c = countryName[i];
            int g = gold[i];
            int s = silver[i];
            int b = bronze[i];
            // i番目の国名がj番目の国名よりも前である間くり返す
            while (j >= 0 && c.compareTo(countryName[j]) < 0) {
                // j番目のデータをj+1番目にコピー
                countryName[j+1] = countryName[j];
                gold[j+1] = gold[j];
                silver[j+1] = silver[j];
                bronze[j+1] = bronze[j];
                j--;
            }
            j++;
            // 待避しておいたi番目のデータをj番目にコピー
            countryName[j] = c;
            gold[j] = g;
            silver[j] = s;
            bronze[j] = b;
        }
    }

    public void printResults() {
        // 集計結果を表示
        for (int i = 0; i < countryName.length && countryName[i] != null; i++) {  
            System.out.print(countryName[i]);
            int sum = gold[i];
            System.out.print("(" + gold[i]);
            sum += silver[i];
            System.out.print("," + silver[i]);
            sum += bronze[i];
            System.out.print("," + bronze[i]);
            System.out.println(")[" + sum + "]");
        }
     
    }
}