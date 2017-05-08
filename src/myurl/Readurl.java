package myurl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Readurl {

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.realcoding.net/teach/java/Glava18/Index1.htm");
		URLConnection urlc = url.openConnection();
		InputStream is = new BufferedInputStream(urlc.getInputStream());
		byte[] data = new byte[is.available()];
		is.read(data);
		is.close();
		System.out.println(new String(data,"utf8"));

	}

}
