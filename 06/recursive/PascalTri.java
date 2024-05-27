package recursive;

/**
 * @author bxxxxxx name
 *
 */
public class PascalTri {

	public int nCr(int n, int r) {
		
		//nCrを求める再帰メソッドを実装
		
	}
	
	public void showSTriangle(int n) {
		
		for(int i = 0;i < n;i++) {
			for(int j = 0;j <= i;j++) {
				int val = nCr(i, j);
				String str = "*";
				if(val % 2 == 0) {
					str = " ";
				}
				System.out.print(str);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {

		int n = 4;
		int r = 2;
		
		PascalTri pas = new PascalTri();
		
		System.out.println(n + "C" + r + " = " + pas.nCr(n,r));
		
		//int row = 30;
		//pas.showSTriangle(row);		
	}
}