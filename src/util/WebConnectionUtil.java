/**
 * version 1.00 1999-08-27
 * author Cay Horstmann
 */

package util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import util.Base64OutputStream;


public class WebConnectionUtil {

	private String webSiteStr;

	public WebConnectionUtil() {

	}

	public URLConnection getConnect(String urlStr, String username, String password) {

		String outputStr = new String();
		outputStr = null;

		URLConnection connection = null;

		try {

			URL url = new URL(urlStr);
			connection = url.openConnection();

			if (username != null && password != null) {
				String input = username + ":" + password;
				String encoding = base64Encode(input);
				connection.setRequestProperty("Authorization", "Basic " + encoding);
			}

			connection.connect();

		} catch (IOException exception) {
			System.out.println("Error: " + exception);
		}

		return connection;
	}

	/*
	 * get header fields
	 */

	public String getWebHeader(URLConnection connection) {

		System.out.println();
		System.out.println("---------- headerFieldkey ----------");

		String headerFieldkeyStr = null;
		String keyStr;

		int ikey = 1;
		while ((keyStr = connection.getHeaderFieldKey(ikey)) != null) {
			String value = connection.getHeaderField(ikey);

			headerFieldkeyStr = headerFieldkeyStr + "<br>" + keyStr + ": " + value;

			System.out.println(keyStr + ": " + value);
			ikey++;
		}

		String headerInfoStr = null;
		headerInfoStr = headerInfoStr + "getContentType: " + connection.getContentType() + "<br>";
		headerInfoStr = headerInfoStr + "getContentLength: " + connection.getContentLength()
				+ "<br>";
		headerInfoStr = headerInfoStr + "getContentEncoding: " + connection.getContentEncoding()
				+ "<br>";
		headerInfoStr = headerInfoStr + "getDate: " + connection.getDate() + "<br>";
		headerInfoStr = headerInfoStr + "getExpiration: " + connection.getExpiration() + "<br>";
		headerInfoStr = headerInfoStr + "getLastModifed: " + connection.getLastModified();

		// print convenience functions
		System.out.println("---------- headerInfo ----------- ");
		System.out.println("getContentType: " + connection.getContentType());
		System.out.println("getContentLength: " + connection.getContentLength());
		System.out.println("getContentEncoding: " + connection.getContentEncoding());
		System.out.println("getDate: " + connection.getDate());
		System.out.println("getExpiration: " + connection.getExpiration());
		System.out.println("getLastModifed: " + connection.getLastModified());
		System.out.println("----------");

		String headerStr = headerFieldkeyStr + "\n" + headerInfoStr;

		return headerStr;

	}

	/*
	    * 
	    */

	public String getContent(URLConnection connection) {

		String contentStr = null;

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection
					.getInputStream()));

			// print first ten lines of contents

			String line;

			// while ((line = in.readLine()) != null && n <= 1) {
			while ((line = in.readLine()) != null) {
				contentStr = contentStr + "\n" + line;

				//System.out.println(line);
			}

			if (line != null) {
				//System.out.println(". . .");
			}

		} catch (IOException exception) {
			System.out.println("Error: " + exception);
		}

		// outputToFile(contentStr, file_announcement);

		return contentStr;

	}

	/*
	    * 
	    */
	public File buildNWSOutputFile(String pathStr, String forecastStr) {

		String[] forecastStrArr = forecastStr.split("\n");
		// System.out.println(forecastStrArr.length);
		// System.out.println(forecastStrArr[8]);

		String[] dateStrArr = new String[7];

		for (int ii = 0; ii < 15; ii++) {
			String[] tmpStr = forecastStrArr[ii].split(" ");
			if (tmpStr.length > 3) {
				if (tmpStr[2].equals("MST")) {
					for (int jj = 0; jj < tmpStr.length; jj++) {
						dateStrArr[jj] = tmpStr[jj];
					}
				}
			}
		}

		// System.out.println(dateStrArr[6]);

		/*
		 * Long DateStr = null;
		 * 
		 * Date date = new Date(DateStr); String dateStr =
		 * Globals.FORMATER_yyyyMMdd_HHmmss.format(date);
		 * 
		 * String fileNameOutputStr = pathStr + webSiteStr + "_" + dateStr +
		 * ".html";
		 */

		String monDigStr = getMondig(dateStrArr[4]);
		String date2DigStr = getDate2Dig(dateStrArr[5]);
		String time4DigStr = getTime4Dig(dateStrArr[0]);

		String fileNameOutputStr; // = "data/test.html";

		fileNameOutputStr = pathStr + "/NWS_" + dateStrArr[6].substring(0, 4) + monDigStr
				+ date2DigStr + "_" + dateStrArr[1] + "_" + time4DigStr + ".html";

		System.out.println("fileNameOutputStr" + fileNameOutputStr);

		File fileNameOutput = new File(fileNameOutputStr);

		return fileNameOutput;
	}

	/*
    * 
    */
	public File buildSpotOutputFile(String pathStr, String urlStr) {

		String[] urlStrArr = urlStr.split("=");

		String[] urlStrArr2 = urlStrArr[2].split("&");
		String fileNameOutputStr; // = "data/test.html";

		fileNameOutputStr = pathStr + "/Spot_" + urlStrArr2[0] + ".html";

		System.out.println("fileNameOutputStr" + fileNameOutputStr);

		File fileNameOutput = new File(fileNameOutputStr);

		return fileNameOutput;
	}

	/*
	    * 
	    */

	private String getMondig(String monStr) {
		String monDigStr = monStr;
		if (monStr.equals("JAN"))
			monDigStr = "01";
		if (monStr.equals("FEB"))
			monDigStr = "02";
		if (monStr.equals("MAR"))
			monDigStr = "03";
		if (monStr.equals("APR"))
			monDigStr = "04";
		if (monStr.equals("MAY"))
			monDigStr = "05";
		if (monStr.equals("JUN"))
			monDigStr = "06";
		if (monStr.equals("JUL"))
			monDigStr = "07";
		if (monStr.equals("AUG"))
			monDigStr = "08";
		if (monStr.equals("SEP"))
			monDigStr = "09";
		if (monStr.equals("OCT"))
			monDigStr = "10";
		if (monStr.equals("NOV"))
			monDigStr = "11";
		if (monStr.equals("DEC"))
			monDigStr = "12";
		return monDigStr;
	}

	/*
    * 
    */

	private String getDate2Dig(String dateAStr) {
		String date2DigStr = dateAStr;
		if (Integer.parseInt(dateAStr) < 10)
			date2DigStr = "0" + dateAStr;
		return date2DigStr;
	}

	/*
    * 
    */

	private String getTime4Dig(String timeStr) {
		String time4DigStr = timeStr;
		if (timeStr.length() < 4)
			time4DigStr = "0" + timeStr;
		return time4DigStr;
	}

	/*
	    * 
	    */
	public File buildOutputFile(String pathStr, String webSiteStr, URLConnection connection) {
		// File fileNameOutput = new File("data/cache/" +
		// "20101209.STORM.01.html");
		Long DateStr = connection.getDate();
		Date date = new Date(DateStr);

		DateFormat FORMATER_yyyyMMdd_HHmmss = new SimpleDateFormat("yyyyMMdd_HHmmss");

		String dateStr = FORMATER_yyyyMMdd_HHmmss.format(date);

		String fileNameOutputStr = pathStr + webSiteStr + "_" + dateStr + ".html";
		File fileNameOutput = new File(fileNameOutputStr);

		return fileNameOutput;
	}

	/*
    * 	   
    */

	public String nwsParseHTML(String urlStr) {

		String forecastStr = "<pre>\n";

		// HtmlParseUtil htmlParseUtil = new HtmlParseUtil();

		try {
			// Parser parser = new Parser
			// ("http://www.crh.noaa.gov/product.php?site=NWS&issuedby=GJT&product=AFD&format=TXT&version=1&glossary=0");
			// Parser parser = new Parser
			// ("/uufs/chpc.utah.edu/common/home/mace_grp/web/html/stormvex/forecast/nws/nws_20101214_123425.html");
			Parser parser = new Parser(urlStr);

			// NodeFilter filter = new TagNameFilter("div");
			NodeFilter filter = new StringFilter("pre");

			NodeList list = parser.parse(filter);
			// NodeList list = parser.parse(null);
			// NodeList list = parser.extractAllNodeshatMatch(filter);
			// System.out.println (list.size());
			// System.out.println (list.toHtml());

			for (int ii = 0; ii < list.size(); ii++) {
				// System.out.println("========================================="
				// + ii);
				Node node = list.elementAt(ii);

				// System.out.println(node.getText());
				// System.out.println(node.toPlainTextString());
				// htmlParseUtil.processMyNodes(node);

				// this will print the div html <div id='two'> some text two
				// </div>
				// now I need to extract the text from this div tag
				/*
				 * Parser tagParser = new Parser();
				 * tagParser.setInputHTML(list.toHtml()); StringBean sb = new
				 * StringBean (); tagParser.visitAllNodesWith (sb);
				 * System.out.println(sb.getStrings ()); // this will print the
				 * content "some text two"
				 */
			}

			int ii = 1;
			// System.out.println("=========================================" +
			// ii);
			Node node = list.elementAt(ii);

			forecastStr = forecastStr + node.getText();
			// String forecastStr = node.toPlainTextString();

			// System.out.println(node.getText());
			// System.out.println(node.toPlainTextString());

			// TextNode text = (TextNode)node;
			// System.out.println ("===text===\n" + text.getText ());

			// NodeList sublist = node.getChildren();
			// System.out.println (sublist.toString());
			// System.out.println (sublist.size());

		} catch (ParserException pe) {
			pe.printStackTrace();
		}

		forecastStr = forecastStr + "</pre>\n";

		return forecastStr;
	}

	/*
    * 	   
    */

	public String spotParseHTML(String urlStr) {

		String forecastStr = null;

		// HtmlParseUtil htmlParseUtil = new HtmlParseUtil();

		try {
			// Parser parser = new Parser
			// ("http://www.crh.noaa.gov/product.php?site=NWS&issuedby=GJT&product=AFD&format=TXT&version=1&glossary=0");
			// Parser parser = new Parser
			// ("/uufs/chpc.utah.edu/common/home/mace_grp/web/html/stormvex/forecast/nws/nws_20101214_123425.html");
			Parser parser = new Parser(urlStr);

			// NodeFilter filter = new TagNameFilter("div");
			// NodeFilter filter = new StringFilter("pre");

			// NodeList list = parser.parse(filter);
			NodeList list = parser.parse(null);
			// NodeList list = parser.extractAllNodeshatMatch(filter);
			// System.out.println (list.size());
			// System.out.println (list.toHtml());
			forecastStr = list.toHtml();

			/*
			 * for (int ii=0; ii<list.size(); ii++) {
			 * System.out.println("=========================================" +
			 * ii); Node node = list.elementAt(ii);
			 * 
			 * System.out.println(node.getText());
			 * //System.out.println(node.toPlainTextString());
			 * //htmlParseUtil.processMyNodes(node);
			 * 
			 * // this will print the div html <div id='two'> some text two
			 * </div> // now I need to extract the text from this div tag
			 * 
			 * Parser tagParser = new Parser();
			 * tagParser.setInputHTML(list.toHtml()); StringBean sb = new
			 * StringBean (); tagParser.visitAllNodesWith (sb);
			 * System.out.println(sb.getStrings ()); // this will print the
			 * content "some text two"
			 * 
			 * }
			 * 
			 * int ii=1;
			 * //System.out.println("========================================="
			 * + ii); Node node = list.elementAt(ii);
			 * 
			 * forecastStr = node.getText(); //String forecastStr =
			 * node.toPlainTextString();
			 * 
			 * System.out.println(node.getText());
			 * //System.out.println(node.toPlainTextString());
			 * 
			 * //TextNode text = (TextNode)node; //System.out.println
			 * ("===text===\n" + text.getText ());
			 * 
			 * //NodeList sublist = node.getChildren(); //System.out.println
			 * (sublist.toString()); //System.out.println (sublist.size());
			 */
		} catch (ParserException pe) {
			pe.printStackTrace();
		}

		return forecastStr;
	}

	// ==================== outputToFile ====================

	public void outputToFile(String announcementText, File file_announcement) {
		DataOutputStream output = null;
		try {
			output = new DataOutputStream(new FileOutputStream(file_announcement));
			output.writeBytes("" + announcementText);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setWebSiteStr(String webSiteStr) {
		this.webSiteStr = webSiteStr;
	}

	public String getWebSiteStr() {
		return webSiteStr;
	}

	// * +++++++++++++++++++++ *//
	// *
	// * +++++++++++++++++++++ *//

	public static String base64Encode(String s) {

		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		Base64OutputStream out = new Base64OutputStream(bOut);

		try {
			out.write(s.getBytes());
			out.flush();

		} catch (IOException exception) {
		}

		return bOut.toString();
	}

}
