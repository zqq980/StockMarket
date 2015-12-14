/***
 * StockFileReader is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * StockFileReader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Neuroph. If not, see <http://www.gnu.org/licenses/>.
 */

package neural.stock.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import util.stdlib.In;

public class StockFileReader {

    private int maxCounter;
    private String[] valuesRow;

    public String[] getValuesRow() {
        return valuesRow;
    }

    public void setValuesRow(String[] valuesRow) {
        this.valuesRow = valuesRow;
    }

    public int getMaxCounter() {
        return maxCounter;
    }

    public void setMaxCounter(int maxCounter) {
        this.maxCounter = maxCounter;
    }

    public StockFileReader() {
        this.setMaxCounter(100);
    }

    public StockFileReader(int maxCounter) {
        this.setMaxCounter(maxCounter);
    }

    @SuppressWarnings("static-access")
    public ArrayList<String[]> read(String fileName) {
    	
        ArrayList<String[]> traningList = new ArrayList<String[]>();
        
    	String[] s1 = null;
        HashMap hm = new HashMap();
        File file = new File(fileName);
        System.out.println("file = " + fileName+". It will be filtered the values for the moment of the market opened");
        int counter = 0;
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader dis = new BufferedReader(new InputStreamReader(fis));
            String s;

            
            while ((s = dis.readLine()) != null) {
                //System.out.println(s);
                s1 = s.split(",");
                
                traningList.add(s1);
             // Print ------------------------------------------------------------------        
                String s00 = s1[0].replace('\"', ' ').trim();
                String s01 = s1[1].replace('\"', ' ').trim();
                hm.put(s00, s.replace('\"', ' ').trim());
                System.out.println(s00 + " " + s01);
                counter = counter + 1;
             // Print End ------------------------------------------------------------------
            }
            fis.close();
        } catch (IOException ioe) {
            System.out.println("Oops- an IOException happened.");
            ioe.printStackTrace();
            System.exit(1);
        }
       // Print ------------------------------------------------------------------
        System.out.println("full number of values = " + counter);        
        Set s = hm.keySet();
        
        Iterator i = s.iterator();
        valuesRow = new String[this.getMaxCounter()];
        int n = 0;
        while (i.hasNext()) {
            String key = (String) i.next();
            String value = (String) hm.get(key);
            //System.out.println(key + "->" + value);
            n = n + 1;
            if (counter - n < this.getMaxCounter()) {
                valuesRow[counter - n] = value;
                System.out.println(counter + " " + n + " " + valuesRow[counter - n] + " " + (counter - n));
            }
        }
        
        System.out.println("valuesRow.length=" + valuesRow.length);
     // Print End ------------------------------------------------------------------
        return traningList;
    }
    
    /*
     * 
     */
    
    public ArrayList<String[]> readcsv(String fileName) {
    	
        ArrayList<String[]> traningList = new ArrayList<String[]>();
    	String[] s1 = null;
        HashMap hm = new HashMap();
        File file = new File(fileName);

        int counter = 0;
        
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader dis = new BufferedReader(new InputStreamReader(fis));
            String s;

            while ((s = dis.readLine()) != null) {
                //System.out.println(s);
                //s1 = s.split(" ");
            	s1 = s.split(",");
                
                traningList.add(s1);
            }
            fis.close();
        } catch (IOException ioe) {
            System.out.println("IOException");
            ioe.printStackTrace();
            System.exit(1);
        }
        
        return traningList;
    }
    
    /*
     * 
     */
    
    public void readFile(String filenameStr) {
        //valuesRow = new String[this.getMaxCounter()];
        
        File file = new File(filenameStr);
        
        In in = new In(file);
        
        String strLine = in.readLine();

        int i = 0;

        while(strLine != null) {
           System.out.println(strLine);
                  //valuesRow[i] = strLine;
           strLine = in.readLine();
                  i++;
        }      
        //System.out.println("valuesRow.length=" + valuesRow.length);
    }
    
}
