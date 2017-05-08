package myurl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadingUrl {

	public static void main(String[] args) {
		String myURL = "http://code-master.com.ua";
		byte[] data = null;
		InputStream is = null;

		try {
		    URL url = new URL(myURL);
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.connect();
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    is = conn.getInputStream();

		    byte[] buffer = new byte[8192];
		    int bytesRead;
		    while ((bytesRead = is.read(buffer)) != -1) {
		        baos.write(buffer, 0, bytesRead);
		    }
		    data = baos.toByteArray();
		} catch (Exception e) {
		} finally {
		    try {
		        if (is != null)
		            is.close();
		    } catch (Exception ex) {}
		}
		System.out.println(new String(data));

	}

}
