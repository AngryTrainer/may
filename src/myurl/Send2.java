package myurl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Send2 {

	public static void main(String[] args) {
		String myURL = "http://code-master.com.ua/post.php";
		String params = "login=admin";
		byte[] data = null;
		InputStream is = null;

		try {
		    URL url = new URL(myURL);
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setDoOutput(true);
		    conn.setDoInput(true);

		    conn.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
		    OutputStream os = conn.getOutputStream();
		    data = params.getBytes("UTF-8");
		    os.write(data);
		    data = null;

		    conn.connect();
		    int responseCode= conn.getResponseCode();

		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    is = conn.getInputStream();

		    byte[] buffer = new byte[8192]; // Такого вот размера буфер
		    // Далее, например, вот так читаем ответ
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
