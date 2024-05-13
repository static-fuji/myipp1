package medals2;

/**
 * @author g2124040 藤本陽人
 * 
 */
public class Country implements Comparable {
    private String name;
    private int gold;
    private int silver;
    private int bronze;

    public Country (String name){
        this.name = name;
        this.gold = 0;
        this.silver = 0;
        this.bronze = 0;
    }

    public int compareTo(Object obj) {
        Country c = (Country) obj;
        // ここに作業3で処理を加える
        if(this.gold > c.gold){
            return -1;
        }else if (this.gold < c.gold){
            return 1;
        }

        if(this.silver > c.silver){
            return -1;
        }else if (this.silver < c.silver){
            return 1;
        }

        if(this.bronze > c.bronze){
            return -1;
        }else if (this.bronze < c.bronze){
            return 1;
        }

        return this.name.compareTo(c.getName());
    }

    public String getName (){
        return this.name;
    }

    public void add (Color color){
        switch (color) {
            case Gold:
                this.gold ++;
                break;
            case Silver:
                this.silver ++;
                break;
            case Bronze:
                this.bronze ++;
                break;
            default:
        }
    }

    public String toString(){
        int TotalMedal = this.gold + this.silver + this.bronze;
        return this.name + "(" + this.gold + "," + this.silver + "," + this.bronze + ")" +"[" + TotalMedal + "]"; 
    }

    public static void main(String[] args) {
        Country c = new Country("Test");
        System.out.println(c.getName());
        System.out.println(c.toString());
        c.add(Color.Bronze);
        System.out.println(c.toString());
        c.add(Color.Silver);
        System.out.println(c.toString());
        c.add(Color.Gold);
        System.out.println(c.toString());
    }
}