package opendata0;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author g2124040 藤本陽人
 * 
 */

public class ReadFromURL {

    private String url;
    private String code;
    private ArrayList<String> data;

    public ReadFromURL(String url,String code) {
        this.url = url;
        this.code = code;
        this.data = new ArrayList<String>();
    }

    public boolean readData(){
        try {
            URL url = new URL(this.url);
            InputStream is = url.openStream();
		    InputStreamReader isr = new InputStreamReader(is, code);
		    BufferedReader br = new BufferedReader(isr);
            String line = null;
		    while((line = br.readLine()) != null) {
			    this.data.add(line);
		    }
		    br.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean writeToFile(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            for (String line : data) {
                fw.write(line + "\n");
            }
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<String> getData() {
        return data;
    } 
    public static void main(String[] args) {
        final String URL = "https://www.city.hakodate.hokkaido.jp/docs/2024013000145/file_contents/tyoubetsuR0604.csv";
		final String CODE = "SJIS";
		final String OUTFILE = "data.txt";
		ArrayList<String> data;
		boolean status;

		ReadFromURL rcsv = new ReadFromURL(URL, CODE);
		status = rcsv.readData();
		if (status) {
			data = rcsv.getData();
			for( final String s: data ) {
				System.out.println(s);
			}
		} else {
			System.out.println("Failed to read " + URL);
		}

		status = rcsv.writeToFile(OUTFILE);
		if (status) {
			System.out.println("Succeeded to write file " + OUTFILE);
		} else {
			System.out.println("Failed to write file " + OUTFILE);
		}
    }
}