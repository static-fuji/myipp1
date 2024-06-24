package opendata1;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class Mountain {
	private String name;     // 山の名前
	private String kname;    // 山の名前（カナ表記）
	private int height;      // 標高
	private String location; // 所在地（都道府県）
	
	public Mountain(String n, String k, int h, String l) {
		name = n;
		kname = k;
		height = h;
		location = l;
	}
	
	public String toString() {
		return name + "(" + location + ") 標高:" + height + "m";
	}
}