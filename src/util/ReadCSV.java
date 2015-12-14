package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {



public static void main(String[] args) {

	ReadCSV obj = new ReadCSV();
	obj.run();

  }

  public void run() {

	//String csvFile = "/Users/mkyong/Downloads/GeoIPCountryWhois.csv";
	String filenameSTR = "X:/Cs/homeschool/sciencefair_2015-2016/StockMarket/data/goog.csv";
	String csvFile = filenameSTR;
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {

		        // use comma as separator
			String[] country = line.split(cvsSplitBy);

			System.out.println("Country [code= " + country[4] 
                                 + " , name=" + country[5] + "]");

		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	System.out.println("Done");
  }

}