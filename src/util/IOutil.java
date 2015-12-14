package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IOutil {
	
   /**
   * =============================
   *        Read File
   * =============================
   */

   public ArrayList<String> inputFromFile(String fileNameReader) {

      ArrayList<String> inputArrayListStr = new ArrayList<String>();
      
      //String fileNameReader = new String();
      //fileNameReader = selectReadFile();
      System.out.println("(IOutil.inputFromFile) input file NAME: " + fileNameReader);

      try {
	      // Reading input by lines:
	      BufferedReader in = new BufferedReader(new FileReader(fileNameReader));

	      String s;

	      while((s = in.readLine())!= null) {
	    	  inputArrayListStr.add(s);
	      }

	      in.close();

      } catch (Exception e) {
	      System.err.println("===== IOutil.inputFromFile ERROR =====");
      }
	    
      return inputArrayListStr;
   }
	  
	/*
	 * ==================== outputStrToFile ====================
	*/
   
	public static void outputStrToFile(String outputStr, String outputFileStr) {
		File outputFile = new File(outputFileStr);
	 	DataOutputStream output = null;
	 	try {
	 	    output = new DataOutputStream(new FileOutputStream(outputFile));
	        output.writeBytes("" + outputStr);
	     } catch (IOException ex) {
	        ex.printStackTrace();
	     } finally {
	     	try {
	 			output.close();
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	     }      // try
	     
	}         // outputStrToFile

}