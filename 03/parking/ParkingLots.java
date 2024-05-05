package parking;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class ParkingLots {
    private final int VACANT = -1, UNPARKED = -1;

    private String name; 
    private int capacity, occupied;
    private int lots[];

    public static void main(String[] args) {
        ParkingLots p = new ParkingLots("P1", 10);
	    System.out.println(p.toString());
        System.out.println("Vacant parking lots: " + (p.getCapacity()-p.getOccupied()));
    }

    ParkingLots(String ParkingName, int ParkingCapa) {
        name = ParkingName;
        capacity = ParkingCapa;
        occupied = 0;
        lots = new int [capacity];

        for (int i = 0; i < this.capacity; i++){
            lots[i]= VACANT;  
        }
    }

    public String getName(){
        return this.name;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public int getOccupied(){
        return this.occupied;
    }

    public String toString() {
        return "The capacity of " + getName() + ": " + getCapacity();
    }
}