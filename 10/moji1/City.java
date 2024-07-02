package moji1;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class City {
    private String name;
    private String nameKana;

    public City(String name, String nameKana) {
        this.name = name;
        this.nameKana = nameKana;
    }
    public String getName() { return this.name; }
    public String getNameKana() { return this.nameKana; }
    public String toString() { return this.name + "(" + this.nameKana + ")"; }
}
