package parking;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class ParkingLots {
    private final int VACANT = -1;//駐車区画の空き状況であることを示す
    private final int UNPARKED = -1;//駐車場内に指定した車両が見つからないことを示す

    private String name; //駐車場の名称
    private int capacity;//駐車場の駐車区画数
    private int occupied;//使用中の駐車区画数
    private int[] lots;//駐車区画の利用状況

    public static void main(String[] args) {
        ParkingLots p = new ParkingLots("P1", 10);
	    System.out.println(p.toString());
        System.out.println("Vacant parking lots: " + (p.getCapacity()-p.getOccupied()));
        p.park(3, 6331);
	    System.out.println("Vacant parking lots: " + (p.getCapacity()-p.getOccupied()));
	    System.out.println("Find car 6331: " + p.findCar(6331));
	    System.out.println("Find car 1336: " + p.findCar(1336));
	    System.out.println("Unpark 1336: " + p.unpark(1336));
	    System.out.println("Unpark 6331: " + p.unpark(6331));
	    System.out.println("Vacant parking lots: " + (p.getCapacity()-p.getOccupied()));
    }

    ParkingLots(String ParkingName, int ParkingCapa) {
        name = ParkingName;
        capacity = ParkingCapa;
        occupied = 0;
        lots = new int [capacity];

        for (int i = 0; i < capacity; i++){
            lots[i]= VACANT;  
        }
    }

    //空き情報を確認するメソッド
    public boolean isVacant(){
        boolean emptyFlag = false;
        for (int parkNum = 0; parkNum < capacity; parkNum++){
            if(lots[parkNum] == VACANT){
                emptyFlag = true;
            }
        }
        return emptyFlag;
    } 

    //特定の駐車区画の空き情報を確認
    public boolean isVacant(int parkNum){
        boolean emptyFlag = false;
        if(parkNum >=0 && parkNum < capacity){
            switch (lots[parkNum]) {
                case VACANT:
                    emptyFlag = true;
                    break;
                default:
            }
        }
        return emptyFlag;
    }

    //駐車区画に車両を追加
    public boolean park(int parkNum, int carNum){
        boolean addCarFlag = false;
        if(parkNum >=0 && parkNum < capacity){
            if(isVacant() == true){
                lots[parkNum] = carNum;
                occupied++;
                addCarFlag = true;
            }
        }
        return addCarFlag;
    }

    //指定した車両が使用している駐車区画を調査
    public int findCar(int carNum){
        int parkingCar = UNPARKED;
        for(int parkNum = 0; parkNum < capacity; parkNum++){
            if(lots[parkNum] == carNum){
                parkingCar = parkNum;
                break;
            }
        }
        return parkingCar;
    }

    //指定した車両を駐車場から出す
    public boolean unpark(int carNum){
        boolean parkingFlag = false;
        for(int parkNum = 0; parkNum < capacity; parkNum++){
            if(lots[parkNum] == carNum){
                lots[parkNum] = VACANT;
                occupied--;
                parkingFlag = true;
                break;
            }
        }
        return parkingFlag;
    }

    public static ParkingLots addSubLots(ParkingLots p, double n){
        double newCapacity = (double)p.capacity*n;
        ParkingLots newParking = new ParkingLots(p.name + "-sub", (int)newCapacity);
        return newParking;
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