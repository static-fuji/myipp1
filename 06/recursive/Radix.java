package recursive;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class Radix {
	public String dec2Bin(int n) {
		///2進数の文字列を作る処理を実装
        String bins = "";
        if (n<2){
            bins = bins + n;
            return bins;
        }else{
            bins = bins + n%2;
            return dec2Bin(n/2) + bins;
        }		
	}
	
	////0~15を16進数の文字に変換するメソッド
	public char getHexChar(int x) {
		
		String baseStr = "0123456789abcdef";
		
		if(x > 15) {
			x = 15;
		}
		
		return baseStr.charAt(x);
		
	}
	
	public String dec2Hex(int n) {
		
		///16進数の文字列にする処理を実装する
        String hexs = "";
		if (n<16){
            hexs = hexs + getHexChar(n);
            return hexs;
        }else{
            hexs = hexs + getHexChar(n%16);
            return dec2Hex(n/16) + hexs;
        }
	}
	
	public char getRad27Char(int x) {
		///getHexCharを参考に0~26を27進数の文字に変換する処理を実装する
		String baseStr = "0123456789abcdefghijklmnopq";
		
		if(x > 26) {
			x = 26;
		}
		
		return baseStr.charAt(x);
	}
	
	public String dec2Rad27(int n) {
        String rads = "";
		
		///27進数の文字列に変換する処理を実装する
		if (n<27){
            rads = rads + getRad27Char(n);
            return rads;
        }else{
            rads = rads + getRad27Char(n%27);
            return dec2Rad27(n/27) + rads;
        }
	}
	
	
	public static void main(String[] args) {
		
		Radix r = new Radix();
		int val = 5012;
		System.out.println("10進数:" + val);
		System.out.println("2進数:" + r.dec2Bin(val));
		System.out.println("16進数:" + r.dec2Hex(val));
		System.out.println("27進数:" + r.dec2Rad27(val));
	}
}