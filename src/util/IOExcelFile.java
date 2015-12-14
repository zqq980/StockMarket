package util;

import java.io.File;
import java.io.FileInputStream;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PushbackInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IOExcelFile {

	public IOExcelFile(String filenameSTR) {
		File file = new File(filenameSTR);
		readExcel(file, filenameSTR);
	}
	
	public void readExcel(File file, String filenameSTR) {
		InputStream is = null;
		
		try {
			is = new FileInputStream(filenameSTR);
			POIFSFileSystem fs = new POIFSFileSystem(is);
		    //POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filenameSTR));
		    //POIFSFileSystem fs = new POIFSFileSystem(new InputStream(file));
		    HSSFWorkbook wb = new HSSFWorkbook(fs);
		    HSSFSheet sheet = wb.getSheetAt(0);
		    HSSFRow row;
		    HSSFCell cell;

		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();

		    int cols = 0; // No of columns
		    int tmp = 0;

		    // This trick ensures that we get the data properly even if it doesn't start from first few rows
		    for(int i = 0; i < 10 || i < rows; i++) {
		        row = sheet.getRow(i);
		        if(row != null) {
		            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
		            if(tmp > cols) cols = tmp;
		        }
		    }

		    for(int r = 0; r < rows; r++) {
		        row = sheet.getRow(r);
		        if(row != null) {
		            for(int c = 0; c < cols; c++) {
		                cell = row.getCell((short)c);
		                if(cell != null) {
		                    // Your code here
		                }
		            }
		        }
		    }
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
	}
	
	
	
	public static void unprotectXLSXSheet(String fileName, String password) throws Exception {
	    InputStream is = null;
	    FileOutputStream fileOut = null;

	    try {
	        is = new FileInputStream(fileName);
	        if (!is.markSupported()) {
	            is = new PushbackInputStream(is, 8);
	        }

	        if (POIFSFileSystem.hasPOIFSHeader(is)) {
	            POIFSFileSystem fs = new POIFSFileSystem(is);
	            EncryptionInfo info = new EncryptionInfo(fs);
	            Decryptor d = Decryptor.getInstance(info);
	            d.verifyPassword(password);
	            is = d.getDataStream(fs);
	        }

	        System.out.println(is.available());
	        XSSFWorkbook wb = new XSSFWorkbook(OPCPackage.open(is));
	        fileOut = new FileOutputStream(fileName);
	        wb.write(fileOut);
	        fileOut.flush();
	    } finally {
	        if (is != null) {
	            is.close();
	        }
	        if (fileOut != null) {
	            fileOut.close();
	        }
	    }
	}	
	
	
	
	/*
	 * 
	 */
	
    public static void main(String[] args) {
    	String filenameSTR = "X:/Cs/homeschool/sciencefair_2015-2016/StockMarket/data/goog.csv";
    	//String filenameSTR = "goog.csv";
        new IOExcelFile(filenameSTR);
    }
    
}
