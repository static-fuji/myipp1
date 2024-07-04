package graph1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class Point {
    private int id; // このインスタンスのidを格納
    private HashMap<Point, Double> map; // このインスタンスから直接行ける他のPointとそこまでの時間を格納
   
    /**
     * コンストラクタ
     * @param id 地点id
     */
    public Point(int id) {
        this.id = id;
        this.map = new HashMap<>();
    }
 
    /**
     * 地点pへの経路を追加
     * @param p 行き先
     * @param t 移動時間
     */
    public void addPath(Point p, double t) {
        map.put(p, t);
    }
 
    /**
     * addPathでHashMapに追加されているすべてのパスについて、第一引数で指定された
     * ゴールまでたどり着けるすべての経路を再帰呼出しを用いて表示する
     * @param goal ゴール
     * @param path これまでたどってきた経路
     */
    public void pathTo(Point goal, ArrayList<Point> path) {
        // TODO どんな処理が必要か各自で判断して実装
        path.add(this);
        if (this.equals(goal)) {
            System.out.println(path);
        } else {
            for (Point p: map.keySet()) {
                if (!path.contains(p)) {
                    p.pathTo(goal, new ArrayList<>(path));
                }
            }
        }
    }

    public double timeTo(Point p) {
        double minTime = Double.MAX_VALUE;
        boolean found = false;

        if (this.id == p.id) {
            return 0;
        }

        if (p.equals(null)){
            return -1;
        }
        for (Point point : map.keySet()) {
            
            double directTime = map.get(point);
            //System.out.println("a");
            double recursiveTime = point.timeTo(p);

            // 再帰呼び出しが-1を返した場合
            if (recursiveTime == -1) {
                continue;
            }

            double totalTime = directTime + recursiveTime;
            minTime = Math.min(minTime, totalTime);
            found = true;
        }

        return found ? minTime : -1;
    }
 
    /*
     * @return idを文字列化したもの
     */
    public String toString() {
        return Integer.toString(id);
    }
}