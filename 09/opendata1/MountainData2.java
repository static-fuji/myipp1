package opendata1;

import opendata0.ReadFromURL;
import java.util.*;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class  MountainData2 extends MountainData{
	private final int INDEX_NAME = 1;
	private final int INDEX_KNAME = 2;
	private final int INDEX_HEIGHT = 3;
	private final int INDEX_LOCATION = 5;
	private ArrayList<Mountain> data2;
	private double average;

    public MountainData2() {
        super();
        this.data2 = new ArrayList<Mountain>();
        this.average = 0;
    }

    public void register (){
        for(String line : this.data) {
            try{
            String s = line;
            String[] word = s.split("\t");
            int height =  Integer.parseInt(word[INDEX_HEIGHT]);
            Mountain mountain = new Mountain(word[INDEX_NAME],word[INDEX_KNAME],height, word[INDEX_LOCATION]);
            this.data2.add(mountain);
            this.average += height;
            } catch(Exception e) {
                this.count ++;
            }
        }
        this.average = this.average / this.data2.size();
    }

    public String toString(){
        String totalData = "";
        for(Mountain m : this.data2) {
            totalData = totalData + m.toString() + "\n";
        }
        return totalData + "average: " + this.average + "m";
    }

    public void sortByKname(){
        Collections.sort(data2, new Comparator<Mountain> () {
            @Override
            public int compare(Mountain m1, Mountain m2) {
                return m1.getKname().compareTo(m2.getKname());
            }
        });
    }

    public void sortByHeight(){
        Collections.sort(data2, new Comparator<Mountain> () {
            @Override
            public int compare(Mountain m1, Mountain m2) {
                return Integer.valueOf(m2.getHeight()).compareTo(Integer.valueOf(m1.getHeight()));
            }
        });
    }

    public static void main(String[] args) {
		MountainData2 trfd = new MountainData2();
		trfd.register();
        trfd.sortByKname();
		System.out.println(trfd.toString());
	}
}