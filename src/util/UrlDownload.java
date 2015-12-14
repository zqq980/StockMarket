package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlDownload {

	public UrlDownload(String urlStr, String outFile) {
		System.out.println("opening connection" + urlStr);
				
		try {
			
			URL url = new URL(urlStr);
			InputStream in = url.openStream();

			FileOutputStream fos = new FileOutputStream(new File(outFile));

			System.out.println("reading file...");
			int length = -1;
			byte[] buffer = new byte[1024];// buffer for portion of data from
											// connection

			while ((length = in.read(buffer)) > -1) {
				fos.write(buffer, 0, length);
			}

			fos.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println("file was downloaded");

	}

	public static void main(String s[]) {

		String[] mmddyyStr = { "121514", "120814" };


			for (int ii = 0; ii < mmddyyStr.length; ii++) {
				String inFileStr = "PoTW%20" + mmddyyStr[ii] + "%20Solutions.pdf";
				String outFileStr = "resA/" + inFileStr;
				
				String urlStr = "http://www.mathcounts.org/sites/default/files/images/potw/pdf/"+ inFileStr;

				new UrlDownload(urlStr, outFileStr);


			}


	}

}