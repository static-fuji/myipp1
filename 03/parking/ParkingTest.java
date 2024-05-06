package parking;

/**
 * @author g2124040 藤本陽人
 * 
 */
public class ParkingTest {
    public static void main(String[] args) {
        ParkingLots[] plist = new ParkingLots[3];
		plist[0] = new ParkingLots("p1", 5);
		plist[1] = new ParkingLots("p2", 8);
		plist[2] = new ParkingLots("p3", 6);
		
        for(int i=0; i < plist.length; i++) {
			ParkingLots p = ParkingLots.addSubLots(plist[i], 0.5);
			System.out.println("ORIGINAL " + plist[i].toString());
			System.out.println("ADDITIONAL " + p.toString());
		}
    }
}