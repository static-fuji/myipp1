package opendata1;

import opendata0.ReadFromURL;
import java.util.*;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class MountainData {

    private static final String URL = "http://linkdata.org/download/rdf1s4006i/link/mountains_Japan.txt";
	private static final String CODE = "UTF-8";	
	private final int INDEX_HEIGHT = 3;
	protected ArrayList<String> data;
	protected int count;

    public MountainData() {
        boolean status;
        ReadFromURL readFromURL = new ReadFromURL(URL, CODE);
        status = readFromURL.readData();
        if (status == true) {
            this.data = readFromURL.getData();
        }
        this.count = 0;
    }

    public int getCount() {
        return this.count;
    }

    public ArrayList<String> getData() {
        return this.data;
    }

    public void preview() {
        for(String line : this.data) {
            String s = line;
            String[] word = s.split("\t");
            try{
            System.out.println(word[3]);
            } catch(Exception e) {
                this.count ++;
            }
        }
    }
    public static void main(String[] args) {
        MountainData trfd = new MountainData();
		trfd.preview();
		System.out.println("Exceptions: " + trfd.getCount());
    }
}